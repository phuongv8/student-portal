package perscholas.capstone.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import perscholas.capstone.model.Program;
import perscholas.capstone.repositories.ProgramsRepository;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing academic programs.
 * This class provides methods for retrieving a specific program by its field of study
 * and for fetching a list of all available programs, sorted by field of study.
 */

@Service
public class ProgramService {
    private final ProgramsRepository programsRepository;

    @Autowired
    public ProgramService(ProgramsRepository programsRepository) {
        this.programsRepository = programsRepository;
    }

    /**
     * Returns the Program object by field of study from the database if it exists
     */
    public Program getProgram(String fieldOfStudy) {
        Optional<Program> program = programsRepository.findByFieldOfStudy(fieldOfStudy);
        if (program.isPresent()) {
            return program.get();
        }
        return null;
    }

    /**
     * Returns all the programs from the database
     */
    @Transactional(readOnly = true)
    public List<Program> getAllPrograms() {
        Sort sortOrder = Sort.by("fieldOfStudy").ascending();
        return programsRepository.findAll(sortOrder);
    }
}

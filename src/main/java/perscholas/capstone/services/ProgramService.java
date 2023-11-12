package perscholas.capstone.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
        try {
            Optional<Program> program = programsRepository.findByFieldOfStudy(fieldOfStudy);
            return program.orElseThrow(() -> new ProgramNotFoundException("Program not found for field of study: " + fieldOfStudy));
        } catch (DataAccessException e) {
            // Log and handle the data access exception
            throw new ProgramServiceException("Error accessing data for field of study: " + fieldOfStudy, e);
        }
    }


    /**
     * Returns all the programs from the database, sorted by field of study
     */
    @Transactional(readOnly = true)
    public List<Program> getAllPrograms() {
        try {
            Sort sortOrder = Sort.by("fieldOfStudy").ascending();
            return programsRepository.findAll(sortOrder);
        } catch (DataAccessException e) {
            throw new ProgramServiceException("Failed to retrieve all programs", e);
        }
    }

    public static class ProgramServiceException extends RuntimeException {
        public ProgramServiceException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static class ProgramNotFoundException extends RuntimeException {
        public ProgramNotFoundException(String message) {
            super(message);
        }
    }
}

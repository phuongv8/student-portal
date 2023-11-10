package perscholas.capstone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import perscholas.capstone.model.Program;

import java.util.Optional;

@Repository
public interface ProgramsRepository extends JpaRepository<Program, Long> {
    Optional<Program> findByFieldOfStudy(String fieldOfStudy);
}

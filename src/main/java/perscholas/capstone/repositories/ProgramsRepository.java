package perscholas.capstone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import perscholas.capstone.model.Program;

public interface ProgramsRepository extends JpaRepository<Program, Long> {
}

package perscholas.capstone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import perscholas.capstone.model.Student;

@Repository
public interface StudentsRepository extends JpaRepository<Student, Long> {
}

package perscholas.capstone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import perscholas.capstone.model.Student;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentsRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);

    /**
     * Future implementation
     * Find students enrolled in a specific field of study
     */
    @Query("SELECT s FROM Student s WHERE s.program.fieldOfStudy = :fieldOfStudy")
    List<Student> findByFieldOfStudy(@Param("fieldOfStudy") String fieldOfStudy);

    /**
     * Future implementation
     * Find students with GPA greater than a certain value
     */
    @Query("SELECT s FROM Student s WHERE s.learnerProfile.gpa > :gpa")
    List<Student> findByGpaGreaterThan(@Param("gpa") float gpa);
}

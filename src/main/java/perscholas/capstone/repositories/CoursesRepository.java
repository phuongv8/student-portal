package perscholas.capstone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import perscholas.capstone.model.Course;
import perscholas.capstone.model.Student;

import java.util.List;

@Repository
public interface CoursesRepository extends JpaRepository<Course, Long> {

    /**
     * Future implementation
     * Find courses by title containing specific keyword
     */
    @Query("SELECT c FROM Course c WHERE c.title LIKE %:titlePart%")
    List<Course> findCourseByTitle(@Param("titlePart") String titlePart);

    /**
     * Future implementation
     * Find courses by number of credits
     */
    @Query("SELECT c FROM Course c WHERE c.numberOfCredits = :credits")
    List<Course> findCourseByNumOfCredits(@Param("credits") int credits);
}

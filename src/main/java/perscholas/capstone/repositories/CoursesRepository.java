package perscholas.capstone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import perscholas.capstone.model.Course;

@Repository
public interface CoursesRepository extends JpaRepository<Course, Long> {
}

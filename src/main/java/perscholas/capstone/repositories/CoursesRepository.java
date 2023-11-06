package perscholas.capstone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import perscholas.capstone.model.Course;

public interface CoursesRepository extends JpaRepository<Course, Long> {
}

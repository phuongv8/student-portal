package perscholas.capstone.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import perscholas.capstone.model.Course;
import perscholas.capstone.model.Student;
import perscholas.capstone.repositories.CoursesRepository;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing courses.
 * This class provides methods for retrieving all courses, finding a specific course by ID,
 * enrolling a student in a course, and removing a student from a course.
 */

@Service
public class CoursesService {
    private final CoursesRepository coursesRepository;

    @Autowired
    public CoursesService(CoursesRepository coursesRepository) {
        this.coursesRepository = coursesRepository;
    }

    @Transactional(readOnly = true)
    public List<Course> getAllCourses() {
        try {
            return coursesRepository.findAll();
        } catch (DataAccessException e) {
            throw new CourseServiceException("Failed to retrieve all courses", e);
        }
    }

    @Transactional(readOnly = true)
    public Optional<Course> findCourseById(Long courseId) {
        try {
            return coursesRepository.findById(courseId);
        } catch (DataAccessException e) {
            throw new CourseServiceException("Failed to find course with ID: " + courseId, e);
        }
    }

    public void enrollStudent(Course course, Student student) {
        try {
            course.addEnrolledStudent(student);
            coursesRepository.save(course);
        } catch (DataAccessException e) {
            throw new CourseServiceException("Error enrolling student in course", e);
        }
    }

    public void removeStudent(Course course, Student student) {
        try {
            if (!course.getEnrolledStudents().contains(student)) {
                return;
            }
            course.removeEnrolledStudent(student);
            coursesRepository.save(course);
        } catch (DataAccessException e) {
            throw new CourseServiceException("Error removing student from course", e);
        }
    }

    public static class CourseServiceException extends RuntimeException {
        public CourseServiceException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}

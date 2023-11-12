package perscholas.capstone.services;

import org.springframework.beans.factory.annotation.Autowired;
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
        return coursesRepository.findAll();
    }

    public Optional<Course> findCourseById(Long courseId) {
        return coursesRepository.findById(courseId);
    }

    public void enrollStudent(Course course, Student student) {
        course.addEnrolledStudent(student);
        coursesRepository.save(course);
    }

    public void removeStudent(Course course, Student student) {
        if (!course.getEnrolledStudents().contains(student)) {
            return;
        }
        course.removeEnrolledStudent(student);
        coursesRepository.save(course);
    }
}

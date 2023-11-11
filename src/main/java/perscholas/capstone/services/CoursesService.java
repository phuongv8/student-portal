package perscholas.capstone.services;

import org.springframework.stereotype.Service;
import perscholas.capstone.model.Course;
import perscholas.capstone.model.Student;
import perscholas.capstone.repositories.CoursesRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CoursesService {
    private final CoursesRepository coursesRepository;

    public CoursesService(CoursesRepository coursesRepository) {
        this.coursesRepository = coursesRepository;
    }

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
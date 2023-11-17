package perscholas.capstone.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import perscholas.capstone.model.Course;
import perscholas.capstone.model.LearnerProfile;
import perscholas.capstone.model.Program;
import perscholas.capstone.model.Student;
import perscholas.capstone.repositories.LearnerProfilesRepository;
import perscholas.capstone.repositories.StudentsRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Service class for managing student records.
 * This class handles business logic related to student operations and interacts with the StudentsRepository and
 * LearnerProfilesRepository for data persistence.
 */

@Service
public class StudentsService {
    private final StudentsRepository studentsRepository;
    private final LearnerProfilesRepository learnerProfilesRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public StudentsService(StudentsRepository studentsRepository, LearnerProfilesRepository learnerProfilesRepository, PasswordEncoder passwordEncoder) {
        this.studentsRepository = studentsRepository;
        this.learnerProfilesRepository = learnerProfilesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Finds a student by their email address.
     */
    @Transactional(readOnly = true)
    public Optional<Student> findStudentByEmail(String email) {
        try {
            return studentsRepository.findByEmail(email);
        } catch (DataAccessException e) {
            throw new StudentServiceException("Error finding student by email: " + email, e);
        }
    }
    /**
     * Retrieves all registered students.
     */
    @Transactional(readOnly = true)
    public List<Student> getAllRegisteredStudents() {
        try {
            return studentsRepository.findAll();
        } catch (DataAccessException e) {
            throw new StudentServiceException("Error retrieving all registered students", e);
        }
    }

    /**
     * Adds a new student to the database with the specified personal and academic details.
     */
    public void addStudent(String firstName,
                           String lastName,
                           String email, LocalDate dateOfBirth,
                           Program program) {
        try {
            LearnerProfile learnerProfile = new LearnerProfile();
            learnerProfile.setNumberOfCredits(0);
            learnerProfile.setGraduated(false);
            learnerProfile.setGpa(0.0f);
            learnerProfile.setStartYear((short) LocalDate.now().getYear());

            Student student = new Student(firstName, lastName, email, dateOfBirth, program);
            student.setLearnerProfile(learnerProfile);
            student.setPassword(passwordEncoder.encode(lastName));

            learnerProfilesRepository.save(learnerProfile);
            studentsRepository.save(student);
        } catch (DataAccessException e) {
            throw new StudentServiceException("Error adding new student", e);
        }
    }

    /**
     * Finds a student by their unique ID.
     */
    @Transactional(readOnly = true)
    public Optional<Student> findStudentById(Long id) {
        try {
            return studentsRepository.findById(id);
        } catch (DataAccessException e) {
            throw new StudentServiceException("Error finding student by ID: " + id, e);
        }
    }

    /**
     * Retrieves all courses a student is enrolled in.
     */
    @Transactional(readOnly = true)
    public Set<Course> getAllStudentCourses(Student student) {
        try {
            return student.getEnrolledIn();
        } catch (DataAccessException e) {
            throw new StudentServiceException("Error retrieving courses for student", e);
        }
    }

    public static class StudentServiceException extends RuntimeException {
        public StudentServiceException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}

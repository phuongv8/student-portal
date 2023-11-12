package perscholas.capstone.services;

import org.springframework.stereotype.Service;
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

    public StudentsService(StudentsRepository studentsRepository, LearnerProfilesRepository learnerProfilesRepository) {
        this.studentsRepository = studentsRepository;
        this.learnerProfilesRepository = learnerProfilesRepository;
    }

    /**
     * Finds a student by their email address.
     */
    public Optional<Student> findStudentByEmail(String email) {
        return studentsRepository.findByEmail(email);
    }

    /**
     * Retrieves all registered students.
     */
    public List<Student> getAllRegisteredStudents() {
        return studentsRepository.findAll();
    }

    /**
     * Adds a new student to the database with the specified personal and academic details.
     */
    public void addStudent(String firstName,
                           String lastName,
                           String email, LocalDate dateOfBirth,
                           Program program) {
        LearnerProfile learnerProfile = new LearnerProfile();
        learnerProfile.setNumberOfCredits(0);
        learnerProfile.setGraduated(false);
        learnerProfile.setGpa(0.0f);
        learnerProfile.setStartYear((short) LocalDate.now().getYear());

        Student student = new Student(firstName, lastName, email, dateOfBirth, program);
        student.setLearnerProfile(learnerProfile);

        learnerProfilesRepository.save(learnerProfile);
        studentsRepository.save(student);
    }

    /**
     * Finds a student by their unique ID.
     */
    public Optional<Student> findStudentById(Long id) {
        return studentsRepository.findById(id);
    }

    /**
     * Retrieves all courses a student is enrolled in.
     */
    public Set<Course> getAllStudentCourses(Student student) {
        return student.getEnrolledIn();
    }
}

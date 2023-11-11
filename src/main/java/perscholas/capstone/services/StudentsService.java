package perscholas.capstone.services;

import org.springframework.stereotype.Service;
import perscholas.capstone.model.Course;
import perscholas.capstone.model.Program;
import perscholas.capstone.model.Student;
import perscholas.capstone.repositories.StudentsRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StudentsService {
    private final StudentsRepository studentsRepository;

    public StudentsService(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    public Optional<Student> findStudentByEmail(String email) {
        return studentsRepository.findByEmail(email);
    }

    public List<Student> getAllRegisteredStudents() {
        return studentsRepository.findAll();
    }

    public void addStudent(String firstName,
                           String lastName,
                           String email, LocalDate dateOfBirth,
                           Program program) {
        Student student = new Student(firstName, lastName, email, dateOfBirth, program);
        studentsRepository.save(student);
    }

    public Optional<Student> findStudentById(Long id) {
        return studentsRepository.findById(id);
    }

    public Set<Course> getAllStudentCourses(Student student) {
        return student.getEnrolledIn();
    }
}

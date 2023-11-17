package perscholas.capstone.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

/**
 * Represents a student in the university system.
 * This class contains personal details of the student, their academic profile,
 * the program they are enrolled in, and the courses they are taking.
 */
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 60, nullable = false)
    private String firstName;

    @Column(length = 60, nullable = false)
    private String lastName;

    @Column(length = 60, nullable = false, unique = true)
    private String email;

    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private String password;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    private LearnerProfile learnerProfile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id")
    private Program program;

    @ManyToMany(mappedBy = "enrolledStudents", cascade = CascadeType.ALL)
    private Set<Course> enrolledIn;

    public Student() {
    }

    public Student(String firstName,
                   String lastName,
                   String email,
                   LocalDate dateOfBirth,
                   Program program) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.program = program;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public LearnerProfile getLearnerProfile() {
        return learnerProfile;
    }

    public Program getProgram() {
        return program;
    }

    public Set<Course> getEnrolledIn() {
        return enrolledIn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLearnerProfile(LearnerProfile learnerProfile) {
        this.learnerProfile = learnerProfile;
    }
}

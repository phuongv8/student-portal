package perscholas.capstone.model;

import jakarta.persistence.*;

import java.util.Set;

/**
 A Persistence entity that a course that the student can enroll or drop out of.
 Each course worth a certain amount of credits that students need to complete in order to graduate.
 **/

@Entity
@Table(name = "Courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    private String title;
    private String description;
    private int numberOfCredits;

    @ManyToMany
    @JoinTable(name = "CourseEnrollments",
    joinColumns = @JoinColumn(name = "course_id"),
    inverseJoinColumns = @JoinColumn(name = "enrolled_student_id", referencedColumnName = "id"))
    private Set<Student> enrolledStudents;

    public Course() {}

    public Course(String title, String description, int numberOfCredits) {
        this.title = title;
        this.description = description;
        this.numberOfCredits = numberOfCredits;
    }

    public  Long getCourseId() {
        return courseId;
    }

    public  String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getNumberOfCredits() {
        return numberOfCredits;
    }

    public Set<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void addEnrolledStudent(Student student) {
        enrolledStudents.add(student);
    }

    public void removeEnrolledStudent(Student student) {
        enrolledStudents.remove(student);
    }


}

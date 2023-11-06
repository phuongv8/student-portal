package perscholas.capstone.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 A Persistence entity that represents a bachelor degree of a field of study that student can pursue
 **/
@Entity
@Table(name = "Programs")
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long programId;

    @Column(nullable = false)
    private final int requiredCreditsToGraduation;

    @Column(nullable = false)
    private String fieldOfStudy;

    @OneToMany(mappedBy = "program")
    private final List<Student> students = new ArrayList<>();

    public Program() {
        this.requiredCreditsToGraduation = 0;
    }

    public Program(int requiredCreditsToGraduation, String fieldOfStudy) {
        this.requiredCreditsToGraduation = requiredCreditsToGraduation;
        this.fieldOfStudy = fieldOfStudy;
    }

    public Long getProgramId() {
        return programId;
    }

    public int getRequiredCreditsToGraduation() {
        return requiredCreditsToGraduation;
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public List<Student> getStudents() {
        return students;
    }
}

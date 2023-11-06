package perscholas.capstone.model;

import jakarta.persistence.*;

/**
 A Persistence entity that represent profile of the student that they can see when they log in
 **/

@Entity
@Table(name = "LearnerProfiles")
public class LearnerProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;

    private int numberOfCredits;

    private float gpa;
    private boolean isGraduated;
    private short startYear;

    @OneToOne(mappedBy = "learnerProfile")
    private Student student;

    public LearnerProfile() {
    }

    public LearnerProfile(short startYear) {
        this.startYear = startYear;
    }

    public Long getProfileId() {
        return profileId;
    }

    public int getNumberOfCredits() {
        return numberOfCredits;
    }

    public float getGpa() {
        return gpa;
    }

    public boolean isGraduated() {
        return isGraduated;
    }

    public short getStartYear() {
        return startYear;
    }

    public Student getStudent() {
        return student;
    }
}

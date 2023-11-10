package perscholas.capstone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import perscholas.capstone.model.LearnerProfile;

import java.util.List;

@Repository
public interface LearnerProfilesRepository extends JpaRepository<LearnerProfile, Long> {
}

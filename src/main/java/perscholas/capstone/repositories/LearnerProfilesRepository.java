package perscholas.capstone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import perscholas.capstone.model.LearnerProfile;

public interface LearnerProfilesRepository extends JpaRepository<LearnerProfile, Long> {
}

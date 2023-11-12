package perscholas.capstone.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import perscholas.capstone.model.LearnerProfile;
import perscholas.capstone.repositories.LearnerProfilesRepository;

import java.util.List;

/**
 * Service class for managing learner profiles.
 * This class handles business logic related to learner profiles and interacts with the LearnerProfilesRepository.
 */

@Service
public class LearnerProfileService {
    private final LearnerProfilesRepository learnerProfilesRepository;

    @Autowired
    public LearnerProfileService(LearnerProfilesRepository learnerProfilesRepository) {
        this.learnerProfilesRepository = learnerProfilesRepository;
    }
}

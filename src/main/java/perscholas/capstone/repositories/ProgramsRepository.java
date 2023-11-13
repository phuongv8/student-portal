package perscholas.capstone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import perscholas.capstone.model.Program;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProgramsRepository extends JpaRepository<Program, Long> {
    Optional<Program> findByFieldOfStudy(String fieldOfStudy);

    /**
     * Future implementation
     * Find programs with a specific number of required credits
     */
    @Query("SELECT p FROM Program p WHERE p.requiredCreditsToGraduation =:credits")
    List<Program> findByRequiredCredits(@Param("credits") int credits);
}

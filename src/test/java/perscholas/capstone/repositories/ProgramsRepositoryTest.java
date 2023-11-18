package perscholas.capstone.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import perscholas.capstone.model.Program;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class ProgramsRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private ProgramsRepository programsRepository;
    @Test
    public void findAndReturnFieldOfStudy() {
        Program program = new Program(120, "Computer Science");
        entityManager.persist(program);
        entityManager.flush();

        Optional<Program> found = programsRepository.findByFieldOfStudy("Computer Science");

        assertTrue(found.isPresent());
        assertEquals(program.getFieldOfStudy(), found.get().getFieldOfStudy());
    }

    @Test
    public void testFindByRequiredCredits() {
        Program program1 = new Program(120, "Computer Science");
        Program program2 = new Program(120, "Math");
        Program program3 = new Program(100, "Psychology");

        entityManager.persist(program1);
        entityManager.persist(program2);
        entityManager.persist(program3);
        entityManager.flush();

        List<Program> foundPrograms = programsRepository.findByRequiredCredits(120);

        assertEquals(2, foundPrograms.size());
        assertTrue(foundPrograms.contains(program1));
        assertTrue(foundPrograms.contains(program2));


    }

}
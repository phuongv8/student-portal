package perscholas.capstone.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import perscholas.capstone.model.Program;
import perscholas.capstone.services.ProgramService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProgramsRepositoryTest {
    @Mock
    private ProgramsRepository programsRepository;

    @Test
    public void testFindByFieldOfStudy() {
        Program program = new Program();
        program.setFieldOfStudy("Computer Science");
        when(programsRepository.findByFieldOfStudy("Computer Science")).thenReturn(Optional.of(program));

        Optional<Program> result = programsRepository.findByFieldOfStudy("Computer Science");

        assertTrue(result.isPresent());
        assertEquals("Computer Science", result.get().getFieldOfStudy());

        verify(programsRepository).findByFieldOfStudy("Computer Science");
    }

    @Test
    public void testFindByRequiredCredits() {
        Program program1 = new Program();
        program1.setRequiredCreditsToGraduation(120);

        Program program2 = new Program();
        program2.setRequiredCreditsToGraduation(120);

        when(programsRepository.findByRequiredCredits(120)).thenReturn(Arrays.asList(program1, program2));

        List<Program> results = programsRepository.findByRequiredCredits(120);

        assertEquals(2, results.size());

        verify(programsRepository).findByRequiredCredits(120);
    }
}
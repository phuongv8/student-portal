package perscholas.capstone.services;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import perscholas.capstone.model.Program;
import perscholas.capstone.repositories.ProgramsRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProgramServiceTest {
    @Mock
    private ProgramsRepository programsRepository;

    @InjectMocks
    private ProgramService programService;
    @Test
    public void testGetAllProgramsSuccess() {
        Program program1 = new Program(50, "Computer Science");
        Program program2 = new Program(50, "Mathematics");
        List<Program> mockPrograms = Arrays.asList(program1, program2);

        when(programsRepository.findAll(Sort.by("fieldOfStudy").ascending())).thenReturn(mockPrograms);

        List<Program> programs = programService.getAllPrograms();

        assertEquals(2, programs.size());
        assertTrue(programs.contains(program1));
        assertTrue(programs.contains(program2));

        verify(programsRepository).findAll(Sort.by("fieldOfStudy").ascending());
    }

    @Test
    public void testGetAllProgramsException() {
        when(programsRepository.findAll(Sort.by("fieldOfStudy").ascending()))
                .thenThrow(new DataAccessException("Database error") {});

        Exception exception = assertThrows(ProgramService.ProgramServiceException.class, () -> {
            programService.getAllPrograms();
        });

        assertEquals("Failed to retrieve all programs", exception.getMessage());

        verify(programsRepository).findAll(Sort.by("fieldOfStudy").ascending());
    }
}
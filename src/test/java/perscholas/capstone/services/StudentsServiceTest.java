package perscholas.capstone.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;
import perscholas.capstone.model.Student;
import perscholas.capstone.repositories.StudentsRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentsServiceTest {
    @Mock
    private StudentsRepository studentsRepository;

    @InjectMocks
    private StudentsService studentsService;

    @ParameterizedTest
    @ValueSource(strings = { "john@example.com", "jane@example.com" })
    public void testFindStudentByEmailSuccess(String email) {
        Student student = new Student();
        student.setEmail(email);
        when(studentsRepository.findByEmail(email)).thenReturn(Optional.of(student));

        Optional<Student> result = studentsService.findStudentByEmail(email);

        assertTrue(result.isPresent());
        assertEquals(email, result.get().getEmail());

        verify(studentsRepository).findByEmail(email);
    }

    @Test
    public void testFindStudentByEmailException() {
        String email = "unknown@example.com";
        when(studentsRepository.findByEmail(email)).thenThrow(new DataAccessException("Database error") {});

        Exception exception = assertThrows(StudentsService.StudentServiceException.class, () -> {
            studentsService.findStudentByEmail(email);
        });

        String expectedMessage = "Error finding student by email: " + email;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

        verify(studentsRepository).findByEmail(email);
    }


}
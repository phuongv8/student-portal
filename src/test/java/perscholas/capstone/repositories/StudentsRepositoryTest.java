package perscholas.capstone.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import perscholas.capstone.model.Program;
import perscholas.capstone.model.Student;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentsRepositoryTest {
    @Mock
    private StudentsRepository studentsRepository;

    @Test
    public void testFindByEmail() {
        Student student = new Student("John", "Doe", "john@example.com", null, null);
        when(studentsRepository.findByEmail("john@example.com")).thenReturn(Optional.of(student));

        Optional<Student> result = studentsRepository.findByEmail("john@example.com");

        assertTrue(result.isPresent());
        assertEquals("john@example.com", result.get().getEmail());

        verify(studentsRepository).findByEmail("john@example.com");
    }

    @Test
    public void testFindByFieldOfStudy() {
        Student student1 = new Student("Alice", "Smith", "alice@example.com", null, new Program(50,"Computer Science"));
        Student student2 = new Student("Bob", "Johnson", "bob@example.com", null, new Program(50,"Computer Science"));

        when(studentsRepository.findByFieldOfStudy("Computer Science")).thenReturn(Arrays.asList(student1, student2));

        List<Student> results = studentsRepository.findByFieldOfStudy("Computer Science");

        assertEquals(2, results.size());

        verify(studentsRepository).findByFieldOfStudy("Computer Science");
    }



}
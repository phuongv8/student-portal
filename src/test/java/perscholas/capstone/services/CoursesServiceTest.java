package perscholas.capstone.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;
import perscholas.capstone.model.Course;
import perscholas.capstone.repositories.CoursesRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CoursesServiceTest {
    @Mock
    private CoursesRepository coursesRepository;

    @InjectMocks
    private CoursesService courseService;

    @Test
    public void testFindCourseByIdSuccess() {
        Long courseId = 1L;
        Course course = new Course("Test Course", "Description", 4);
        when(coursesRepository.findById(courseId)).thenReturn(Optional.of(course));

        Optional<Course> foundCourse = courseService.findCourseById(courseId);

        assertTrue(foundCourse.isPresent());
        assertEquals(course, foundCourse.get());

        verify(coursesRepository).findById(courseId);
    }

    @Test
    public void testFindCourseByIdException() {
        Long courseId = 1L;
        when(coursesRepository.findById(courseId)).thenThrow(new DataAccessException("Database error") {});

        Exception exception = assertThrows(CoursesService.CourseServiceException.class, () -> {
            courseService.findCourseById(courseId);
        });

        String expectedMessage = "Failed to find course with ID: " + courseId;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

        verify(coursesRepository).findById(courseId);
    }


}
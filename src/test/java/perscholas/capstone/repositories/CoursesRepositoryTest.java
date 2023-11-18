package perscholas.capstone.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import perscholas.capstone.model.Course;
import perscholas.capstone.services.CoursesService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CoursesRepositoryTest {
    @Mock
    private CoursesRepository coursesRepository;

    @InjectMocks
    private CoursesService courseService;

    @Test
    void findCourseByTitle() {
        Course course1 = new Course("Intro to Programming", "Description", 4);
        Course course2 = new Course("Advanced Programming", "Description", 4);
        Course course3 = new Course("Physics 101", "Description", 3);
        Course course4 = new Course("Chemistry 101", "Description", 3);
        Course course5 = new Course("Biology 101", "Description", 4);
        when(coursesRepository.findCourseByTitle("Programming")).thenReturn(Arrays.asList(course1, course2));

        List<Course> courses = coursesRepository.findCourseByTitle("Programming");

        assertEquals(2, courses.size());
        assertTrue(courses.contains(course1));
        assertTrue(courses.contains(course2));

        verify(coursesRepository).findCourseByTitle("Programming");
    }

    @Test
    void testFindCourseByNumOfCredits() {
        Course course1 = new Course("Physics 101", "Description", 3);
        Course course2 = new Course("Chemistry 101", "Description", 3);
        Course course3 = new Course("Biology 101", "Description", 4);
        when(coursesRepository.findCourseByNumOfCredits(3)).thenReturn(Arrays.asList(course1, course2));

        List<Course> courses = coursesRepository.findCourseByNumOfCredits(3);

        assertEquals(2, courses.size());
        assertTrue(courses.contains(course1));
        assertTrue(courses.contains(course2));

        verify(coursesRepository).findCourseByNumOfCredits(3);
    }

}
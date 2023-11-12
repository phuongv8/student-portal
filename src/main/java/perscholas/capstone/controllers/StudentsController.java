package perscholas.capstone.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import perscholas.capstone.model.Course;
import perscholas.capstone.model.Student;
import perscholas.capstone.services.CoursesService;
import perscholas.capstone.services.StudentsService;

import java.util.Optional;

/**
 * Controller class for handling student-related web requests.
 * This class provides endpoints for student profile management, course enrollment,
 * and viewing enrolled courses.
 */

@Controller
public class StudentsController {
    private final StudentsService studentsService;
    private final CoursesService coursesService;

    private static final String STUDENT_VIEW = "student_view";
    private static final String REDIRECT_HOME = "redirect:/";

    public StudentsController(StudentsService studentsService, CoursesService coursesService) {
        this.studentsService = studentsService;
        this.coursesService = coursesService;
    }

    /**
     * Handles the request to get a student's profile.
     */
    @RequestMapping("/student")
    public String getStudentProfile(@RequestParam("student_id") Long studentId, Model model) {
        Optional<Student> student = studentsService.findStudentById((studentId));
        if (student.isEmpty()) {
            System.err.printf("Student with Id %d doesn't exist%n",
                    studentId);
            return REDIRECT_HOME;
        }

        model.addAttribute("student_id", student.get().getId());
        model.addAttribute("student", student.get());
        showStudentProfile(model);

        return STUDENT_VIEW;
    }

    /**
     * Handles the request to view all courses available for a student.
     */
    @GetMapping("/student/courses")
    public String getAllCourses(@RequestParam("student_id") Long studentId, Model model) {

        Optional<Student> student = studentsService.findStudentById(studentId);
        if (student.isEmpty()) {
            System.err.printf("Student with Id %d doesn't exist%n",
                    studentId);
            return REDIRECT_HOME;
        }
        model.addAttribute("student_id", studentId);
        model.addAttribute("all_courses", coursesService.getAllCourses());
        model.addAttribute("student_courses",
                studentsService.getAllStudentCourses(student.get()));
        showAllCourses(model);
        return STUDENT_VIEW;
    }

    /**
     * Handles the request for a student to enroll in a course.
     */
    @PostMapping("/student/enroll")
    public String enrollInCourse(@RequestParam("student_id") Long studentId,
                                 @RequestParam("course_id") Long courseId,
                                 Model model) {
        Optional<Student> student = studentsService.findStudentById(studentId);

        if (student.isEmpty()) {
            System.err.printf("Trying to enroll a student with Id %d that doesn't exist%n",
                    studentId);
            return REDIRECT_HOME;
        }

        Optional<Course> course = coursesService.findCourseById(courseId);
        if (course.isEmpty()) {
            System.err.printf("Trying to enroll a student to course Id: %d that doesn't exist%n",
                    courseId);
            return REDIRECT_HOME;
        }

        coursesService.enrollStudent(course.get(), student.get());

        model.addAttribute("student_id", studentId);
        model.addAttribute("all_courses", coursesService.getAllCourses());
        model.addAttribute("student_courses",
                studentsService.getAllStudentCourses(student.get()));

        showAllCourses(model);
        return STUDENT_VIEW;
    }

    /**
     * Handles the request for a student to leave a course.
     */
    @PostMapping("/student/leave_course")
    public String leaveCourse(@RequestParam("student_id") Long studentId,
                              @RequestParam("course_id") Long courseId,
                              Model model) {
        Optional<Student> student = studentsService.findStudentById(studentId);

        if (student.isEmpty()) {
            System.err.printf("Trying to un-enroll a student with Id %d that doesn't exist%n",
                    studentId);
            return REDIRECT_HOME;
        }

        Optional<Course> course = coursesService.findCourseById(courseId);
        if (course.isEmpty()) {
            System.err.printf("Trying to un-enroll a student from course Id: %d that doesn't " +
                            "exist%n",
                    courseId);
            return REDIRECT_HOME;
        }

        coursesService.removeStudent(course.get(), student.get());

        model.addAttribute("student_id", studentId);
        model.addAttribute("all_courses", coursesService.getAllCourses());
        model.addAttribute("student_courses",
                studentsService.getAllStudentCourses(student.get()));

        showAllCourses(model);
        return STUDENT_VIEW;
    }

    /**
     * Updates the model to display the student's profile.
     */
    private void showStudentProfile(Model model) {
        model.addAttribute("show_courses", false);
    }

    /**
     * Updates the model to display all courses for the student.
     */
    private void showAllCourses(Model model) {
        model.addAttribute("show_courses", true);
    }
}

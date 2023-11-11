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

@Controller
public class StudentsController {
    private final StudentsService studentsService;
    private final CoursesService coursesService;

    public StudentsController(StudentsService studentsService, CoursesService coursesService) {
        this.studentsService = studentsService;
        this.coursesService = coursesService;
    }

    @RequestMapping("/student")
    public String getStudentProfile(@RequestParam("student_id") Long studentId, Model model) {
        Optional<Student> student = studentsService.findStudentById((studentId));
        if (student.isEmpty()) {
            System.err.printf("Student with Id %d doesn't exist%n",
                    studentId);
            return "redirect:/";
        }

        model.addAttribute("student_id", student.get().getId());
        model.addAttribute("student", student.get());
        showStudentProfile(model);

        return "student_view";
    }

    @GetMapping("/student/courses")
    public String getAllCourses(@RequestParam("student_id") Long studentId, Model model) {

        Optional<Student> student = studentsService.findStudentById(studentId);
        if (student.isEmpty()) {
            System.err.printf("Student with Id %d doesn't exist%n",
                    studentId);
            return "redirect:/";
        }
        model.addAttribute("student_id", studentId);
        model.addAttribute("all_courses", coursesService.getAllCourses());
        model.addAttribute("student_courses",
                studentsService.getAllStudentCourses(student.get()));
        showAllCourses(model);
        return "student_view";
    }

    @PostMapping("/student/enroll")
    public String enrollInCourse(@RequestParam("student_id") Long studentId,
                                 @RequestParam("course_id") Long courseId,
                                 Model model) {
        Optional<Student> student = studentsService.findStudentById(studentId);

        if (student.isEmpty()) {
            System.err.printf("Trying to enroll a student with Id %d that doesn't exist%n",
                    studentId);
            return "redirect:/";
        }

        Optional<Course> course = coursesService.findCourseById(courseId);
        if (course.isEmpty()) {
            System.err.printf("Trying to enroll a student to course Id: %d that doesn't exist%n",
                    courseId);
            return "redirect:/";
        }

        coursesService.enrollStudent(course.get(), student.get());

        model.addAttribute("student_id", studentId);
        model.addAttribute("all_courses", coursesService.getAllCourses());
        model.addAttribute("student_courses",
                studentsService.getAllStudentCourses(student.get()));

        showAllCourses(model);
        return "student_view";
    }

    @PostMapping("/student/leave_course")
    public String leaveCourse(@RequestParam("student_id") Long studentId,
                              @RequestParam("course_id") Long courseId,
                              Model model) {
        Optional<Student> student = studentsService.findStudentById(studentId);

        if (student.isEmpty()) {
            System.err.printf("Trying to un-enroll a student with Id %d that doesn't exist%n",
                    studentId);
            return "redirect:/";
        }

        Optional<Course> course = coursesService.findCourseById(courseId);
        if (course.isEmpty()) {
            System.err.printf("Trying to un-enroll a student from course Id: %d that doesn't " +
                            "exist%n",
                    courseId);
            return "redirect:/";
        }

        coursesService.removeStudent(course.get(), student.get());

        model.addAttribute("student_id", studentId);
        model.addAttribute("all_courses", coursesService.getAllCourses());
        model.addAttribute("student_courses",
                studentsService.getAllStudentCourses(student.get()));

        showAllCourses(model);
        return "student_view";
    }

    private void showStudentProfile(Model model) {
        model.addAttribute("show_courses", false);
    }

    private void showAllCourses(Model model) {
        model.addAttribute("show_courses", true);
    }
}
package perscholas.capstone.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import perscholas.capstone.model.Course;
import perscholas.capstone.model.Program;
import perscholas.capstone.model.Student;
import perscholas.capstone.services.CoursesService;
import perscholas.capstone.services.ProgramService;
import perscholas.capstone.services.StudentsService;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

/**
 * Controller class for handling requests to the home page and its related sections.
 * This class provides endpoints for displaying lists of students, courses, and programs on the home page.
 */

@Controller
public class HomePageController {
    private final StudentsService studentsService;
    private final ProgramService programService;
    private final CoursesService coursesService;
    private static final String PUBLIC_VIEW = "public_view";

    public HomePageController(StudentsService studentsService,
                              ProgramService programService,
                              CoursesService coursesService) {
        this.studentsService = studentsService;
        this.programService = programService;
        this.coursesService = coursesService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    /**
     * Handles the request to display all registered students.
     */
    @GetMapping("/students")
    public String getAllStudents(Model model, Principal principal) {
        addCommonAttributes(model, principal);
        List<Student> allRegisteredStudents = studentsService.getAllRegisteredStudents();
        model.addAttribute("students", allRegisteredStudents);
        model.addAttribute("show_students", true);
        return PUBLIC_VIEW;
    }

    /**
     * Handles the request to display all courses.
     */
    @GetMapping("/courses")
    public String displayAllCourses(Model model, Principal principal) {
        addCommonAttributes(model, principal);
        List<Course> courses = coursesService.getAllCourses();
        model.addAttribute("courses", courses);
        model.addAttribute("show_courses", true);
        return PUBLIC_VIEW;
    }

    /**
     * Handles the request to display all academic programs.
     */
    @GetMapping("/programs")
    public String displayAllPrograms(Model model, Principal principal) {
        addCommonAttributes(model, principal);
        List<Program> programs = programService.getAllPrograms();
        model.addAttribute("programs", programs);
        model.addAttribute("show_programs", true);
        return PUBLIC_VIEW;
    }

    /**
     * Adds common attributes to the model for authentication and user information.
     */
    private void addCommonAttributes(Model model, Principal principal) {
        boolean isLoggedIn = principal != null;
        model.addAttribute("isLoggedIn", isLoggedIn);
        if (isLoggedIn) {
            Optional<Student> student = studentsService.findStudentByEmail(principal.getName());
            student.ifPresent(value -> model.addAttribute("student_id", value.getId()));
        }
        model.addAttribute("show_students", false);
        model.addAttribute("show_courses", false);
        model.addAttribute("show_programs", false);
        model.addAttribute("show_scores", false);
    }
}


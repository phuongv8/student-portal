package perscholas.capstone.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import perscholas.capstone.model.Student;
import perscholas.capstone.repositories.StudentsRepository;
import perscholas.capstone.services.StudentsService;

import java.security.Principal;
import java.util.Optional;

/**
 * Controller class for handling student login requests.
 * This class provides endpoints for students to log in to the system using their credentials.
 */

@Controller
public class LoginController {
    private final StudentsService studentsService;
    private final StudentsRepository studentsRepository;
    private static final String LOGIN_PAGE = "login";

    public LoginController(StudentsService studentsService, StudentsRepository studentsRepository) {
        this.studentsService = studentsService;
        this.studentsRepository = studentsRepository;
    }

    /**
     * Handles the request to display the login page.
     */
    @GetMapping("/login")
    public String login() {
        return LOGIN_PAGE;
    }

    @GetMapping("/profile")
    public String defaultAfterLogin(Principal principal) {
        String email = principal.getName();
        Optional<Student> student = studentsService.findStudentByEmail(email);

        return student.map(value -> "redirect:/student?student_id=" + value.getId()).orElse("redirect:/error");
    }


    /**
     * Handles the POST request for student login.
     * Validates the student's email and password, and redirects to the student profile page upon successful login.
     * @return The name of the view to be rendered or a redirection on successful login.
     */
//    @PostMapping("/login")
//    public String studentLogin(@RequestParam("email") String email,
//                               @RequestParam("password") String password,
//                               Model model) {
//        Optional<Student> student = studentsService.findStudentByEmail(email);
//        if (student.isEmpty()) {
//            model.addAttribute("error", true);
//            model.addAttribute("error_message", "Student with this email wasn't found");
//            return LOGIN_PAGE;
//        }
//
//        if (!student.get().getLastName().equalsIgnoreCase(password)) {
//            model.addAttribute("error", true);
//            model.addAttribute("error_message", "Password doesn't match");
//            return LOGIN_PAGE;
//        }
//
//        return "redirect:student?student_id=" + student.get().getId();
//    }
}

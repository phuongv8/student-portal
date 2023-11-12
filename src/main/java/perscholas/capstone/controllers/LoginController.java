package perscholas.capstone.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import perscholas.capstone.model.Student;
import perscholas.capstone.services.StudentsService;

import java.util.Optional;

/**
 * Controller class for handling student login requests.
 * This class provides endpoints for students to log in to the system using their credentials.
 */

@Controller
public class LoginController {
    private final StudentsService studentsService;

    public LoginController(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    /**
     * Handles the request to display the login page.
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * Handles the POST request for student login.
     * Validates the student's email and password, and redirects to the student profile page upon successful login.
     * @return The name of the view to be rendered or a redirection on successful login.
     */
    @PostMapping("/login")
    public String studentLogin(@RequestParam("email") String email,
                               @RequestParam("password") String password,
                               Model model) {
        Optional<Student> student = studentsService.findStudentByEmail(email);
        if (student.isEmpty()) {
            model.addAttribute("error", true);
            model.addAttribute("error_message", "Student with this email wasn't found");
            return "login";
        }

        if (!student.get().getLastName().equalsIgnoreCase(password)) {
            model.addAttribute("error", true);
            model.addAttribute("error_message", "Password doesn't match");
            return "login";
        }

        return "redirect:student?student_id=" + student.get().getId();
    }
}

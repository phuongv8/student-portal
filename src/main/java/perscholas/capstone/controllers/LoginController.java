package perscholas.capstone.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import perscholas.capstone.model.Student;
import perscholas.capstone.services.StudentsService;

import java.util.Optional;

@Controller
public class LoginController {
    private final StudentsService studentsService;

    public LoginController(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

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

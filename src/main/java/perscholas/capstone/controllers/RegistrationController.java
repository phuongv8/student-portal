package perscholas.capstone.controllers;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import perscholas.capstone.model.Program;
import perscholas.capstone.services.ProgramService;
import perscholas.capstone.services.StudentsService;

import java.time.LocalDate;
import java.util.Locale;

/**
 * Controller class for handling registration of new students.
 * This class provides endpoints for new students to sign up and get registered in the system.
 */

@Controller
public class RegistrationController {
    private final StudentsService studentsService;
    private final ProgramService programService;
    private static final String REGISTRATION_CONFIRMATION = "registration-confirmation";

    public RegistrationController(StudentsService studentsService, ProgramService programService) {
        this.studentsService = studentsService;
        this.programService = programService;
    }

    /**
     * Handles the request to sign up a new student.
     * Registers a student with the provided personal details and enrolls them in a program.
     * @return A redirection to the home page after successful registration.
     */
    @GetMapping("/register")
    public String signUpNewStudent(@RequestParam("firstName") String firstName,
                                   @RequestParam("lastName") String lastName,
                                   @RequestParam("email") String email,
                                   @RequestParam("dateOfBirth") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateOfBirth,
                                   @RequestParam("fieldOfStudy") String fieldOfStudy) {
        Program program = programService.getProgram(fieldOfStudy);

        studentsService.addStudent(firstName, lastName, email, dateOfBirth, program);

        // Redirect to the registration confirmation page
        return "redirect:/" + REGISTRATION_CONFIRMATION;
    }

    @GetMapping("/" + REGISTRATION_CONFIRMATION)
    public String showRegistrationConfirmation() {
        return REGISTRATION_CONFIRMATION;
    }
}

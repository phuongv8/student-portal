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

@Controller
public class RegistrationController {
    private final StudentsService studentsService;
    private final ProgramService programService;

    public RegistrationController(StudentsService studentsService, ProgramService programService) {
        this.studentsService = studentsService;
        this.programService = programService;
    }

    @GetMapping("/register")
    public String signUpNewStudent(@RequestParam("firstName") String firstName,
                                   @RequestParam("lastName") String lastName,
                                   @RequestParam("email") String email,
                                   @RequestParam("dateOfBirth")
                                   @DateTimeFormat(pattern = "yyyy-MM-dd")
                                   LocalDate dateOfBirth,
                                   @RequestParam("fieldOfStudy") String fieldOfStudy,
                                   @RequestParam("degree") String degreeString) {
        Program program = programService.getProgram(fieldOfStudy);

        studentsService.addStudent(firstName, lastName, email, dateOfBirth, program);

        return "redirect:/";
    }




}

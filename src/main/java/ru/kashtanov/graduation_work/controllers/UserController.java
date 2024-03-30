package ru.kashtanov.graduation_work.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kashtanov.graduation_work.services.AdminService;
import ru.kashtanov.graduation_work.models.UserEntity;
import ru.kashtanov.graduation_work.secutiry.PasswordConfig;
import ru.kashtanov.graduation_work.models.Student;
import ru.kashtanov.graduation_work.services.StudentService;
import ru.kashtanov.graduation_work.services.TutorService;
import ru.kashtanov.graduation_work.services.UserEntityService;

/**
 * UserController is design for processing requests of logging and registration
 */
@Controller
public class UserController {
    private final StudentService studentService;
    private final TutorService tutorService;
    private final AdminService adminService;
    private final UserEntityService userEntityService;
    private final PasswordConfig passwordConfig;


    public UserController(StudentService studentService,
                          TutorService tutorService,
                          AdminService adminService,
                          UserEntityService userEntityService,
                          PasswordConfig passwordConfig) {
        this.studentService = studentService;
        this.tutorService = tutorService;
        this.adminService = adminService;
        this.userEntityService = userEntityService;
        this.passwordConfig = passwordConfig;
    }

    @GetMapping("/register")
    public String getRegisterStudent(Model model) {
        model.addAttribute("registerRequestStudent", new Student());
        return "logPages/registeringPage";

    }

    @GetMapping("/login")
    public String getLoginStudent(Model model) {
        model.addAttribute("loginRequestStudent", new Student());
        return "logPages/loginPage";
    }

    @PostMapping("/register")
    public String registerStudent(@ModelAttribute Student student) {
        Student registeredStudent = studentService.register(student.getFirstName(), student.getSecondName(),
                student.getUsername(), student.getPassword());
        return registeredStudent == null ? "logPages/errorPage" : "redirect:/login";
    }


}

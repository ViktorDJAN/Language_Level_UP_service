package ru.kashtanov.graduation_work.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kashtanov.graduation_work.services.AdminService;
import ru.kashtanov.graduation_work.models.Student;
import ru.kashtanov.graduation_work.services.StudentService;
import ru.kashtanov.graduation_work.models.Tutor;
import ru.kashtanov.graduation_work.dto.TutorForStudentDto;
import ru.kashtanov.graduation_work.services.TutorService;

/**
 * AdminController is intended for processing requests coming to url with the Admin role
 */
@Controller
@RequestMapping("/api/v1/admin_scope")
public class AdminController {
    private final AdminService adminService;
    private final StudentService studentService;
    private final TutorService tutorService;

    public AdminController(AdminService adminService, StudentService studentService, TutorService tutorService) {
        this.adminService = adminService;
        this.studentService = studentService;
        this.tutorService = tutorService;
    }

    /**
     * This method represents the main view of user with role Admin
     *
     * @param model is used here to join data from controller to view
     * @return adminPage.html
     */
    @GetMapping("/adminPage")
    public String getAdminPage(Model model) {
        model.addAttribute("tutorAssign", new Tutor());
        model.addAttribute("studentAssign", new Student());
        model.addAttribute("tutorStudent", new TutorForStudentDto());
        model.addAttribute("tutorsBunch", tutorService.getFullTutorList());
        model.addAttribute("studentsBunch", studentService.getFullStudentList());
        model.addAttribute("withoutTutorStudents", studentService.getAllStudentsWithoutTutor());
        return "admin/adminPage";
    }


    @PostMapping("/assign")
    public String assignTutorToStudent(@ModelAttribute("tutorStudent") TutorForStudentDto tutorForStudentDto) {
        tutorService.assignTutorToStudent(tutorForStudentDto);
        return "redirect:/api/v1/admin_scope/adminPage";
    }


    @GetMapping("/tutors")
    public String getAllTutorsList(Model model) {
        model.addAttribute("tutorsBunch", tutorService.getFullTutorList());
        return "tutor/allTutors";
    }


    @GetMapping("/tutor/{tutorId}")
    public String getCertainTutor(@PathVariable("tutorId") Integer tutorId, Model model) {
        model.addAttribute("foundTutor", tutorService.getCertainTutorById(tutorId));
        return "tutor/certain_tutor";
    }

    @GetMapping("/tutor/new")
    public String getNewTutor(Model model) {
        model.addAttribute("createdTutor", new Tutor());
        return "tutor/new_tutor";

    }

    @PostMapping("/create")
    public String createTutor(@ModelAttribute("createdTutor") Tutor tutor) {
        tutorService.addTutorToDataBase(tutor);
        return "redirect:/api/v1/admin_scope/tutors";
    }

    @GetMapping("/{id}/edit")
    public String editTutor(@PathVariable("id") Integer tutorId, Model model) {
        model.addAttribute("editedTutor", tutorService.getCertainTutorById(tutorId));
        return "tutor/edit_tutorPage";
    }


    @PostMapping("/{id}/edit")
    public String update(@ModelAttribute("editedTutor") Tutor tutor, @PathVariable("id") Integer tutorId) {
        tutorService.update(tutorId, tutor);
        return "redirect:/api/v1/admin_scope/tutors";
    }

    @PostMapping("/{tutorId}")
    public String delete(@PathVariable("tutorId") Integer tutorId) {
        tutorService.deleteTutorById(tutorId);

        return "redirect:/api/v1/admin_scope/tutors";
    }
}

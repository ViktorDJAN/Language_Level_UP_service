package ru.kashtanov.graduation_work.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kashtanov.graduation_work.models.Message;
import ru.kashtanov.graduation_work.models.MessageSenderReceiver;
import ru.kashtanov.graduation_work.repositories.MessageRepository;
import ru.kashtanov.graduation_work.services.MessageService;
import ru.kashtanov.graduation_work.services.MessageSenderReceiverService;
import ru.kashtanov.graduation_work.models.Student;
import ru.kashtanov.graduation_work.services.StudentService;

import java.util.List;

/**
 * StudentController is intended for processing requests coming to url with the Student role
 */
@Controller
@RequestMapping("/api/v1/student_scope")
public class StudentController {
    private final StudentService studentService;
    private final MessageService messageService;
    private final MessageRepository messageRepository;
    private final MessageSenderReceiverService messageSenderReceiverService;


    public StudentController(StudentService studentService,
                             MessageService messageService,
                             MessageRepository messageRepository, MessageSenderReceiverService messageSenderReceiverService) {
        this.studentService = studentService;
        this.messageService = messageService;
        this.messageRepository = messageRepository;
        this.messageSenderReceiverService = messageSenderReceiverService;
    }


    @GetMapping("/menu")
    public String getMenuStudent(Model model) {
        model.addAttribute("authenticatedStudent", studentService.findAuthenticatedStudent());
        return "student/menuPage";
    }


    @GetMapping("/courses/no_tutor")
    public String getCoursesWithOutTutor() {
        return "student/withoutTutorCourses";
    }

    @GetMapping("/testing")
    public String getTestingPage() {
        return "testing/testingPage";
    }


    @PostMapping("/create")
    public String registerNewStudent(@ModelAttribute Student student) {
        studentService.addNewStudent(student);
        return "redirect:/api/v1/students";
    }


    @GetMapping("/student_chat")
    public String getStudentChatPage(Model model) {
        Student student = studentService.findAuthenticatedStudent();
        List<Message> messageList = messageService.getAllMessagesBetweenSenderAndReceiver(student.getId());
        Message message = new Message();
        model.addAttribute("enteredMessage", message);
        model.addAttribute("student", student);
        model.addAttribute("messageList", messageList);
        model.addAttribute("openClose", studentService.hasStudentTutor());
        return "student/student_chat_page";
    }

    @PostMapping("/send_msg_to_tutor")
    public String sendMessageToTutor(@ModelAttribute("enteredMessage") Message message) {
        Student student = studentService.findAuthenticatedStudent();
        studentService.sendNewMessageToTutor(message, student);
        if (studentService.hasStudentTutor()) {
            return "redirect:/api/v1/student_scope/student_chat";
        } else {
            return "redirect:/api/v1/student_scope/error_page";
        }
    }
}

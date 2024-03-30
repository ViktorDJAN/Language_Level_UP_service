package ru.kashtanov.graduation_work.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kashtanov.graduation_work.models.Message;
import ru.kashtanov.graduation_work.services.MessageService;
import ru.kashtanov.graduation_work.models.Tutor;
import ru.kashtanov.graduation_work.services.StudentService;
import ru.kashtanov.graduation_work.dto.MessageRequestDto;
import ru.kashtanov.graduation_work.services.TutorService;
import ru.kashtanov.graduation_work.services.UserEntityService;

import java.util.List;

/**
 * TutorController is intended for processing requests coming to url with the Tutor role
 */
@Controller
@RequestMapping("/api/v1/tutor_scope")
public class TutorController  {
    private final TutorService tutorService;
    private final UserEntityService userEntityService;
    private final StudentService studentService;
    private final MessageService messageService;


    public TutorController(TutorService tutorService,
                           UserEntityService userEntityService,
                           StudentService studentService,
                           MessageService messageService) {
        this.tutorService = tutorService;
        this.userEntityService = userEntityService;
        this.studentService = studentService;
        this.messageService = messageService;
    }

    @GetMapping("/tutor_chat")
    public String getTutorChatPage(Model model, Tutor tutor) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.isAuthenticated()) {
            tutor = tutorService.getCertainTutorByUsername(auth.getName());
        }
        model.addAttribute("tutorStudentList", tutor.getStudentList());
        List<Message> messageList = messageService.getAllMessagesBetweenSenderAndReceiver(tutor.getId());
        model.addAttribute("messageList", messageList);
        return "tutor/tutor_chat_page";
    }

    @GetMapping("/student/{id}")
    public String showTutorChatWithSingleStudent(@PathVariable("id") Integer studentId, Model model) {
        List<Message> messageList = messageService.getAllMessagesBetweenSenderAndReceiver(studentId);
        Message message = new Message();
        MessageRequestDto messageRequestDto = new MessageRequestDto();
        messageRequestDto.setStudentId(studentId);
        model.addAttribute("msgRequest", messageRequestDto);
        model.addAttribute("enteredMessage", message);
        model.addAttribute("studentId", studentId);
        model.addAttribute("getStudentFromList", studentService.getCertainStudentById(studentId));
        model.addAttribute("messageList", messageList);
        return "tutor/tutorChatWithSingleStudent";

    }

    @PostMapping("/student/{id}")
    public ModelAndView sendMessageToStudent(@PathVariable(name = "id") Integer id,
                                             @ModelAttribute("message") MessageRequestDto messageRequestDto, Model model) {
        Tutor tutor = tutorService.findAuthenticatedTutor();
        ModelAndView mav = new ModelAndView("redirect:/api/v1/tutor_scope/student/{id}");
        Message message = new Message();
        message.setMessage(messageRequestDto.getMessage());
        tutorService.sendNewMessageToStudent(message, tutor, messageRequestDto.getStudentId());
        return mav;
    }
}

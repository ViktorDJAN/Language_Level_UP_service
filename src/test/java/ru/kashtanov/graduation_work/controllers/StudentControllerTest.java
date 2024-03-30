package ru.kashtanov.graduation_work.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.kashtanov.graduation_work.models.Message;
import ru.kashtanov.graduation_work.models.Student;
import ru.kashtanov.graduation_work.models.Tutor;
import ru.kashtanov.graduation_work.repositories.MessageRepository;
import ru.kashtanov.graduation_work.repositories.UserEntityRepository;
import ru.kashtanov.graduation_work.secutiry.AppSuccessHandler;
import ru.kashtanov.graduation_work.secutiry.PasswordConfig;
import ru.kashtanov.graduation_work.secutiry.SecurityConfiguration;
import ru.kashtanov.graduation_work.services.MessageSenderReceiverService;
import ru.kashtanov.graduation_work.services.MessageService;
import ru.kashtanov.graduation_work.services.QuestionAnswerService;
import ru.kashtanov.graduation_work.services.StudentService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebMvcTest(StudentController.class)
@Import(SecurityConfiguration.class)
class StudentControllerTest {

    @MockBean
    StudentService studentService;
    @MockBean
    UserEntityRepository userEntityRepository;
    @MockBean
    AppSuccessHandler appSuccessHandler;
    @MockBean
    AuthenticationProvider authenticationProvider;


    @MockBean
    PasswordConfig passwordConfig;

    @MockBean
    MessageService messageService;
    @MockBean
    MessageRepository messageRepository;
    @MockBean
    MessageSenderReceiverService messageSenderReceiverService;


    @Autowired
    WebApplicationContext context;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    @WithMockUser
    void getMenuStudentTest() throws Exception {
        when(studentService.findAuthenticatedStudent()).thenReturn(new Student());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/student_scope/menu"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("student/menuPage"));

    }

    @Test
    void getCoursesWithOutTutorTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/student_scope//courses/no_tutor"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("student/withoutTutorCourses"));

    }

    @Test
    void getTestingPageTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/student_scope/testing"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("testing/testingPage"));
    }

    @Test
    void registerNewStudentTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/student_scope/send_msg_to_tutor"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }



    @Test
    void sendMessageToTutorTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/student_scope/send_msg_to_tutor"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }
}
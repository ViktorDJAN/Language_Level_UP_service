package ru.kashtanov.graduation_work.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.kashtanov.graduation_work.services.AnswerService;
import ru.kashtanov.graduation_work.services.QuestionAnswerService;
import ru.kashtanov.graduation_work.services.QuestionService;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(QuestionAnswerController.class)
class QuestionAnswerControllerTest {
    @MockBean
    QuestionAnswerService questionAnswerService;
    @MockBean
    QuestionService questionService;
    @MockBean
    AnswerService answerService;





    @Autowired
    private WebApplicationContext context;

    @Autowired
    MockMvc mockMvc;
    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    void showAllQuestionsWithTheirsAnswersTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/qaList"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("testing/testingPage"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("questionsBunch"));
    }

    @Test
    void formResultTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/resultForming"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    void getResultTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/result"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
}
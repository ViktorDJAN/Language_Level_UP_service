package ru.kashtanov.graduation_work.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.kashtanov.graduation_work.models.Tutor;
import ru.kashtanov.graduation_work.repositories.TutorRepository;
import ru.kashtanov.graduation_work.services.AdminService;
import ru.kashtanov.graduation_work.services.StudentService;
import ru.kashtanov.graduation_work.services.TutorService;

import java.util.Optional;

import static java.nio.file.Paths.get;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@WebMvcTest(AdminController.class)
class AdminControllerTest {
    @MockBean
    AdminService adminService;
    @MockBean
    StudentService studentService;
    @MockBean
    TutorService tutorService;
    @MockBean
    TutorRepository tutorRepository;

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
    void getAdminPageTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/admin_scope/adminPage"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("admin/adminPage"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("tutorAssign"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("studentAssign"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("tutorStudent"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("tutorsBunch"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("studentsBunch"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("withoutTutorStudents"));
    }

    @Test
    void getAllTutorsListTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/admin_scope/tutors"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("tutor/allTutors"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("tutorsBunch"));
    }



    @Test
    void getNewTutorTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/admin_scope/tutor/new"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("tutor/new_tutor"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("createdTutor"));
    }

    @Test
    void getCertainTutorTest() throws Exception{
        Integer id = 1;
        when(tutorService.getCertainTutorById(id)).thenReturn(new Tutor());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/admin_scope/tutor/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("tutor/certain_tutor"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("foundTutor"));
    }

    @Test
     void editTutorTest() throws Exception{
        Integer id = 2;
        when(tutorService.getCertainTutorById(id)).thenReturn(new Tutor());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/admin_scope/2/edit"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("tutor/edit_tutorPage"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("editedTutor"));

    }

    @Test
    void createTutorTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/admin_scope/create"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    void updateTest()throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/admin_scope/1/edit"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    void deleteTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/admin_scope/1"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

}





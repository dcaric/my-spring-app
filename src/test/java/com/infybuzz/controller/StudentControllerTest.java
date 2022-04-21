package com.infybuzz.controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.context.ActiveProfiles;

/**
 * @author dcaric on 14/04/2022
 * @project spring-boot-app
 */
//@RunWith(SpringRunner.class)
@SpringBootTest(classes={com.infybuzz.app.SpringBootAppApplication.class})
@ActiveProfiles("dev")
@AutoConfigureMockMvc
class StudentControllerTest {

    //@InjectMocks
    //private StudentController studentController;

    @Autowired
    private MockMvc mockMvc;

    //@Mock
    //private StudentService studentService;

    @BeforeEach
    public void setupMethods() {
    }

    @Test
    void getAllStudents_successful() throws Exception {

        System.out.println("mockMvc: " + mockMvc);
        mockMvc.perform(get("/student/getAllStudents"))
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(25)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].firstName", is("dario2")));
    }

}

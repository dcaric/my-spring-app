package com.infybuzz.controller;
import com.infybuzz.entity.Student;
import com.infybuzz.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author dcaric on 14/04/2022
 * @project spring-boot-app
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes={com.infybuzz.app.SpringBootAppApplication.class})
@AutoConfigureMockMvc
class StudentControllerTest {

    @InjectMocks
    private StudentController studentController;

    @Autowired
    private MockMvc mockMvc;

    private static List<Student> studentList = new ArrayList<>();

    @Mock
    private StudentService studentService;

    @BeforeEach
    public void setupMethods() {
        Student RECORD_1 = new Student(33L, "dario1", "caric", "dario1.caric@gmail.com");
        Student RECORD_2 = new Student(34L, "dario2", "caric", "dario2.caric@gmail.com");
        studentList.add(RECORD_1);
        studentList.add(RECORD_2);

    }


    @Test
    void getAllStudents_successful_controller() throws Exception {

        when(studentService.getAllStudents()).thenReturn(studentList);

        System.out.println("mockMvc: " + mockMvc);
        mockMvc.perform(get("/student/getAllStudents"))
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].firstName", is("dario2")));


    }

}

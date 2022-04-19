package com.infybuzz.service;
import com.infybuzz.entity.Student;
import com.infybuzz.repository.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    List<Student> records = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        Student RECORD_1 = new Student(33L, "dario1", "caric", "dario1.caric@gmail.com");
        Student RECORD_2 = new Student(34L, "dario2", "caric", "dario2.caric@gmail.com");
        records.add(RECORD_1);
        records.add(RECORD_2);

        //MockitoAnnotations.initMocks(this);
        //mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        //MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    public void getAllStudents_successful_service() {

        // when findAll is called from repository
        when(studentRepository.findAll()).thenReturn(records);

        // start test and save result in studentList
        List<Student> studentList = studentService.getAllStudents();

        // test verification, some attributes etc.
        assertEquals(2, studentList.size());
        assertEquals("dario2", studentList.get(1).getFirstName());
        verify(studentRepository, times(1)).findAll();
    }


}
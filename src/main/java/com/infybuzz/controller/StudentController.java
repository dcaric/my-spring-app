package com.infybuzz.controller;

import com.infybuzz.entity.Student;
import com.infybuzz.entity.StudentTypes.StudentSimple;
import com.infybuzz.entity.StudentTypes.StudentSimpleClass;
import com.infybuzz.service.StudentService;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author dcaric on 10/04/2022
 * @project spring-boot-app
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/getAllStudents2/{name}")
    /*
    public ResponseEntity<List<StudentSimple>> getAllStudents2() {
        return new ResponseEntity<>(studentService.getAllStudents2(), HttpStatus.OK);
    }
    */
    public List<StudentSimple> getAllStudents2(@PathVariable String name) {
        return studentService.getAllStudents2(name);
    }
    @GetMapping("/getAllStudents")
    public List<Student> getAllStudents() {
        System.out.println("getAllStudents");

        Student RECORD_1 = new Student(33L, "dario1", "caric", "dario1.caric@gmail.com");
        Student RECORD_2 = new Student(34L, "dario2", "caric", "dario2.caric@gmail.com");
        List<Student> records = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2));
        System.out.println("All students: " + records);
        return records.size() > 0 ? records : List.of();

        //List<Student> allStudents = studentService.getAllStudents();

        //return allStudents.size() > 0 ? allStudents: List.of();
    }

    @GetMapping("/getById/{id}")
    public Student getById(@PathVariable Long id) throws DocumentException, IOException {
        return studentService.getById(id);
    }

    @GetMapping("/getPdf/{name}")
    public ResponseEntity<Resource> getPdf(@PathVariable String name) throws DocumentException, IOException {
        return studentService.getPdf(name);
    }

    @PostMapping("/create")
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping("/update")
    public Student updateStudent(@RequestBody Student student) {
        return studentService.updateStudent(student);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }
}

package com.infybuzz.controller;

import com.infybuzz.entity.Student;
import com.infybuzz.entity.StudentTypes.StudentSimple;
import com.infybuzz.Business.Admin.Authentication;
import com.infybuzz.service.StudentService;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import com.infybuzz.helperClasses.ExportToXls;
// jjwt token


/**
 * @author dcaric on 10/04/2022
 * @project spring-boot-app
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    private Authentication authentication = new Authentication();

    @GetMapping("/getAllStudents2/{name}")
    @Async
    public List<StudentSimple> getAllStudents2(@PathVariable String name) {
        return studentService.getAllStudents2(name);
    }


    @GetMapping("/cookie")
    public String readCookie(@CookieValue(value = "username", defaultValue = "Atta") String username) {
        System.out.println("cookie");

        return "Hey! My username is " + username;
    }

    @GetMapping("/getAllStudents")
    public List<Student> getAllStudents() {
        List<Student> allStudents = studentService.getAllStudents();
        System.out.println("getAllStudents:" + allStudents.size());
        return allStudents.size() > 0 ? allStudents: List.of();
    }

    @GetMapping("/getById/{id}")
    public Student getById(@PathVariable Long id) throws DocumentException, IOException {
        return studentService.getById(id);
    }

    @GetMapping("/getPdf/{name}")
    public ResponseEntity<Resource> getPdf(@PathVariable String name) throws DocumentException, IOException {
        return studentService.getPdf(name);
    }

    @GetMapping("/getXls/{name}")
    public void getXls(@PathVariable String name, HttpServletResponse response) throws DocumentException, IOException {

        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; file-name=student.xls";
        response.setHeader(headerKey, headerValue);

        List<StudentSimple> data = studentService.getAllStudents2(name);
        try {
            ExportToXls employeeExcelExporter = new ExportToXls(data);
            employeeExcelExporter.export(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/create")
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping("/update")
    public Student updateStudent(@RequestBody Student student) {
        return studentService.updateStudent(student);
    }

    @DeleteMapping("delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }
}
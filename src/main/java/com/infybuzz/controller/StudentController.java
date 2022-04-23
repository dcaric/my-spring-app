package com.infybuzz.controller;

import com.infybuzz.entity.Student;
import com.infybuzz.entity.StudentTypes.StudentSimple;
import com.infybuzz.service.StudentService;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

// jjwt token
import io.jsonwebtoken.Jwts;

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

    @PostMapping("/login")
    //@ResponseBody
    public ResponseEntity<?> login(@RequestBody String credentials, HttpServletResponse response) {

        System.out.println("LOGIN" + "\ncredentials:" + credentials + "\nresponse:" + response);
        // create a cookie
        //Cookie cookie = new Cookie("platform","mobile");

        // expires in 7 days
        //cookie.setMaxAge(7 * 24 * 60 * 60);

        // optional properties
        //cookie.setSecure(true);
        //cookie.setHttpOnly(true);
        //cookie.setPath("/");

        // add cookie to response
        //response.addCookie(cookie);
        //System.out.println("cookie: " + cookie);
        System.out.println("response: " + response);

        // TODO: add your login logic here
        String jwtTokenTemp = "NOT_AVAILABLE";
        //LocalDateTime now = LocalDateTime.now();
        //Date now = new Date();
        Calendar now = Calendar.getInstance();
        Calendar nowPlus100 = Calendar.getInstance();
        nowPlus100.add(Calendar.SECOND,100);

        now.add(Calendar.SECOND,111);

        String jwtToken = Jwts.builder()
                .claim("name", "etkcad")
                .claim("email", "dario.caric@ericsson.com")
                .setSubject("dario")
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(now.getTime())
                .setExpiration(nowPlus100.getTime())
                .compact();
        System.out.println("jwtToken: " + jwtToken);

        // return response entity
        return new ResponseEntity<>(jwtToken, HttpStatus.OK);
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

package com.infybuzz.controller;

import com.infybuzz.Business.Admin.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author dcaric on 24/04/2022
 * @project spring-boot-app
 */
@RestController
public class AuthController {

    private Authentication authentication = new Authentication();

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody String credentials, HttpServletResponse response) {
        return new ResponseEntity<>(authentication.handleLogin(credentials), HttpStatus.OK);
    }
}

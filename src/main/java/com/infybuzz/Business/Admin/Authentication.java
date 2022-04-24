package com.infybuzz.Business.Admin;

import io.jsonwebtoken.Jwts;

import java.io.Serializable;
import java.util.Calendar;
import java.util.UUID;

/**
 * @author dcaric on 24/04/2022
 * @project spring-boot-app
 */
public class Authentication implements Serializable {

    public String handleLogin(String credentials) {

        System.out.println("LOGIN" + "\ncredentials:" + credentials);

        String jwtToken = "no token generated";
        try {
            Calendar now = Calendar.getInstance();
            Calendar nowPlus100 = Calendar.getInstance();
            nowPlus100.add(Calendar.SECOND,100);
            now.add(Calendar.SECOND,111);
            jwtToken = Jwts.builder()
                    .claim("name", "etkcad")
                    .claim("email", "dario.caric@ericsson.com")
                    .setSubject("dario")
                    .setId(UUID.randomUUID().toString())
                    .setIssuedAt(now.getTime())
                    .setExpiration(nowPlus100.getTime())
                    .compact();
            System.out.println("jwtToken: " + jwtToken);
        } catch (Exception e) {
            System.out.println("ERROR" + e);
        }

        return jwtToken;
    }
}

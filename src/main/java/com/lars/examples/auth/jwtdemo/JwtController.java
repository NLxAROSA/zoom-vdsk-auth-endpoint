package com.lars.examples.auth.jwtdemo;

import javax.crypto.SecretKey;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


@RestController
public class JwtController {
    
    private static String SDK_KEY = "<yoursdkkey>";
    private static String SDK_SECRET = "<yoursdksecret>";
    private static int EXPIRATION_SECONDS=7200;
    private static int ROLE = 1;
    private static String SESSION_TOPIC = "<topicname>";
    private static String SESSION_KEY = "<keyname>";
    private static String USER_IDENTITY = "<username>";

    @PostMapping("/")
    public @ResponseBody JwtResponse getSignature(@RequestBody JwtRequest request) {

        Double iat = Math.floor(System.currentTimeMillis() / 1000);
        Double exp = iat + EXPIRATION_SECONDS;
        SecretKey key = Keys.hmacShaKeyFor(SDK_SECRET.getBytes());

        String signature = Jwts.builder().
        claim("app_key", SDK_KEY).
        claim("role_type", ROLE).
        claim("tpc",  SESSION_TOPIC).
        claim("version", 1).
        claim("iat",  iat).
        claim("exp",  exp).
        claim("user_identity",  USER_IDENTITY).
        claim("session_key",  SESSION_KEY).
        header().type("JWT").and().
        signWith(key).
        compact();

        return new JwtResponse(signature);
    }
    
}

package com.lars.examples.auth.jwtdemo;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@RestController
public class JwtController {

    private static final int EXPIRATION_SECONDS=7200; // Let token expire after 2 hours
    private static final String SESSION_KEY = "larstest";
    private static final String USER_IDENTITY = "JavaScript";

    private final String sdkKey; // provided at runtime via environment variable
    private final String sdkSecret; // provided at runtime via environment variable
    
    /**
     * Constructor. SDK key and secret are provided at runtime via environment variables.
     * See application.properties for the variables and their fallbacks
     * @param sdkKey your Zoom Video SDK key
     * @param sdkSecret your Zoom Video SDK secret
     */
    public JwtController(@Value("${zoom.videosdk.key}") String sdkKey, @Value("${zoom.videosdk.secret}") String sdkSecret)   {
        this.sdkKey = sdkKey;
        this.sdkSecret = sdkSecret;
    }

    /**
     * POST request that takes a request and generates a signed JWT.
     * @param request Request object containing session name and role
     * @return A signed JWT
     */
    @PostMapping("/")
    public @ResponseBody JwtResponse getSignature(@RequestBody JwtRequest request) {

        // Set instance time and expiration time
        Double iat = Math.floor(System.currentTimeMillis() / 1000);
        Double exp = iat + EXPIRATION_SECONDS;
        // Generate a HS256 key
        SecretKey key = Keys.hmacShaKeyFor(sdkSecret.getBytes());

        // Build payload and sign it. The 'alg' header will be added automatically by the JWT library
        String signature = Jwts.builder().
        claim("app_key", sdkKey).
        claim("role_type", request.getRole()).
        claim("tpc",  request.getSessionName()).
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

package com.lars.examples.auth.jwtdemo;

public class JwtResponse {
    private String signature;

    public JwtResponse(String signature) {
        this.signature = signature;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
    
}

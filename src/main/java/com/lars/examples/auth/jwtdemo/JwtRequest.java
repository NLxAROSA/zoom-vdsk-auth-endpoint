package com.lars.examples.auth.jwtdemo;

public class JwtRequest {
    private String sessionName;
    private String role;

    public JwtRequest(String sessionName, String role) {
        this.sessionName = sessionName;
        this.role = role;
    }
    public String getSessionName() {
        return sessionName;
    }
    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    
}

package com.lars.examples.auth.jwtdemo;

public class JwtRequest {
    private String sessionName;
    private int role;

    public JwtRequest(String sessionName, int role) {
        this.sessionName = sessionName;
        this.role = role;
    }
    public String getSessionName() {
        return sessionName;
    }
    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }
    public int getRole() {
        return role;
    }
    public void setRole(int role) {
        this.role = role;
    }
    
}

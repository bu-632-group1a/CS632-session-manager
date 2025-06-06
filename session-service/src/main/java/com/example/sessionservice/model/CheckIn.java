package com.example.sessionservice.model;

public class CheckIn {
    private String id;
    private String sessionCode;
    private String user;

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getSessionCode() { return sessionCode; }
    public void setSessionCode(String sessionCode) { this.sessionCode = sessionCode; }
    public String getUser() { return user; }
    public void setUser(String user) { this.user = user; }
}

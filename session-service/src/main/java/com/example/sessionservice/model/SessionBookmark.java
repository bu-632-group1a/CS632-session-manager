package com.example.sessionservice.model;

public class SessionBookmark {
    private String id;
    private String sessionCode;
    private String description;

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getSessionCode() { return sessionCode; }
    public void setSessionCode(String sessionCode) { this.sessionCode = sessionCode; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}

package tn.esprit.examen.nomPrenomClasseExamen.controllers;

import lombok.ToString;

import java.util.List;

public class EmailRequest {
    private List<String> emails;
    private String subject;
    private String message;
    private Long id;

    // Getters et setters
    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

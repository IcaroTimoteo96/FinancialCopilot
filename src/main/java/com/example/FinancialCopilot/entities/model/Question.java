package com.example.FinancialCopilot.entities.model;

public class Question {
    private long Id;
    private String question;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}

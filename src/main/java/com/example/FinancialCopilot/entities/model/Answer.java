package com.example.FinancialCopilot.entities.model;

public class Answer {
    private long Id;
    private String answer;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}

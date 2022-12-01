package com.example.nztaa2.Models;

public class QuizQuestion {

    private String id;
    private String question;
    private String[] quizAnswerIds;

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
    public String getQuestion() {
        return question;
    }
}

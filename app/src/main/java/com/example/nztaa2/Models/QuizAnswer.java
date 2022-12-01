package com.example.nztaa2.Models;

public class QuizAnswer {
    private String id;
    private String answer;
    private Boolean isCorrect;
    private String quizQuestionId;

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    public String getAnswer() {
        return answer;
    }

    public void setIsCorrect(String isCorrect) {
        this.isCorrect = Boolean.valueOf(isCorrect);
    }
    public Boolean getIsCorrect() {
        return isCorrect;
    }

    public void setQuizQuestionId(String quizQuestionId) {
        this.quizQuestionId = quizQuestionId;
    }
    public String getQuizQuestionId() {
        return quizQuestionId;
    }

}

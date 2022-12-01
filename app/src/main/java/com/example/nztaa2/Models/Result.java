package com.example.nztaa2.Models;

import java.util.Date;

public class Result {
    private String id;

    private Date timeCost;
    private Date initTime;
    private Date createDateTime;

    private int initAmount;
    private int finishedAmount;
    private int correctAmount;
    private int failAmount;

    private QuizQuestion[] quizQuestions;

    private Boolean isPassed;
}

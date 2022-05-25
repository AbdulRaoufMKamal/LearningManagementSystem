package com.example.lms;

public class Quizzes {

    private String courseName;
    private int quizNumber;

    public Quizzes(String courseName, int quizNumber) {
        this.courseName = courseName;
        this. quizNumber = quizNumber;
    }
    public int getQuizNumber() {
        return quizNumber;
    }

    public void setQuizNumber(int quizNumber) {
        this.quizNumber = quizNumber;
    }
}

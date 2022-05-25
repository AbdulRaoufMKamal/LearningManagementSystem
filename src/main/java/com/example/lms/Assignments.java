package com.example.lms;

public class Assignments {

    private String courseName;
    private int assignmentNumber;

    public Assignments(String courseName, int assignmentNumber) {
        this.courseName = courseName;
        this.assignmentNumber = assignmentNumber;
    }

    public int getAssignmentNumber() {
        return assignmentNumber;
    }

    public void setAssignmentNumber(int assignmentNumber) {
        this.assignmentNumber = assignmentNumber;
    }
}

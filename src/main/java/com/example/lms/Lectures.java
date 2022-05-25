package com.example.lms;

public class Lectures {
    private String lectureName;
    private int lectureNumber;

    public Lectures(String lectureName, int lectureNumber) {
        this.lectureName = lectureName;
        this.lectureNumber = lectureNumber;
    }

    public String getLectureName() {
        return lectureName;
    }

    public int getLectureNumber() {
        return lectureNumber;
    }

    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
    }

    public void setLectureNumber(int lectureNumber) {
        this.lectureNumber = lectureNumber;
    }
}

package com.codepathfinalprojectacademics.gpacalculator.models;

public class Course {
    private final String courseName;
    private final float grade;
    private final int credits;

    public Course(String courseName, float grade, int credits) {
        this.courseName = courseName;
        this.grade = grade;
        this.credits = credits;
    }

    public String getCourseName() {
        return courseName;
    }

    public float getGrade() {
        return grade;
    }

    public int getCredits() {
        return credits;
    }
}

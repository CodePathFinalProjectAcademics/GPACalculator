package com.codepathfinalprojectacademics.gpacalculator;

public class Section {
    private String section_name;
    private float worth;
    private float grade;

    public Section(String section_name, float worth, float grade) {
        this.section_name = section_name;
        this.worth = worth;
        this.grade = grade;
    }

    public String getSection_name() {
        return section_name;
    }

    public float getWorth() {
        return worth;
    }

    public float getGrade() {
        return grade;
    }
}

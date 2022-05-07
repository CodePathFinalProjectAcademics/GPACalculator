package com.codepathfinalprojectacademics.gpacalculator.models;

public class Section {
    private final String section_name;
    private final float worth;
    private final float grade;

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

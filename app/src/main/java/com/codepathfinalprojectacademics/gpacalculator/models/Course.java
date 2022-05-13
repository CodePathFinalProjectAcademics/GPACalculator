package com.codepathfinalprojectacademics.gpacalculator.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Course")
public class Course extends ParseObject {

    public static final String KEY_NAME = "name";
    public static final String KEY_GRADE = "grade";
    public static final String KEY_CREDITS = "credits";
    public static final String KEY_WEIGHT = "weight";
    public static final String KEY_USER = "user";
    public static final String KEY_LETTER_GRADE = "letterGrade";

    public String getName() {
        return getString(KEY_NAME);
    }

    public void setName(String name) {
        put(KEY_NAME, name);
    }

    public float getGrade() {
        return (float) getDouble(KEY_GRADE);
    }

    public void setGrade(float grade) {
        put(KEY_GRADE, grade);
    }

    public int getCredits() {
        return getInt(KEY_CREDITS);
    }

    public void setCredits(int credits) {
        put(KEY_CREDITS, credits);
    }

    public String getWeight() {
        return getString(KEY_WEIGHT);
    }

    public void setWeight(String weight) {
        put(KEY_WEIGHT, weight);
    }

    public ParseUser getUser() {
        return getParseUser(KEY_USER);
    }

    public void setUser(ParseUser user) {
        put(KEY_USER, user);
    }
}

package com.codepathfinalprojectacademics.gpacalculator.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Section")
public class Section extends ParseObject {
    private static final String KEY_NAME = "name";
    private static final String KEY_GRADE = "grade";
    private static final String KEY_PERCENTAGE = "percentage";
    private static final String KEY_USER = "user";


    public String getName() {
        return getString(KEY_NAME);
    }

    public void setName(String name) {
        put(KEY_NAME, name);
    }

    public float getPercentage() {
        return (float) getDouble(KEY_PERCENTAGE);
    }

    public void setPercentage(float percentage) {
        put(KEY_PERCENTAGE, percentage);
    }

    public ParseUser getUser() {
        return getParseUser(KEY_USER);
    }

    public void setUser(ParseUser user) {
        put(KEY_USER, user);
    }

    public float getGrade() {
        return (float) getDouble(KEY_GRADE);
    }

    public void setGrade(float grade) {
        put(KEY_GRADE, grade);
    }
}

package com.codepathfinalprojectacademics.gpacalculator;

import android.app.Application;

import com.parse.Parse;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("Ikgodfk8g7wSmkYCawLfDN4JEeFfZP3BQxP2nYtc")
                .clientKey("e5cGrqnYzslppDAjt6S6LxpyNjk4Xlj6QJaaOQER")
                .server("https://parseapi.back4app.com/")
                .build());
    }
}

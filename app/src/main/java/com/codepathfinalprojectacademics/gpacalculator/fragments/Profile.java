package com.codepathfinalprojectacademics.gpacalculator.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.codepathfinalprojectacademics.gpacalculator.LoginActivity;
import com.codepathfinalprojectacademics.gpacalculator.R;
import com.codepathfinalprojectacademics.gpacalculator.models.Course;
import com.codepathfinalprojectacademics.gpacalculator.models.Section;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class Profile extends Fragment {
    private Button logOutButton;

    private TextView userGPA;
    private TextView userName;

    public Profile() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        logOutButton = view.findViewById(R.id.logoutBtn);
        userGPA = view.findViewById(R.id.user_gpa);
        userName = view.findViewById(R.id.userName);

        userName.setText(ParseUser.getCurrentUser().getUsername());
        logOutButton.setOnClickListener(v -> ParseUser.logOutInBackground(e -> {
            if(e != null) return;

            // the user has been logged out
            Toast.makeText(getContext(), "Logged out successfully", Toast.LENGTH_SHORT).show();

            // return to the login activity
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        }));

        queryCourse();
    }

    private void queryCourse() {
        ParseQuery<Course> q = ParseQuery.getQuery(Course.class);
        q.include("user");

        // only look for the entries created by the current user
        q.whereEqualTo("user", ParseUser.getCurrentUser());

        // add them to the adapter
        q.findInBackground(new FindCallback<Course>() {
            @Override
            public void done(List<Course> objects, ParseException e) {
                if (e != null) {
                    System.out.println("Error with querying");
                    return;
                }

                float gpa = calculateGPA((ArrayList<Course>) objects);
                userGPA.setText(String.format("%.2f", gpa));
            }
        });
    }

    /**
     * Calculate the final GPA from the user
     * @param semester all the courses that the user have taken
     * @return current GPA of the user
     */
    private float calculateGPA(ArrayList<Course>semester) {
        // get these values from the user
        float currentGPA = 0f;
        int totalCreditEarned = 0;

        int totalCreditAttempted = totalCreditEarned;

        // get the current grade point from the user's past semesters
        float totalGradePoint = currentGPA * totalCreditEarned;

        for(int i = 0; i < semester.size(); i++) {
            Course course = semester.get(i);

            // count the credits in each course
            totalCreditAttempted += course.getCredits();

            // calculate the total grade point
            totalGradePoint += course.getCredits() * (float) course.getGrade();
        }

        // avoid division by zero error
        if(totalCreditAttempted == 0) {
            return 0f;
        }

        return totalGradePoint / totalCreditAttempted;
    }
}

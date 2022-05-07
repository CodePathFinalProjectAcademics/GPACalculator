package com.codepathfinalprojectacademics.gpacalculator.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.codepathfinalprojectacademics.gpacalculator.CreateAssignment;
import com.codepathfinalprojectacademics.gpacalculator.CreateCourse;
import com.codepathfinalprojectacademics.gpacalculator.models.Course;
import com.codepathfinalprojectacademics.gpacalculator.adapters.HomeAdapter;
import com.codepathfinalprojectacademics.gpacalculator.R;
import com.codepathfinalprojectacademics.gpacalculator.models.Section;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Home extends Fragment {
    private ArrayList<Course> classArrayList;
    private HomeAdapter adapter;

    private Button createCourseButton;

    /**
     * Convert the letter grades to credit earned
     */
    private static final Map<String, Float>gradeConversionTable = new HashMap<String, Float>() {{
        put("A+", 4.0f); put("A", 4.0f); put("A-", 3.7f);
        put("B+", 3.3f); put("B", 3.0f); put("B-", 2.7f);
        put("C+", 2.3f); put("C", 2.0f); put("C-", 1.7f);
        put("D+", 1.3f); put("D", 1.0f); put("D-", 0.7f);
        put("F", 0.0f);
    }};

    public Home() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        createCourseButton = rootView.findViewById(R.id.addCourseBtn);
        RecyclerView recyclerView = rootView.findViewById(R.id.rvHome);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        classArrayList = new ArrayList<>();

        adapter = new HomeAdapter(getContext(), classArrayList);
        recyclerView.setAdapter(adapter);

        createCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CreateCourse.class);
                startActivityForResult(intent, 2);
            }
        });
//        CreateDataForCards();

        adapter.notifyDataSetChanged();

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            String name = data.getStringExtra("name");
            String grade = data.getStringExtra("grade");
            String credits = data.getStringExtra("credits");

            Course course = new Course(name, Float.parseFloat(grade), Integer.parseInt(credits));

            classArrayList.add(course);
            adapter.notifyDataSetChanged();
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private void CreateDataForCards(){
        Course course = new Course("Math 391", 95, 4);
        classArrayList.add(course);

        course = new Course("FIQWS 10003", 90, 3);
        classArrayList.add(course);

        course = new Course("Phys 208", 85, 4);
        classArrayList.add(course);

        course = new Course("Math 346", 80, 4);
        classArrayList.add(course);

        adapter.notifyDataSetChanged();
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
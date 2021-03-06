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
import android.widget.TextView;
import android.widget.Toast;

import com.codepathfinalprojectacademics.gpacalculator.CreateAssignment;
import com.codepathfinalprojectacademics.gpacalculator.CreateCourse;
import com.codepathfinalprojectacademics.gpacalculator.models.Course;
import com.codepathfinalprojectacademics.gpacalculator.adapters.HomeAdapter;
import com.codepathfinalprojectacademics.gpacalculator.R;
import com.codepathfinalprojectacademics.gpacalculator.models.Section;
import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Home extends Fragment {
    private ArrayList<Course> classArrayList;
    private HomeAdapter adapter;

    private Button createCourseButton;
    private Button deleteAllButton;
    private TextView gpaHomeResultShow;

    public Home() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        createCourseButton = rootView.findViewById(R.id.addCourseBtn);
        deleteAllButton = rootView.findViewById(R.id.deleteAllCourseBtn);
        gpaHomeResultShow = rootView.findViewById(R.id.tvFinalGpaShow);
        RecyclerView recyclerView = rootView.findViewById(R.id.rvHome);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        classArrayList = new ArrayList<>();

        adapter = new HomeAdapter(getContext(), classArrayList);
        recyclerView.setAdapter(adapter);

        createCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ParseUser.getCurrentUser() == null) {
                    Toast.makeText(getContext(), "You must sign in to use this feature", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(getActivity(), CreateCourse.class);
                startActivityForResult(intent, 2);
            }
        });

        deleteAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<Course>q = ParseQuery.getQuery(Course.class);
                q.whereEqualTo("user", ParseUser.getCurrentUser());
                q.findInBackground(new FindCallback<Course>() {
                    @Override
                    public void done(List<Course> objects, ParseException e) {
                        if (e != null) {
                            return;
                        }

                        for (int i = 0; i < objects.size(); i++) {
                            objects.get(i).deleteInBackground(new DeleteCallback() {
                                @Override
                                public void done(ParseException e) {
                                    if (e != null) {
                                        System.out.println("Something went wrong");
                                    }
                                    adapter.notifyDataSetChanged();
                                }
                            });
                        }
                    }
                });
            }
        });

        // grab all data from parse
        query();
        gpaHomeResultShow.setText(String.format("%.2f", calculateGPA(classArrayList)));

        return rootView;
    }

    /**
     * Grab all data entries from parse
     */
    private void query() {
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

                classArrayList.addAll(objects);
                adapter.notifyDataSetChanged();
                gpaHomeResultShow.setText(String.format("%.2f", calculateGPA(classArrayList)));

            }
        });
    }


    /**
     * Grab the user entries from the creation activity
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            String name = data.getStringExtra("name");
            String grade = data.getStringExtra("grade");
            String credits = data.getStringExtra("credits");

            // save the course to parse and update the adapter
            saveCourse(name, Integer.parseInt(credits), Float.parseFloat(grade));
        }
    }

    private void saveCourse(String courseName, int credits, float grade) {
        Course course = new Course();
        course.setUser(ParseUser.getCurrentUser());
        course.setName(courseName);
        course.setCredits(credits);
        course.setGrade(grade);
        course.setWeight("");
        classArrayList.add(course);
        adapter.notifyDataSetChanged();
        gpaHomeResultShow.setText(String.format("%.2f", calculateGPA(classArrayList)));


        course.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e != null) {
                    System.out.println("Error while saving");
                    System.out.println(e.toString());
                    return;
                }

                Toast.makeText(getContext(), "Save Successfully", Toast.LENGTH_SHORT).show();
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
package com.codepathfinalprojectacademics.gpacalculator.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
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
import com.codepathfinalprojectacademics.gpacalculator.R;
import com.codepathfinalprojectacademics.gpacalculator.models.Section;
import com.codepathfinalprojectacademics.gpacalculator.adapters.ClassGPAAdapter;
import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

public class Classgpa extends Fragment {
    private ClassGPAAdapter adapter;
    private ArrayList<Section> sectionArrayList;
    private Context context;
    private TextView classGpaResult;

    private static final int ACTIVITY_REQ_CODE = 10;
    private Button addNewSectionButton;
    private Button deleteAllButton;

    public Classgpa() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_classgpa, container, false);

        addNewSectionButton = rootView.findViewById(R.id.addNewSectionBtn);
        deleteAllButton = rootView.findViewById(R.id.deleteSectionBtn);

        classGpaResult = rootView.findViewById(R.id.tvClassGradeShow);
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerViewCard);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        sectionArrayList = new ArrayList<>();

        adapter = new ClassGPAAdapter(context, sectionArrayList);
        recyclerView.setAdapter(adapter);

        addNewSectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAssignmentActivity();
            }
        });

        deleteAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<Section>q = ParseQuery.getQuery(Section.class);
                q.whereEqualTo("user", ParseUser.getCurrentUser());
                q.findInBackground(new FindCallback<Section>() {
                    @Override
                    public void done(List<Section> objects, ParseException e) {
                        if(e != null) {
                            return;
                        }

                        for(int i = 0; i < objects.size(); i++) {
                            objects.get(i).deleteInBackground(new DeleteCallback() {
                                @Override
                                public void done(ParseException e) {
                                    if(e != null) {
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
        classGpaResult.setText(String.format("%.2f", calculateGrade(sectionArrayList)));
        return rootView;
    }

    public void goToAssignmentActivity() {
        if(ParseUser.getCurrentUser() == null) {
            Toast.makeText(getContext(), "You must sign in to use this feature", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(getActivity(), CreateAssignment.class);
        startActivityForResult(intent, 1);
    }

    /**
     * Grab all data entries from parse
     */
    private void query() {
        ParseQuery<Section>q = ParseQuery.getQuery(Section.class);
        q.include("user");
        q.whereEqualTo("user", ParseUser.getCurrentUser());

        q.findInBackground(new FindCallback<Section>() {
            @Override
            public void done(List<Section> objects, ParseException e) {
                if(e != null) {
                    System.out.println("Error with querying");
                    return;
                }

                sectionArrayList.addAll(objects);
                adapter.notifyDataSetChanged();
//                classGpaResult.setText(Float.toString(calculateGrade(sectionArrayList)));
                classGpaResult.setText(String.format("%.2f", calculateGrade(sectionArrayList)));

            }
        });
    }


    /**
     * Grab the user entries from the creation activity
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            String name = data.getStringExtra("name");
            String worth = data.getStringExtra("worth");
            String grade = data.getStringExtra("grade");

            saveSection(name, Float.parseFloat(grade), Float.parseFloat(worth));
        }
    }

    private void saveSection(String sectionName, float grade, float percentage) {
        Section section = new Section();
        section.setUser(ParseUser.getCurrentUser());
        section.setName(sectionName);
        section.setPercentage(percentage);
        section.setGrade(grade);

        sectionArrayList.add(section);
        adapter.notifyDataSetChanged();
        classGpaResult.setText(String.format("%.2f", calculateGrade(sectionArrayList)));


        section.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e != null) {
                    System.out.println("Error while saving");
                    return;
                }

                Toast.makeText(getContext(), "Save Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * Calculate the grade for a class
     * @param sections list of all the grade distributions
     * @return the current grade for that class
     */
    private static float calculateGrade(ArrayList<Section>sections) {
        float currentGrade = 0f;

        for(int i = 0; i < sections.size(); i++) {
            Section section = sections.get(i);
            currentGrade += (section.getPercentage() / 100) * section.getGrade();
        }

        return currentGrade;
    }
}
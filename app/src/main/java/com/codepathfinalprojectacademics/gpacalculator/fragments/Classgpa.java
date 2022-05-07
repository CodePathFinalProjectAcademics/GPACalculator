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

import com.codepathfinalprojectacademics.gpacalculator.CreateAssignment;
import com.codepathfinalprojectacademics.gpacalculator.R;
import com.codepathfinalprojectacademics.gpacalculator.models.Section;
import com.codepathfinalprojectacademics.gpacalculator.adapters.ClassGPAAdapter;

import java.util.ArrayList;

public class Classgpa extends Fragment {
    private ClassGPAAdapter adapter;
    private ArrayList<Section> sectionArrayList;
    private Context context;

    private static final int ACTIVITY_REQ_CODE = 10;
    private Button addNewSectionButton;

    public Classgpa() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_classgpa, container, false);

        addNewSectionButton = rootView.findViewById(R.id.addNewSectionBtn);
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerViewCard);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        sectionArrayList = new ArrayList<>();

        adapter = new ClassGPAAdapter(context, sectionArrayList);
        recyclerView.setAdapter(adapter);

//        CreateDataForCards();

        addNewSectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAssignmentActivity();
            }
        });

        return rootView;
    }
    @SuppressLint("NotifyDataSetChanged")
    private void CreateDataForCards(){
        Section section = new Section("Exams", 50, 80);
        sectionArrayList.add(section);

        section = new Section("Homework", 20, 95);
        sectionArrayList.add(section);

        section = new Section("Attendence", 10, 100);
        sectionArrayList.add(section);

        section = new Section("Labs", 10, 97);
        sectionArrayList.add(section);

        adapter.notifyDataSetChanged();
    }

    public void goToAssignmentActivity() {
        Intent intent = new Intent(getActivity(), CreateAssignment.class);
        startActivityForResult(intent, 1);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            String name = data.getStringExtra("name");
            String worth = data.getStringExtra("worth");
            String grade = data.getStringExtra("grade");

            Section section = new Section(name, Float.parseFloat(worth), Float.parseFloat(grade));

            sectionArrayList.add(section);
            adapter.notifyDataSetChanged();
        }
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
            currentGrade += section.getWorth() * section.getGrade();
        }

        return currentGrade;
    }
}
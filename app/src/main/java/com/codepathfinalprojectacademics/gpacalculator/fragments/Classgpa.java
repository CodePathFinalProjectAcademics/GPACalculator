package com.codepathfinalprojectacademics.gpacalculator.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.codepathfinalprojectacademics.gpacalculator.AssigmentActivity;
import com.codepathfinalprojectacademics.gpacalculator.R;
import com.codepathfinalprojectacademics.gpacalculator.Section;
import com.codepathfinalprojectacademics.gpacalculator.adapters.ClassGPAAdapter;

import java.util.ArrayList;

public class Classgpa extends Fragment {
    String[] assignments = {};

    public Classgpa() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_classgpa, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(new ClassGPAAdapter(assignments));

        Button addAssigments = view.findViewById(R.id.addAssigmentsBtn);
        addAssigments.setOnClickListener(v -> goToAssigmentActivity());

        return view;
    }

    public void goToAssigmentActivity() {
        Intent intent = new Intent(getActivity(), AssigmentActivity.class);
        startActivity(intent);
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
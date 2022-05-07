package com.codepathfinalprojectacademics.gpacalculator.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codepathfinalprojectacademics.gpacalculator.R;
import com.codepathfinalprojectacademics.gpacalculator.models.Section;
import com.codepathfinalprojectacademics.gpacalculator.adapters.ClassGPAAdapter;

import java.util.ArrayList;

public class Classgpa extends Fragment {
    private ClassGPAAdapter adapter;
    private ArrayList<Section> sectionArrayList;
    private Context context;

    public Classgpa() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_classgpa, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerViewCard);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        sectionArrayList = new ArrayList<>();

        adapter = new ClassGPAAdapter(context, sectionArrayList);
        recyclerView.setAdapter(adapter);

        CreateDataForCards();

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

//    public void goToAssigmentActivity() {
//        Intent intent = new Intent(getActivity(), AssigmentActivity.class);
//        startActivity(intent);
//    }

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
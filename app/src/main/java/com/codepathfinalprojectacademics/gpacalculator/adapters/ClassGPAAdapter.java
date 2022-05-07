package com.codepathfinalprojectacademics.gpacalculator.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codepathfinalprojectacademics.gpacalculator.R;
import com.codepathfinalprojectacademics.gpacalculator.models.Section;

import java.util.ArrayList;

public class ClassGPAAdapter extends RecyclerView.Adapter<ClassGPAAdapter.ClassGPAHolder> {
    private Context context;
    private ArrayList<Section> assigments;

    public ClassGPAAdapter(Context context, ArrayList<Section> assigments) {
        this.context = context;
        this.assigments = assigments;
    }

    @NonNull
    @Override
    public ClassGPAHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.class_gpa, parent, false);

        return new ClassGPAHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassGPAHolder holder, int position) {
        Section assignment = assigments.get(position);

        holder.setDetails(assignment);
    }

    @Override
    public int getItemCount() {
        return assigments.size();
    }

    class ClassGPAHolder extends RecyclerView.ViewHolder{
        TextView txtAssigmentNameCard, txtWeightCard, txtGradeCard;

        ClassGPAHolder(View itemView){
            super(itemView);

            txtAssigmentNameCard = itemView.findViewById(R.id.tvsection);
            txtWeightCard = itemView.findViewById(R.id.tvworth);
            txtGradeCard = itemView.findViewById(R.id.tvscore);
        }

        @SuppressLint("SetTextI18n")
        void setDetails(Section assignment){
            txtAssigmentNameCard.setText(assignment.getSection_name());
            txtWeightCard.setText(Integer.toString((int) assignment.getWorth()));
            txtGradeCard.setText(Integer.toString((int) assignment.getGrade()));
        }
    }
}

package com.codepathfinalprojectacademics.gpacalculator.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codepathfinalprojectacademics.gpacalculator.R;
import com.codepathfinalprojectacademics.gpacalculator.models.Course;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private Context context;
    private final ArrayList<Course> course;

    public HomeAdapter(Context context, ArrayList<Course> semester) {
        this.context = context;
        this.course = semester;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gpa_home, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Course semester = course.get(position);

        holder.setDetails(semester);
    }

    @Override
    public int getItemCount() {
        return course.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtCourseName, txtGrade, txtCredit;

        ViewHolder(View itemView){
            super(itemView);

            txtCourseName = itemView.findViewById(R.id.tvsection);
            txtGrade = itemView.findViewById(R.id.tvscore);
            txtCredit = itemView.findViewById(R.id.tvcredit);
        }

        @SuppressLint("SetTextI18n")
        void setDetails(Course course){
            txtCourseName.setText(course.getCourseName());
            txtGrade.setText(Integer.toString((int) course.getGrade()));
            txtCredit.setText(Integer.toString((int) course.getCredits()));
        }
    }
}

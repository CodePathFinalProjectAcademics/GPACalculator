package com.codepathfinalprojectacademics.gpacalculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private Context context;
    private List<Semesters> semester;

    public HomeAdapter(Context context, List<Semesters> semester) {
        this.context = this.context;
        this.semester = semester;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(context).inflate(R.layout.gpa_home, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Semesters semesters = semester.get(position);
        holder.bind(semesters);

    }

    @Override
    public int getItemCount() {
        return semester.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvclass;
        private TextView tvgrade;
        private TextView tvcredit;
        private TextView tvwiegth;
        private TextView tvgpa_resulr;
        private TextView tvgpa_question;
        private Button btngpa_add;
        private ImageView imvgpa;


        public ViewHolder(@NonNull View itemView){
            super(itemView);
            tvclass = itemView.findViewById(R.id.tvsection);
            tvgrade = itemView.findViewById(R.id.tvscore);
            tvcredit = itemView.findViewById(R.id.tvcredit);
            tvwiegth = itemView.findViewById(R.id.tvworth);
            tvgpa_resulr = itemView.findViewById(R.id.tvgpa_result);
            tvgpa_question = itemView.findViewById(R.id.tvgpa_question);
            btngpa_add = itemView.findViewById(R.id.btngpa_add);
            imvgpa = itemView.findViewById(R.id.imvgpa);

        }

        public void bind(Semesters semesters) {
           //tvclass.setText(semesters.);

        }
    }
}

package com.codepathfinalprojectacademics.gpacalculator.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codepathfinalprojectacademics.gpacalculator.R;

public class ClassGPAAdapter extends RecyclerView.Adapter<ClassGPAAdapter.MyViewHolder> {
    String[] list;

    public ClassGPAAdapter(String[] list){
        this.list = list;
    }

    @NonNull
    @Override
    public ClassGPAAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.classgpa_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(list[position]);
    }

    @Override
    public int getItemCount() {
        return list.length;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.assigmentNameText);
        }
    }
}

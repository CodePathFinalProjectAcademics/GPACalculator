package com.codepathfinalprojectacademics.gpacalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codepathfinalprojectacademics.gpacalculator.adapters.ClassGPAAdapter;
import com.codepathfinalprojectacademics.gpacalculator.fragments.Classgpa;

public class AssigmentActivity extends AppCompatActivity {
    EditText assigmentText;
    EditText weightText;
    EditText gradeText;
    Button addAssigmentBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assigment);

        assigmentText = findViewById(R.id.assigmentNameET);
        weightText = findViewById(R.id.weightET);
        gradeText = findViewById(R.id.assigmentNameET);
        addAssigmentBtn = findViewById(R.id.addAssigmentBtn);

        addAssigmentBtn.setOnClickListener(v -> {
            if(v.getId() == R.id.addAssigmentBtn){
                sendData();
                getSupportFragmentManager().beginTransaction().replace(R.id.recyclerView, new Classgpa()).commit();
            }

            Toast.makeText(this, "Sucessfully added a class!", Toast.LENGTH_SHORT).show();
        });
    }

    public void sendData(){
        Bundle bundle = new Bundle();
        bundle.putString("ASSIGMENT_NAME", assigmentText.getText().toString());

        Classgpa classgpa = new Classgpa();

        classgpa.setArguments(bundle);
        assigmentText.setText("");
        getSupportFragmentManager().beginTransaction().replace(R.id.linearLayout, classgpa).commit();
    }
}
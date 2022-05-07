package com.codepathfinalprojectacademics.gpacalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codepathfinalprojectacademics.gpacalculator.fragments.Classgpa;

public class CreateAssignment extends AppCompatActivity {

    private TextView sectionNameText;
    private TextView worthText;
    private TextView gradeText;

    private EditText inputSectionName;
    private EditText inputWorth;
    private EditText inputGrade;

    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_assignemnt);

        sectionNameText = findViewById(R.id.createSectionName);
        worthText = findViewById(R.id.createWorthText);
        gradeText = findViewById(R.id.createGradeText);

        inputSectionName = findViewById(R.id.sectionNameInput);
        inputWorth = findViewById(R.id.worthInput);
        inputGrade = findViewById(R.id.gradeInput);

        submitButton = findViewById(R.id.submitClassAvgBtn);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sectionName = inputSectionName.getText().toString();
                String worth = inputWorth.getText().toString();
                String grade = inputGrade.getText().toString();

                try {
                    Float.parseFloat(worth);
                } catch (Exception e) {
                    Toast.makeText(CreateAssignment.this, "Worth must be a percentage", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    Float.parseFloat(grade);
                } catch (Exception e) {
                    Toast.makeText(CreateAssignment.this, "Grade must be a number", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent();
                intent.putExtra("name", sectionName);
                intent.putExtra("worth", worth);
                intent.putExtra("grade", grade);

                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
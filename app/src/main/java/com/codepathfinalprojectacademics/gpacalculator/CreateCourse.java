package com.codepathfinalprojectacademics.gpacalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class CreateCourse extends AppCompatActivity {
    private TextView courseNameText;
    private TextView creditText;
    private TextView gradeText;

    private EditText inputCourseName;
    private EditText inputCredits;
    private EditText inputGrade;

    private Button submitButton;

    private Spinner letterGradeSpinner;
    public String letterGradeSelected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_course);

        courseNameText = findViewById(R.id.createCourseName);
        creditText = findViewById(R.id.createCreditText);
        gradeText = findViewById(R.id.createGradeTextCourse);

        inputCourseName = findViewById(R.id.courseNameInput);
        inputCredits = findViewById(R.id.creditsInput);
        inputGrade = findViewById(R.id.gradeInputCourse);

        letterGradeSpinner = findViewById(R.id.spinnerGradeLetter);

        submitButton = findViewById(R.id.submitCourseBtn);

    // Spinner Stuff
        ArrayAdapter<String> gradeAdapter = new ArrayAdapter<String>(CreateCourse.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.courseGradesLetters));

        gradeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        letterGradeSpinner.setAdapter(gradeAdapter);

    // Saves the selection of the spinner
        letterGradeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                letterGradeSelected = parent.getItemAtPosition(position).toString();
                Toast.makeText(CreateCourse.this, letterGradeSelected, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String courseName = inputCourseName.getText().toString();
                String credits = inputCredits.getText().toString();
                String grade = inputGrade.getText().toString();

                try {
                    Integer.parseInt(credits);
                } catch (Exception e) {
                    Toast.makeText(CreateCourse.this, "Credits must be a number", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    Float.parseFloat(grade);
                } catch (Exception e) {
                    Toast.makeText(CreateCourse.this, "Grade must be a number", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent();
                intent.putExtra("name", courseName);
                intent.putExtra("credits", credits);
                intent.putExtra("grade", grade);

                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
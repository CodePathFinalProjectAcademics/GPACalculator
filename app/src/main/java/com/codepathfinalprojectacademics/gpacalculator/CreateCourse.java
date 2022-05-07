package com.codepathfinalprojectacademics.gpacalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CreateCourse extends AppCompatActivity {
    private TextView courseNameText;
    private TextView creditText;
    private TextView gradeText;

    private EditText inputCourseName;
    private EditText inputCredits;
    private EditText inputGrade;

    private Button submitButton;
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

        submitButton = findViewById(R.id.submitCourseBtn);

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
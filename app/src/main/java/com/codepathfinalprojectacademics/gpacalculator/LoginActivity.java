package com.codepathfinalprojectacademics.gpacalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private Button guestModeButton;
    private Button logInButton;
    private Button signUpButton;

    private EditText usernameInputText;
    private EditText passwordInputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        guestModeButton = findViewById(R.id.guestModeBtn);
        logInButton = findViewById(R.id.logInBtn);
        signUpButton = findViewById(R.id.signUpBtn);

        usernameInputText = findViewById(R.id.inputUsername);
        passwordInputText = findViewById(R.id.inputPassword);

        guestModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainActivity();
            }
        });
    }

    public void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
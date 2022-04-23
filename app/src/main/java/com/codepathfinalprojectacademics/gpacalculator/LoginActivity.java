package com.codepathfinalprojectacademics.gpacalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

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

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameInputText.getText().toString();
                String password = passwordInputText.getText().toString();
                userLogIn(username, password);
            }
        });
    }

    public void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Log the user in to the app
     */
    private void userLogIn(String username, String password) {
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e != null) {
                    return;
                }

                goToMainActivity();
                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
package com.codepathfinalprojectacademics.gpacalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

        if(ParseUser.getCurrentUser() != null) goToMainActivity();

        guestModeButton = findViewById(R.id.guestModeBtn);
        logInButton = findViewById(R.id.logoutBtn);
        signUpButton = findViewById(R.id.signUpBtn);

        usernameInputText = findViewById(R.id.inputUsername);
        passwordInputText = findViewById(R.id.inputPassword);

        guestModeButton.setOnClickListener(v -> goToMainActivity());

        logInButton.setOnClickListener(v -> {
            String username = usernameInputText.getText().toString();
            String password = passwordInputText.getText().toString();
            userLogIn(username, password);
        });

        signUpButton.setOnClickListener(v -> {
            String username = usernameInputText.getText().toString();
            String password = passwordInputText.getText().toString();

            userSignUp(username, password);
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
        ParseUser.logInInBackground(username, password, (user, e) -> {
            if(e != null) {
                Toast.makeText(LoginActivity.this, "Login Unsuccessful", Toast.LENGTH_SHORT).show();
                return;
            }

            goToMainActivity();
            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
        });
    }

    private void userSignUp(String username, String password) {
        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setPassword(password);

        user.signUpInBackground(e -> {
            if (e == null) {
                userLogIn(username, password);

            } else {
                Toast.makeText(LoginActivity.this, "Cannot Sign Up", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
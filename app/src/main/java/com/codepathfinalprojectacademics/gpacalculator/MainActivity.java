package com.codepathfinalprojectacademics.gpacalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.codepathfinalprojectacademics.gpacalculator.databinding.ActivityMainBinding;
import com.codepathfinalprojectacademics.gpacalculator.fragments.Classgpa;
import com.codepathfinalprojectacademics.gpacalculator.fragments.Home;
import com.codepathfinalprojectacademics.gpacalculator.fragments.Profile;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new Home());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch(item.getItemId()){
                case R.id.home:
                    Toast.makeText(this, "Switched to Home Fragment", Toast.LENGTH_SHORT).show();
                    replaceFragment(new Home());
                    break;
                case R.id.classgpa:
                    Toast.makeText(this, "Switched to ClassGPA Fragment", Toast.LENGTH_SHORT).show();
                    replaceFragment(new Classgpa());
                    break;
                case R.id.profile:
                    if(ParseUser.getCurrentUser() == null) {
                        Toast.makeText(this, "Please log in to see your profile", Toast.LENGTH_SHORT).show();
                        break;
                    }

                    Toast.makeText(this, "Switched to Profile Fragment", Toast.LENGTH_SHORT).show();
                    replaceFragment(new Profile());
                    break;
            }

            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}
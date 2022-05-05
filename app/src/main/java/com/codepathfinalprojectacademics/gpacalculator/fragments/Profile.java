package com.codepathfinalprojectacademics.gpacalculator.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.codepathfinalprojectacademics.gpacalculator.LoginActivity;
import com.codepathfinalprojectacademics.gpacalculator.R;
import com.parse.ParseUser;

public class Profile extends Fragment {
    private Button logOutButton;
    public Profile() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        logOutButton = view.findViewById(R.id.logoutBtn);
        logOutButton.setOnClickListener(v -> ParseUser.logOutInBackground(e -> {
            if(e != null) return;

            // the user has been logged out
            Toast.makeText(getContext(), "Logged out successfully", Toast.LENGTH_SHORT).show();

            // return to the login activity
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        }));
    }
}

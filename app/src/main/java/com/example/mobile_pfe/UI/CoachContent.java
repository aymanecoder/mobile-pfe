package com.example.mobile_pfe.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.mobile_pfe.R;

public class CoachContent extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coach_content);

        ScrollView scrollView = findViewById(R.id.scrollView);
        scrollView.setScrollbarFadingEnabled(true);

        ConstraintLayout workout = findViewById(R.id.coach_workout);
        workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CoachContent.this, CoachWorkout.class);
                startActivity(intent);

            }
        });
    }

}
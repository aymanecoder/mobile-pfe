package com.example.mobile_pfe.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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

        TextView back = findViewById(R.id.Back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CoachContent.this, FindCoaches.class);
                startActivity(intent);

            }
        });

        ImageView workout = findViewById(R.id.workout_image);
        ImageView nutrition = findViewById(R.id.nutrition_image);

        workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CoachContent.this, CoachWorkout.class);
                startActivity(intent);

            }
        });

        ImageView videos = findViewById(R.id.videos_image);
        videos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CoachContent.this, CoachVideos.class);
                startActivity(intent);

            }
        });
    }

}
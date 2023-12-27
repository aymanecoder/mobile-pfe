package com.example.mobile_pfe.CoachPlanningActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile_pfe.GroupChatActivity.GroupChatProfileActivity;
import com.example.mobile_pfe.MainActivity;
import com.example.mobile_pfe.R;





public class CoachPlanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planing1);
        TextView escape_key = findViewById(R.id.back);
        ImageView imageView = findViewById(R.id.imageView10);

        // Set click listener for the ImageView
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click event here
                navigateToGroupChatProfileActivity();
            }
        });
        // Set click listener for the TextView (escapeKey)
        escape_key.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToMainActivity();

            }
        });
    }

    private void navigateToGroupChatProfileActivity() {
        // Create an Intent to start the GroupChatProfileActivity
        Intent intent = new Intent(this, GroupChatProfileActivity.class);

        // Add any data you need to pass to the new activity

        // Start the activity
        startActivity(intent);
        finish();
    }
    private void navigateToMainActivity() {
        // Create an Intent to start the GroupChatProfileActivity
        Intent intent = new Intent(this, MainActivity.class);

        // Add any data you need to pass to the new activity

        // Start the activity
        startActivity(intent);
        finish();
    }
}

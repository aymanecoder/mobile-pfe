package com.example.mobile_pfe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.mobile_pfe.ResultActivity.ResultActivity;
import com.example.mobile_pfe.TeamActivity.TeamActivity;
import com.example.mobile_pfe.loginActivities.login;
import com.example.mobile_pfe.matchActivities.ShowMatches;
import com.example.mobile_pfe.profileActivities.metricprofil;
import com.example.mobile_pfe.registerActivities.RegisterActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Use the correct layout resource ID

        // Assuming this code is in your activity or fragment
        RelativeLayout matchsLayout = findViewById(R.id.matchs);

        matchsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event, e.g., start the ShowMatches activity
                Intent intent = new Intent(MainActivity.this, ShowMatches.class);
                startActivity(intent);
            }
        });

        RelativeLayout teamsLayout = findViewById(R.id.teams);

        teamsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event, e.g., start the ShowMatches activity
                Intent intent = new Intent(MainActivity.this, TeamActivity.class);
                startActivity(intent);
            }
        });

        // Find the user_image ImageView by its ID
        ImageView userImage = findViewById(R.id.user_image);

        // Set an OnClickListener for the user_image ImageView
        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event, e.g., start a new activity or perform an action
                // Replace ExampleActivity.class with the actual activity you want to start
                Intent intent = new Intent(MainActivity.this, metricprofil.class);
                startActivity(intent);
            }
        });
    }

    // ... Rest of your code
}

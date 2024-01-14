package com.example.mobile_pfe.profileActivities;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile_pfe.R;

public class metricprofil extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_metrics); // Replace with the actual layout file

        Button iconGear = findViewById(R.id.icon_gear_);

        iconGear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event
                redirectToProfile();
            }
        });
    }

    private void redirectToProfile() {
        // Use Intent to navigate to the profile activity or fragment
        Intent intent = new Intent(this, Profil.class); // Replace with your actual profile activity
        startActivity(intent);
    }
}


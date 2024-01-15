package com.example.mobile_pfe.profileActivities;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.mobile_pfe.model.Profile;
import com.example.mobile_pfe.R;

public class metricprofil extends AppCompatActivity {
    private Profile receivedProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_metrics); // Replace with the actual layout file

        // Inside the receiving activity's onCreate or wherever you handle the Intent
// Inside the receiving activity's onCreate or wherever you handle the Intent
        receivedProfile = (Profile) getIntent().getSerializableExtra("profile");

// Check for null to avoid NullPointerException
        if (receivedProfile != null) {
            // Now you can access the profile's properties
            String firstName = receivedProfile.getFirstName();
            String lastName = receivedProfile.getLastName();
            String picturePath = receivedProfile.getPicturePath();

            ImageView profileImageView = findViewById(R.id.profilePicture);

            // Set values to TextViews
//            firstNameTextView.setText(firstName);
//            lastNameTextView.setText(lastName);

            // Load user image with Glide into the ImageView
            Glide.with(this)
                    .load(picturePath)
                    .placeholder(R.drawable.notfound)
                    .error(R.drawable.notfound)
                    .into(profileImageView);
        }

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

        Intent intent = new Intent(metricprofil.this, ProfilActivity.class); // Replace with your actual profile activity
        intent.putExtra("profile", receivedProfile);
        startActivity(intent);
    }
}


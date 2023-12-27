package com.example.mobile_pfe.GroupChatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile_pfe.R;

public class GroupChatProfileActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groupe);
        ImageView imageView = findViewById(R.id.escape_key);
        View starredMessagesView = findViewById(R.id.starred_messages);
        View mediaView = findViewById(R.id.media);

        // Set click listener for the View
        starredMessagesView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click event here
                navigateToStarredMessagesActivity();
            }
        });
        // Set click listener for the View
        mediaView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click event here
                navigateToMediaMessagesActivity();
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click event here
                navigateToGroupChatActivity();
            }
        });
    }
    private void navigateToGroupChatActivity() {
        // Create an Intent to start the GroupChatProfileActivity
        Intent intent = new Intent(this, GroupChatActivity.class);

        // Add any data you need to pass to the new activity

        // Start the activity
        startActivity(intent);
    }
    private void navigateToStarredMessagesActivity() {
        // Create an Intent to start the StarredMessagesActivity
        Intent intent = new Intent(this, StarredMessagesActivity.class);

        // Add any data you need to pass to the new activity

        // Start the activity
        startActivity(intent);
    }
    private void navigateToMediaMessagesActivity() {
        // Create an Intent to start the GroupChatProfileActivity
        Intent intent = new Intent(this, com.example.mobile_pfe.GroupChatActivity.MediaMessagesActivity.class);

        // Add any data you need to pass to the new activity

        // Start the activity
        startActivity(intent);
    }
}

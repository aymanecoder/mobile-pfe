package com.example.mobile_pfe.GroupChatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile_pfe.R;

public class StarredMessagesActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starred_messages);
        ImageView imageView = findViewById(R.id.escape_key);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click event here
                navigateToGroupChatProfileActivity();
            }
        });

    }
    private void navigateToGroupChatProfileActivity() {
        // Create an Intent to start the StarredMessagesActivity
        Intent intent = new Intent(this, GroupChatProfileActivity.class);

        // Add any data you need to pass to the new activity

        // Start the activity
        startActivity(intent);
    }
}

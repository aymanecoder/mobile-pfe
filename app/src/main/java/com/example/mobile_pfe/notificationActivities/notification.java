package com.example.mobile_pfe.notificationActivities;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile_pfe.R;

public class notification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification);

        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null) {
            String screen = intent.getExtras().getString("screen");
            // Handle the screen data as needed
            // For example, open a specific activity or fragment based on the "screen" value
        }
    }
}




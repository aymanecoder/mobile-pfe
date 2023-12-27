package com.example.mobile_pfe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mobile_pfe.ResultActivity.ResultActivity;
import com.example.mobile_pfe.TeamActivity.TeamActivity;
import com.example.mobile_pfe.loginActivities.login;
import com.example.mobile_pfe.matchActivities.ShowMatches;
import com.example.mobile_pfe.registerActivities.RegisterActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView equipeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Use the correct layout resource ID
        startSecondActivity();
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

    }
    private void startSecondActivity() {
        // Create an Intent to start SecondActivity
        Intent intent = new Intent(this, ResultActivity.class);

        // Optionally, you can pass data to the second activity using Intent extras
        // intent.putExtra("key", "value");

        // Start the SecondActivity
        startActivity(intent);

}

    public void onRegisterButtonClick(View view) {
        // Handle the button click
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
    public void onLoginButtonClick(View view) {
        // Handle the button click
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }


}
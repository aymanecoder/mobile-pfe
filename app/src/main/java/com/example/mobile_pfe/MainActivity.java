package com.example.mobile_pfe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.mobile_pfe.R;
import com.example.mobile_pfe.model.Globals.AppGlobals;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView equipeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        equipeTextView = findViewById(R.id.equipeTextView);

        // Get the access token from your global variable or wherever you store it
        String accessToken = AppGlobals.getAccessToken();

        // Check if the token is not null or empty before setting it to the TextView
        if (accessToken != null && !accessToken.isEmpty()) {
            equipeTextView.setText("Token: " + accessToken);
        } else {
            equipeTextView.setText("Token not available");
        }
    }
}
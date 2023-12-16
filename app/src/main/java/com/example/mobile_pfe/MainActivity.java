package com.example.mobile_pfe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.mobile_pfe.loginActivities.login;
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
        setContentView(R.layout.activity_chat_41); // Use the correct layout resource ID

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

    private String fetchEquipeData() {
        String result = null;
        HttpURLConnection urlConnection = null;

        try {
            // Replace "your-server-url" with the actual URL of your Spring Boot server
            URL url = new URL("http:/192.168.0.101:8080/api/v1/equipes");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            // Read the response
            InputStream inputStream = urlConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            result = stringBuilder.toString();

        } catch (IOException e) {
            Log.e(TAG, "Error fetching Equipe: " + e.getMessage());
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return result;
    }

    private void updateEquipeTextView(String result) {
        // Update the TextView with the response
        if (result != null) {
            try {
                JSONArray jsonArray = new JSONArray(result);
                if (jsonArray.length() > 0) {
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    String payload = jsonObject.optString("payload");
                    String body = jsonObject.optString("body");
                    equipeTextView.setText("Payload: " + payload + "\nBody: " + body);
                }
            } catch (JSONException e) {
                Log.e(TAG, "Error parsing JSON: " + e.getMessage());
            }
        }
    }
}
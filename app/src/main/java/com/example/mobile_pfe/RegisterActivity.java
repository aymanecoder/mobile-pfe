package com.example.mobile_pfe;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    private EditText etName, etEmail, etPassword, etRepassword;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ativity_register);
        ImageView cancelOutButton = findViewById(R.id.cancelOutButton);
        cancelOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event
                navigateToStarterScreen();
            }
        });
        etName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        etRepassword = findViewById(R.id.et_repassword);

        Button signupButton = findViewById(R.id.signupButton);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the method to make the POST request
                makeSignUpRequest();
            }
        });
    }
    private void navigateToStarterScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        // Optionally, you can finish the current activity if you don't want to go back to it
        // finish();
    }
    private void makeSignUpRequest() {
        String url = getString(R.string.http_localhost_8080_signup); // Replace with your backend API endpoint

        // Get user input from EditText fields
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String repassword = etRepassword.getText().toString().trim();
        System.out.printf("%s %s %s %s", repassword, password, email, name);
        // Create JSON object with user data
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("name", name);
            jsonBody.put("email", email);
            jsonBody.put("password", password);
            jsonBody.put("repassword", repassword);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Make a POST request using Volley
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle the response from the backend
                        try {
                            String message = response.getString("message");
                            Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle errors
                        Toast.makeText(RegisterActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );

        // Add the request to the Volley request queue
        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }
}

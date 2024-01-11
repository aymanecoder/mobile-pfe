package com.example.mobile_pfe.registerActivities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.compose.ui.semantics.Role;

import com.example.mobile_pfe.Model.Globals.AppGlobals;
import com.example.mobile_pfe.R;
import com.example.mobile_pfe.loginActivities.login;
import com.example.mobile_pfe.Model.Register.RegisterResponse;
import com.example.mobile_pfe.sevices.RegisterTask;

public class RegisterActivity extends AppCompatActivity {
    private EditText etName, etEmail, etPassword, etRepassword;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ativity_register);

        // Initialize views
        etName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        etRepassword = findViewById(R.id.et_repassword);
        Button signupButton = findViewById(R.id.signupButton);

        // Set up the signup button click listener
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeSignUpRequest();
            }
        });
    }

    private void makeSignUpRequest() {
        String fullName = etName.getText().toString().trim();
        String[] names = fullName.split(" ", 2);  // Split the string into two parts

        String firstName = "";
        String lastName = "";

        if (names.length > 0) {
            firstName = names[0];  // First part is the first name
            if (names.length > 1) {
                lastName = names[1];  // Second part is the last name
            }
        }
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString().trim();
        String repassword = etRepassword.getText().toString().trim();
        Log.e("Request", "Request: " + email );
        // Use RegisterTask for the registration process
        RegisterTask registerTask = new RegisterTask();
        registerTask.Register(email,password ,"USER" ,firstName ,lastName , new RegisterTask.RegisterCallback() {
            @Override
            public void onSuccess() {
                // Handle success
                Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                navigateToLogin();
            }


            @Override
            public void onError(String errorMessage) {
                // Handle error
                Toast.makeText(RegisterActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void navigateToLogin() {
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
        finish();
    }
}

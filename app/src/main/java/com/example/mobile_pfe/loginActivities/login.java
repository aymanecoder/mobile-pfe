package com.example.mobile_pfe.loginActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mobile_pfe.R;
import com.example.mobile_pfe.SportActivities.ListSportActivity;
import com.example.mobile_pfe.UI.MainActivity;
import com.example.mobile_pfe.model.Globals.AppGlobals;
import com.example.mobile_pfe.registerActivities.RegisterActivity;
import com.example.mobile_pfe.sevices.LoginTask;


public class login extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private TextView errorTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        errorTextView = findViewById(R.id.errorTextView);

        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                LoginTask loginTask = new LoginTask();
                loginTask.login(email, password, new LoginTask.LoginCallback() {
                    @Override
                    public void onSuccess() {
                        // Handle success, navigate to MainActivity
                        System.out.println("Access Token: " + AppGlobals.getAccessToken());
                        Intent intent = new Intent(login.this, ListSportActivity.class);
                         startActivity(intent);

                    }

                    @Override
                    public void onError(String errorMessage) {
                        // Handle error, show error message
                        errorTextView.setVisibility(View.VISIBLE);
                        errorTextView.setText(errorMessage);
                    }
                });
            }
        });
    }
    public void onRegisterButtonClickOfLogin(View view) {
        // Handle the button click
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
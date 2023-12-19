package com.example.mobile_pfe.TeamActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile_pfe.R;

public class Teamprofilactivity extends AppCompatActivity {

    private ImageView logoImageView;
    private EditText emailEditText;
    private Button saveButton;
    private Button cancelButton;
    private TextView errorTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teamprofil);

        // Initialisation des vues
        logoImageView = findViewById(R.id.logoImageView);
        emailEditText = findViewById(R.id.emailEditText);
        saveButton = findViewById(R.id.Save);
        cancelButton = findViewById(R.id.Save2);
        errorTextView = findViewById(R.id.errorTextView);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}

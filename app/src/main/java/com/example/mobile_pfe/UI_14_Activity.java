package com.example.mobile_pfe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class UI_14_Activity extends AppCompatActivity {

    String selectedGender = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui14);


        Button maleButton = findViewById(R.id.male_button);
        Button femaleButton = findViewById(R.id.female_button);

        maleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedGender = "Male";
                maleButton.setBackground(getResources().getDrawable(R.drawable.clicked_button));
                femaleButton.setBackground(getResources().getDrawable(R.drawable.button1));
            }
        });

        femaleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedGender = "Female";
                maleButton.setBackground(getResources().getDrawable(R.drawable.button1));
                femaleButton.setBackground(getResources().getDrawable(R.drawable.clicked_button));
            }
        });


        Button button = findViewById(R.id.next_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedGender != null) {
                    Intent intent = new Intent(UI_14_Activity.this, UI_15_Activity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Please choose a gender", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
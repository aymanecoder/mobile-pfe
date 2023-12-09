package com.example.mobile_pfe.programActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mobile_pfe.R;

public class ChoixProgramActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_program);

        Button viewProgramsButton = findViewById(R.id.view_programs_button);
        viewProgramsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click, start ProgramActivity
                Intent intent = new Intent(ChoixProgramActivity.this, ListProgramActivity.class);
                startActivity(intent);
            }
        });

        Button viewCompetitionsButton = findViewById(R.id.view_competitions_button);
        viewCompetitionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click, start CompetitionActivity
                Intent intent = new Intent(ChoixProgramActivity.this, ListCompetitionActivity.class);
                startActivity(intent);
            }
        });
    }
}
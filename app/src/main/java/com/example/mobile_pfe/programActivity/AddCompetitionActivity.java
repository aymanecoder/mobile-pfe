package com.example.mobile_pfe.programActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mobile_pfe.R;
import com.example.mobile_pfe.UI.CoachContent;

public class AddCompetitionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_competition);

        TextView back = findViewById(R.id.Back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddCompetitionActivity.this, ListCompetitionActivity.class);
                startActivity(intent);

            }
        });
    }
}
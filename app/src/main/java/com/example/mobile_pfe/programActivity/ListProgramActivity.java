package com.example.mobile_pfe.programActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.mobile_pfe.R;

public class ListProgramActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_program);
    }

    public void onNutritionClicked(View view) {
        // Hide Entrainement and show Nutrition section
//        findViewById(R.id.entrainementSection).setVisibility(View.GONE);
//        findViewById(R.id.nutritionSection).setVisibility(View.VISIBLE);
    }

    public void onEntrainementClicked(View view) {
        // Hide Nutrition and show Entrainement section
//        findViewById(R.id.nutritionSection).setVisibility(View.GONE);
//        findViewById(R.id.entrainementSection).setVisibility(View.VISIBLE);
    }

}
package com.example.mobile_pfe.programActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mobile_pfe.Network.RetrofitInstance;
import com.example.mobile_pfe.R;
import com.example.mobile_pfe.model.Competition.Competition;
import com.example.mobile_pfe.model.Program.Program;
import com.example.mobile_pfe.sevices.CompetitionService;
import com.example.mobile_pfe.sevices.CompetitionService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompetitionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition);

        if (getIntent().hasExtra("competitionId")) {
            int competitionId =  getIntent().getIntExtra("competitionId",1);
            Toast.makeText(this, "Received competition ID: " + competitionId, Toast.LENGTH_SHORT).show();
            // Use the competition ID as needed


            CompetitionService service = RetrofitInstance.getRetrofitInstance().create(CompetitionService.class);

            /*Call the method with parameter in the interface to get the employee data*/
            Call<Competition> call = service.getById(Math.toIntExact(competitionId));

            /*Log the URL called*/
            Log.wtf("URL Called", call.request().url() + "");

            call.enqueue(new Callback<Competition>() {
                @Override
                public void onResponse(Call<Competition> call, Response<Competition> response) {
                    Competition competitionData =(Competition) response.body();
                    Log.wtf("Response", " "+competitionData);
                    if (competitionData != null) {

                        generateCompetitionData(competitionData);
                    } else {
                        Log.e("Response", "Competition data is null");
                        // Handle the case where the Competition data is null
                        Toast.makeText(CompetitionActivity.this, "Competition data is null", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Competition> call, Throwable t) {
                    System.out.println("get all errors");
                    t.printStackTrace();
                    Toast.makeText(CompetitionActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                }
            });

        }


    }


    private void generateCompetitionData(Competition competitionData) {
        // Access UI elements by their IDs
        TextView titleTextView = findViewById(R.id.post_title);
        TextView nmbrTeamsTextView = findViewById(R.id.nmbrTeams);
        TextView descriptionTextView = findViewById(R.id.post_description);
        TextView dateTextView = findViewById(R.id.post_date);
        ImageView postImageView = findViewById(R.id.post_image);

        // Populate UI elements with competition data
        titleTextView.setText(competitionData.getTitle());
        nmbrTeamsTextView.setText(String.valueOf(competitionData.getNbrTeams())); // Replace with actual author data
        descriptionTextView.setText(competitionData.getDescreption());
        dateTextView.setText(competitionData.getCreationDate()); // Replace with actual date data

        // Load image using Glide or your preferred image loading library
        Glide.with(this)
                .load(competitionData.getLogoPath()) // Replace with actual image URL or resource ID
                .placeholder(R.drawable.notfound)
                .error(R.drawable.notfound)
                .into(postImageView);
    }


}
package com.example.mobile_pfe.TeamActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile_pfe.Network.RetrofitInstance;
import com.example.mobile_pfe.R;
import com.example.mobile_pfe.databinding.ActivityListviewteamBinding;
import com.example.mobile_pfe.model.Equipe.TeamRequestBody;
import com.example.mobile_pfe.model.Sportif;
import com.example.mobile_pfe.programActivity.UploadProgramActivity;
import com.example.mobile_pfe.sevices.SportifService;
import com.example.mobile_pfe.sevices.TeamService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class listteamactivity extends AppCompatActivity {
    ActivityListviewteamBinding Binding;
    private ListAdapter2 listAdapter; // Added field to store the ListAdapter2 instance
    private ArrayList<Sportif> selectedSportifs; // Added field to store selected sportifs

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Binding = ActivityListviewteamBinding.inflate(getLayoutInflater());
        setContentView(Binding.getRoot());

        Intent intent = getIntent();
        String teamName = intent.getStringExtra("teamName");
        String logoImageUrl = intent.getStringExtra("logoImageUrl");
        String description = intent.getStringExtra("description");

        // Now you can use these values as needed
        Log.d("TeamInfo", "Team Name: " + teamName);
        Log.d("TeamInfo", "Logo Image URL: " + logoImageUrl);
        Log.d("TeamInfo", "Description: " + description);

        selectedSportifs = new ArrayList<>(); // Initialize the selected sportifs list

        SportifService sportifService = RetrofitInstance.getSportifService();

        Call<List<Sportif>> call = sportifService.getSportifs();
        call.enqueue(new Callback<List<Sportif>>() {
            @Override
            public void onResponse(Call<List<Sportif>> call, Response<List<Sportif>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Sportif> sportifs = response.body();
                    listAdapter = new ListAdapter2(listteamactivity.this, sportifs); // Initialize listAdapter
                    displaySportifs(sportifs);
                } else {
                    // Handle error
                }
            }

            @Override
            public void onFailure(Call<List<Sportif>> call, Throwable t) {
                // Handle failure
            }
        });

        // Add a click listener to the "Save" button
        Button saveButton = findViewById(R.id.Save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listAdapter != null) {
                    // Get the selected sportifs from the adapter
                    selectedSportifs = listAdapter.getSelectedSportifs();
                    System.out.println("Sportif ID: " + selectedSportifs);

                    // Now you can use the selectedSportifs list as needed
                    for (Sportif sportif : selectedSportifs) {
                        Log.d("SelectedSportif", "Sportif ID: " + sportif.getId());
                        System.out.println("Sportif ID: " + sportif.getId());
                    }

                    // Implement the logic for sending data to the server
                    sendCreateTeamRequest(logoImageUrl, teamName, description, 1, selectedSportifs, 1);
                }
            }
        });
    }

    private void displaySportifs(List<Sportif> sportifs) {
        Binding.lisvieww.setAdapter(listAdapter);
    }

    private void sendCreateTeamRequest(String logo, String teamName, String description, Integer adminId, List<Sportif> selectedSportifs, Integer sportId) {
        List<Integer> memberIds = new ArrayList<>();
        for (Sportif sportif : selectedSportifs) {
            memberIds.add(sportif.getId()); // Assuming you have a method to get the ID of Sportif
        }

        TeamService teamService = RetrofitInstance.getTeamService();
        Call<Void> call = teamService.createTeam(new TeamRequestBody(logo, teamName, description, adminId, memberIds, sportId));

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(listteamactivity.this, "Team details uploaded successfully", Toast.LENGTH_SHORT).show();
                } else {
                    // Handle unsuccessful response
                    Toast.makeText(listteamactivity.this, "Failed to upload team details", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(listteamactivity.this, "Failed to upload program details: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
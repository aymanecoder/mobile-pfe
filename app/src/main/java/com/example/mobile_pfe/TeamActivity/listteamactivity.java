package com.example.mobile_pfe.TeamActivity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile_pfe.Network.RetrofitInstance;
import com.example.mobile_pfe.R;
import com.example.mobile_pfe.databinding.ActivityListviewteamBinding;
import com.example.mobile_pfe.matchActivities.ShowMatches;
import com.example.mobile_pfe.model.Equipe.TeamRequestBody;
import com.example.mobile_pfe.model.Sportif;
import com.example.mobile_pfe.programActivity.UploadProgramActivity;
import com.example.mobile_pfe.sevices.SportifService;
import com.example.mobile_pfe.sevices.TeamService;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class listteamactivity extends AppCompatActivity {
    ActivityListviewteamBinding Binding;
    private ListAdapter2 listAdapter; // Added field to store the ListAdapter2 instance
    private ArrayList<Sportif> selectedSportifs; // Added field to store selected sportifs

    private boolean fromChooseTeam;

    private ColorStateList originalTextColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Binding = ActivityListviewteamBinding.inflate(getLayoutInflater());
        setContentView(Binding.getRoot());

        Intent intent = getIntent();
        String teamName = intent.getStringExtra("teamName");
        String logoImageUrl = intent.getStringExtra("logoImageUrl");
        String description = intent.getStringExtra("description");
        fromChooseTeam = getIntent().getBooleanExtra("fromChooseTeam", false);

        // Move the backButton code here
        TextView backButton = findViewById(R.id.back1);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        originalTextColor = backButton.getTextColors();
        backButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Pressed state: Set text color to white
                        backButton.setTextColor(Color.WHITE);
                        break;

                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        // Released or canceled state: Restore the original text color
                        backButton.setTextColor(originalTextColor);
                        break;
                }
                return false;
            }
        });



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
                    System.out.println("REQuestbody " + "logo" + logoImageUrl + "title" + teamName + "description" + description + 1 + "SelctedSportifs" + selectedSportifs);
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
        // Create a map of member IDs with keys like "members[1].id", "members[2].id", etc.
        Map<String, Integer> memberIds = new HashMap<>();
        for (int i = 0; i < selectedSportifs.size(); i++) {
            memberIds.put("members[" + i + "].id", selectedSportifs.get(i).getId());
        }

        MultipartBody.Part filePart = null;
        if (logo != null && !logo.isEmpty()) {
            // Create a MultipartBody.Part for the file (assuming you have the file path)
            File file = new File(logo);
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            filePart = MultipartBody.Part.createFormData("logo", file.getName(), requestFile);
        }

        // Create RequestBody for other parameters
        RequestBody teamNameBody = RequestBody.create(MediaType.parse("text/plain"), teamName);
        RequestBody descriptionBody = RequestBody.create(MediaType.parse("text/plain"), description);
        RequestBody adminIdBody = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(adminId));
        RequestBody sportIdBody = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(sportId));

        TeamService teamService = RetrofitInstance.getTeamService();

        // Use the modified createTeam method with Map<String, Integer> for members
        Call<ResponseBody> call = teamService.createTeam(filePart, teamNameBody, descriptionBody, sportIdBody, memberIds);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(listteamactivity.this, "Team details uploaded successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(listteamactivity.this, TeamActivity.class);
                    intent.putExtra("fromChooseTeam", fromChooseTeam);
                    startActivity(intent);
                } else {
                    // Handle unsuccessful response
                    Toast.makeText(listteamactivity.this, "Failed to upload team details", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(listteamactivity.this, "Failed to upload program details: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



}
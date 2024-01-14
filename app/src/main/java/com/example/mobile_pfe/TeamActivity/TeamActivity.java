package com.example.mobile_pfe.TeamActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_pfe.Network.RetrofitInstance;
import com.example.mobile_pfe.R;
import com.example.mobile_pfe.model.Sportif;
import com.example.mobile_pfe.sevices.TeamService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamActivity extends AppCompatActivity implements CustomAdapter.OnItemClickListener {

    private CustomAdapter adapter;
    private List<TeamDetails> originalTeamDetailsList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lisviewteam);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        TeamService teamService = RetrofitInstance.getTeamService();
        Call<List<TeamDetails>> call = teamService.getTeams();

        call.enqueue(new Callback<List<TeamDetails>>() {
            @Override
            public void onResponse(Call<List<TeamDetails>> call, Response<List<TeamDetails>> response) {
                if (response.isSuccessful()) {
                    List<TeamDetails> teamDetailsList = response.body();

                    // Print Team ID and Team Name
                    for (TeamDetails teamDetails : teamDetailsList) {
                        System.out.println("Team ID: " + teamDetails.getId());
                        System.out.println("Team Name: " + teamDetails.getTeamName());
                    }

                    originalTeamDetailsList = new ArrayList<>(teamDetailsList);

                    adapter = new CustomAdapter(TeamActivity.this, teamDetailsList, TeamActivity.this);
                    recyclerView.setAdapter(adapter);

                    Toast.makeText(TeamActivity.this, "Success to upload team details", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(TeamActivity.this, "Failed to upload teams details", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<TeamDetails>> call, Throwable t) {
                Toast.makeText(TeamActivity.this, "Failed to connect to the server", Toast.LENGTH_SHORT).show();
            }
        });

        // Search functionality
        EditText searchView = findViewById(R.id.frame_11);
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not needed for this example
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterTeams(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Not needed for this example
            }
        });

        ImageButton ellipseButton = findViewById(R.id.ellipse_1);
        ellipseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeamActivity.this, Teamprofilactivity.class);
                startActivity(intent);
            }
        });
    }

    private void filterTeams(String query) {
        List<TeamDetails> filteredList = new ArrayList<>();

        for (TeamDetails teamDetails : originalTeamDetailsList) {
            if (teamDetails.getTeamName().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(teamDetails);
            }
        }

        adapter.filterList(filteredList);
    }

    // Handle item click
    @Override
    public void onItemClick(TeamDetails teamDetails) {
        // Handle the click event, open ItemActivity with the clicked team's information
        Intent intent = new Intent(this, ItemActivity.class);
        // Pass the necessary details to ItemActivity using intent extras
        intent.putExtra("teamId", teamDetails.getId());
        intent.putExtra("teamName", teamDetails.getTeamName());
        System.out.println("ItemActivity Members List: 1" + teamDetails.getLogoPath());
        intent.putExtra("picture", teamDetails.getLogoPath());

        List<Sportif> members = teamDetails.getMembers();

        if (members != null && !members.isEmpty()) {
            intent.putExtra("members", new ArrayList<>(members));
        } else {
            // If the list is empty or null, you can pass an empty ArrayList
            intent.putExtra("members", new ArrayList<Sportif>());
            // or
            // intent.putExtra("members", (Serializable) null);
        }
        System.out.println("ItemActivity Members List: 2" + members);

        // Add other details as needed
        startActivity(intent);
    }
}
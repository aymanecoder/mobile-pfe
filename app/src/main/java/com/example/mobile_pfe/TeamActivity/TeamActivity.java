package com.example.mobile_pfe.TeamActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_pfe.Network.RetrofitInstance;
import com.example.mobile_pfe.R;
import com.example.mobile_pfe.matchActivities.MatchRepository;
import com.example.mobile_pfe.matchActivities.ShowMatches;
import com.example.mobile_pfe.model.Sportif;
import com.example.mobile_pfe.sevices.GroupService;
import com.example.mobile_pfe.sevices.MatchRequest;
import com.example.mobile_pfe.sevices.MatchService;
import com.example.mobile_pfe.sevices.TeamService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamActivity extends AppCompatActivity implements CustomAdapter.OnItemClickListener {

    private CustomAdapter adapter;
    private List<TeamDetails> originalTeamDetailsList;

    private boolean fromChooseTeam;

    private ColorStateList originalTextColor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lisviewteam);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        fromChooseTeam = getIntent().getBooleanExtra("fromChooseTeam", false);



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

                    adapter = new CustomAdapter(TeamActivity.this, teamDetailsList, TeamActivity.this,fromChooseTeam);
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


        // Find your button in the layout
        Button yourButton = findViewById(R.id.createMatch);

//         Conditionally show/hide the button based on the flag
        if (fromChooseTeam) {
            yourButton.setVisibility(View.VISIBLE);
        } else {
            yourButton.setVisibility(View.GONE);
        }
        // Move the backButton code here
        TextView backButton = findViewById(R.id.backlist);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        originalTextColor = backButton.getTextColors();
        yourButton.setOnTouchListener(new View.OnTouchListener() {
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


        yourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<TeamDetails> selectedTeams = adapter.getSelectedTeams();
                if(selectedTeams.size()==2) {
                    MatchRepository matchRepository = MatchRepository.getInstance();
                    MatchRequest matchRequest = matchRepository.getMatchRequest();
                    matchRequest.setSport(1);
                    matchRequest.setTypeMatch("UPCOMING");
                    matchRequest.setTeamIdsFromSelectedTeams(selectedTeams);
                    System.out.println("Match details 2 :" + matchRequest.toString());
                    //her switch from this fragment to TeamActivity
                    MatchService matchService = RetrofitInstance.getMatchService();

                    // Use the modified createTeam method with Map<String, Integer> for members
                    Call<ResponseBody> call = matchService.createMatch(matchRequest);

                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(TeamActivity.this, "match details uploaded successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(TeamActivity.this, ShowMatches.class);
                                startActivity(intent);

                            } else {
                                // Handle unsuccessful response
                                Toast.makeText(TeamActivity.this, "Failed to upload match details", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(TeamActivity.this, "Failed to connect " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else{
                    Toast.makeText(TeamActivity.this, "You must select two teams " , Toast.LENGTH_SHORT).show();

                }
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
                intent.putExtra("fromChooseTeam", fromChooseTeam);
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

        intent.putExtra("fromChooseTeam", fromChooseTeam);
        startActivity(intent);

    }

}
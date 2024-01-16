package com.example.mobile_pfe.programActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobile_pfe.Adapter.ProgramAdapter;
import com.example.mobile_pfe.UI.MainActivity;
import com.example.mobile_pfe.model.Globals.AppGlobals;
import com.example.mobile_pfe.Network.RetrofitInstance;
import com.example.mobile_pfe.R;
import com.example.mobile_pfe.UI.CoachContent;
import com.example.mobile_pfe.UI.FindCoaches;
import com.example.mobile_pfe.Adapter.CompetitionAdapter;
import com.example.mobile_pfe.model.Competition.Competition;
import com.example.mobile_pfe.sevices.CompetitionService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListCompetitionActivity extends AppCompatActivity {

    private CompetitionAdapter adapter;
    private RecyclerView recyclerView;

    private ColorStateList originalTextColor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_competition);

        TextView back = findViewById(R.id.Back);

        originalTextColor = back.getTextColors();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListCompetitionActivity.this, CoachContent.class);
                startActivity(intent);

            }
        });

        /*Create handle for the RetrofitInstance interface*/
        CompetitionService service = RetrofitInstance.getRetrofitInstance().create(CompetitionService.class);

        /*Call the method with parameter in the interface to get the employee data*/
        Call<List<Competition>> call = service.getChallengesBySportAndTypeProgram(AppGlobals.getSportId());

        /*Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<List<Competition>>() {
            @Override
            public void onResponse(Call<List<Competition>> call, Response<List<Competition>> response) {
                generateEmployeeList((ArrayList<Competition>) response.body());
            }

            @Override
            public void onFailure(Call<List<Competition>> call, Throwable t) {
                Toast.makeText(ListCompetitionActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });



        back.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Pressed state: Set text color to white
                        back.setTextColor(Color.WHITE);
                        break;

                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        // Released or canceled state: Restore the original text color
                        back.setTextColor(originalTextColor);

                        // Trigger onBackPressed() when the button is released
                        Intent intent = new Intent(ListCompetitionActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                }
                return false;
            }
        });

        Log.d("AccessToken", "Value: " + AppGlobals.getAccessToken());
// Now you can call extractUserRole
        List<String> userRoles = AppGlobals.extractUserRoles();

        Log.d("UserRole", "Value: " + userRoles);

        if (userRoles.contains("COACH")) {
            FloatingActionButton fabAdd = findViewById(R.id.fabAdd);
            fabAdd.setVisibility(View.VISIBLE);

            fabAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Navigate to UploadActivity when fabAdd is clicked
                    Intent intent = new Intent(ListCompetitionActivity.this, AddCompetitionActivity.class);
                    startActivity(intent);
                }
            });
        } else {
            FloatingActionButton fabAdd = findViewById(R.id.fabAdd);
            fabAdd.setVisibility(View.GONE);
        }

    }

    private void generateEmployeeList(ArrayList<Competition> programDataList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        if (programDataList == null) {
            // Handle the case where data is null
            // For example, display a message or perform some appropriate action
            Toast.makeText(this, "No data available", Toast.LENGTH_SHORT).show();
            return;
        }

        adapter = new CompetitionAdapter(programDataList);

        adapter.setOnItemClickListener(new CompetitionAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Competition competition) {
                Intent intent = new Intent(ListCompetitionActivity.this, CompetitionActivity.class);
                Log.d("competitionId"," "+competition.getId());
                intent.putExtra("competitionId", competition.getId());
                startActivity(intent);
            }

            @Override
            public void onButtonClick(Competition competition) {
                // to do
                Log.d("button competitionId"," "+competition.getId());

                CompetitionService service = RetrofitInstance.getRetrofitInstance().create(CompetitionService.class);

                Call<Map<String, String>> call = service.joinChallenge(competition.getId());

                /* Log the URL called */
                Log.wtf("URL Called", call.request().url() + "");

                call.enqueue(new Callback<Map<String, String>>() {

                    @Override
                    public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                        if (response.isSuccessful()) {
                            Map<String, String> responseBody = response.body();

                            if (responseBody != null && responseBody.containsKey("status") && responseBody.containsKey("message")) {
                                String status = responseBody.get("status");
                                String message = responseBody.get("message");

                                if ("success".equals(status)) {
                                    Toast.makeText(ListCompetitionActivity.this, "Success: " + message, Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(ListCompetitionActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(ListCompetitionActivity.this, "Invalid response format", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // Handle error response
                            Toast.makeText(ListCompetitionActivity.this, "Error: " + response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Map<String, String>> call, Throwable t) {
                        t.printStackTrace();
                        Log.d("error", t.getMessage());
                        Toast.makeText(ListCompetitionActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListCompetitionActivity.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }
}
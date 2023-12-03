package com.example.mobile_pfe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.mobile_pfe.adapter.TeamAdapter;
import com.example.mobile_pfe.model.Equipe;
import com.example.mobile_pfe.retrofit.EquipeApi;
import com.example.mobile_pfe.retrofit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_list);

        recyclerView = findViewById(R.id.TeamList_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadEmployees();
    }

    private void loadEmployees() {
        RetrofitService retrofitService = new RetrofitService();
        EquipeApi employeeApi = retrofitService.getRetrofit().create(EquipeApi.class);
        employeeApi.getAllTeams()
                .enqueue(new Callback<List<Equipe>>() {
                    @Override
                    public void onResponse(Call<List<Equipe>> call, Response<List<Equipe>> response) {
                        populateListView(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Equipe>> call, Throwable t) {
                        Toast.makeText(TeamListActivity.this, "Failed to load employees", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void populateListView(List<Equipe> teamList) {
        TeamAdapter teamAdapter = new TeamAdapter(teamList);
        recyclerView.setAdapter(teamAdapter);
    }
}
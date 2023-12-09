package com.example.mobile_pfe.programActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.mobile_pfe.Network.RetrofitInstance;
import com.example.mobile_pfe.R;
import com.example.mobile_pfe.adapter.CompetitionAdapter;
import com.example.mobile_pfe.model.Competition.Competition;
import com.example.mobile_pfe.model.Competition.CompetitionList;
import com.example.mobile_pfe.sevices.CompetitionService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListCompetitionActivity extends AppCompatActivity {

    private CompetitionAdapter adapter;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_competition);


        /*Create handle for the RetrofitInstance interface*/
        CompetitionService service = RetrofitInstance.getRetrofitInstance().create(CompetitionService.class);

        /*Call the method with parameter in the interface to get the employee data*/
        Call<CompetitionList> call = service.getAll();

        /*Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<CompetitionList>() {
            @Override
            public void onResponse(Call<CompetitionList> call, Response<CompetitionList> response) {
                generateEmployeeList(response.body().getCompetitionArrayList());
            }

            @Override
            public void onFailure(Call<CompetitionList> call, Throwable t) {
                Toast.makeText(ListCompetitionActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generateEmployeeList(ArrayList<Competition> programDataList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        adapter = new CompetitionAdapter(programDataList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListCompetitionActivity.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }
}
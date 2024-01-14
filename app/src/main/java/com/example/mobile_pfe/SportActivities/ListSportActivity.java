package com.example.mobile_pfe.SportActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.mobile_pfe.Adapter.CompetitionAdapter;
import com.example.mobile_pfe.Adapter.SportAdapter;
import com.example.mobile_pfe.Adapter.SportAdapter;
import com.example.mobile_pfe.Network.RetrofitInstance;
import com.example.mobile_pfe.R;
import com.example.mobile_pfe.UI.MainActivity;
import com.example.mobile_pfe.model.Sport.Sport;
import com.example.mobile_pfe.model.Sport.Sport;
import com.example.mobile_pfe.sevices.SportService;
import com.example.mobile_pfe.model.Globals.AppGlobals;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListSportActivity extends AppCompatActivity {

    private SportAdapter adapter;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sport);

        SportService service = RetrofitInstance.getRetrofitInstance().create(SportService.class);

        /*Call the method with parameter in the interface to get the employee data*/
        Call<List<Sport>> call = service.getAll();

        /*Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<List<Sport>>() {
            @Override
            public void onResponse(Call<List<Sport>> call, Response<List<Sport>> response){
                Log.wtf("reponse", response + "");
                generateEmployeeList((ArrayList<Sport>) response.body());
            }

            @Override
            public void onFailure(Call<List<Sport>> call, Throwable t) {
                System.out.println("get all errors");
                t.printStackTrace();
                Toast.makeText(ListSportActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void generateEmployeeList(ArrayList<Sport> sportDataList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        if (sportDataList == null || sportDataList.isEmpty()) {
            // Handle the case where data is null or empty
            Log.e("EntrainFragment", "No data available");
            Toast.makeText(ListSportActivity.this, "No data available", Toast.LENGTH_SHORT).show();
            return;
        }

        adapter = new SportAdapter(sportDataList);

        adapter.setOnItemClickListener(new SportAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Sport sport) {
                // Handle item click, for example, navigate to another activity with sport ID
                AppGlobals.setSportId(sport.getId());
                Intent intent = new Intent(ListSportActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListSportActivity.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }

}
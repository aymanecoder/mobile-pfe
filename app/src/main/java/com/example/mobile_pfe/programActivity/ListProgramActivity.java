package com.example.mobile_pfe.programActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.mobile_pfe.Adapter.ProgramAdapter;
import com.example.mobile_pfe.Network.RetrofitInstance;
import com.example.mobile_pfe.R;
import com.example.mobile_pfe.model.Program.Program;
import com.example.mobile_pfe.sevices.ProgramService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListProgramActivity extends AppCompatActivity {
    private ProgramAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_program);


        /*Create handle for the RetrofitInstance interface*/
        ProgramService service = RetrofitInstance.getRetrofitInstance().create(ProgramService.class);

        /*Call the method with parameter in the interface to get the employee data*/
        Call<List<Program>> call = service.getAll();

        /*Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<List<Program>>() {
            @Override
            public void onResponse(Call<List<Program>> call, Response<List<Program>> response){
                generateEmployeeList((ArrayList<Program>) response.body());
            }

            @Override
            public void onFailure(Call<List<Program>> call, Throwable t) {
                System.out.println("get all errors");
                t.printStackTrace();
                Toast.makeText(ListProgramActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton fabAdd = findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to UploadActivity when fabAdd is clicked
                Intent intent = new Intent(ListProgramActivity.this, UploadProgramActivity.class);
                startActivity(intent);
            }
        });
    }



    private void generateEmployeeList(ArrayList<Program> programDataList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        if (programDataList == null) {
            // Handle the case where data is null
            // For example, display a message or perform some appropriate action
            Toast.makeText(this, "No data available", Toast.LENGTH_SHORT).show();
            return;
        }

        adapter = new ProgramAdapter(programDataList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListProgramActivity.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
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
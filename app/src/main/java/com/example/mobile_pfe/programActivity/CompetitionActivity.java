package com.example.mobile_pfe.programActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.mobile_pfe.Network.RetrofitInstance;
import com.example.mobile_pfe.R;
import com.example.mobile_pfe.model.Program.Program;
import com.example.mobile_pfe.sevices.CompetitionService;
import com.example.mobile_pfe.sevices.ProgramService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompetitionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition);

        if (getIntent().hasExtra("competitionId")) {
            int competitionId = getIntent().getIntExtra("competitionId", 1);
            Toast.makeText(this, "Received competition ID: " + competitionId, Toast.LENGTH_SHORT).show();
            // Use the program ID as needed


            CompetitionService service = RetrofitInstance.getRetrofitInstance().create(CompetitionService.class);

            /*Call the method with parameter in the interface to get the employee data*/
            Call<Program> call = service.getById(programId);

            /*Log the URL called*/
            Log.wtf("URL Called", call.request().url() + "");

            call.enqueue(new Callback<Program>() {
                @Override
                public void onResponse(Call<Program> call, Response<Program> response) {
                    Program programData =(Program) response.body();
                    Log.wtf("Response", " "+programData);
                    if (programData != null) {

                        generateProgramData(programData);
                    } else {
                        Log.e("Response", "Program data is null");
                        // Handle the case where the Program data is null
                        Toast.makeText(ProgramActivity.this, "Program data is null", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Program> call, Throwable t) {
                    System.out.println("get all errors");
                    t.printStackTrace();
                    Toast.makeText(ProgramActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                }
            });

        }


    }
}
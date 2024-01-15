package com.example.mobile_pfe.programActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mobile_pfe.Adapter.ProgramAdapter;
import com.example.mobile_pfe.Network.RetrofitInstance;
import com.example.mobile_pfe.R;
import com.example.mobile_pfe.sevices.ProgramService;
import com.example.mobile_pfe.model.Program.Program;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProgramActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program);

        if (getIntent().hasExtra("programId")) {
            Long programId = getIntent().getLongExtra("programId", 1L);
            Toast.makeText(this, "Received program ID: " + programId, Toast.LENGTH_SHORT).show();
            // Use the program ID as needed


            ProgramService service = RetrofitInstance.getRetrofitInstance().create(ProgramService.class);

            /*Call the method with parameter in the interface to get the employee data*/
            Call<Program> call = service.getById(Math.toIntExact(programId));

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

    private void generateProgramData(Program programData) {
        // Access UI elements by their IDs
        TextView titleTextView = findViewById(R.id.post_title);
        TextView authorTextView = findViewById(R.id.post_author);
        TextView descriptionTextView = findViewById(R.id.post_description);
        TextView dateTextView = findViewById(R.id.post_date);
        ImageView postImageView = findViewById(R.id.post_image);

        // Populate UI elements with program data
        titleTextView.setText(programData.getTitle());
        authorTextView.setText("Author:  yassine oussi"); // Replace with actual author data
        descriptionTextView.setText(programData.getDescreption());
        dateTextView.setText("Date: 11/1/2024" ); // Replace with actual date data

        // Load image using Glide or your preferred image loading library
        Glide.with(this)
                .load(programData.getPicturePath()) // Replace with actual image URL or resource ID
                .placeholder(R.drawable.notfound)
                .error(R.drawable.notfound)
                .into(postImageView);
    }

}
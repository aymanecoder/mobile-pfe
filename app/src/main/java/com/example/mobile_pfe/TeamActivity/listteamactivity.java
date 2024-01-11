package com.example.mobile_pfe.TeamActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile_pfe.Network.RetrofitInstance;
import com.example.mobile_pfe.R;
import com.example.mobile_pfe.databinding.ActivityListviewteamBinding;
import com.example.mobile_pfe.model.Sportif;
import com.example.mobile_pfe.sevices.SportifService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class listteamactivity extends AppCompatActivity {
    ActivityListviewteamBinding Binding;
    private ArrayList<Sportif> selectedSportifs; // Added field to store selected sportifs

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Binding = ActivityListviewteamBinding.inflate(getLayoutInflater());
        setContentView(Binding.getRoot());

        selectedSportifs = new ArrayList<>(); // Initialize the selected sportifs list

        SportifService sportifService = RetrofitInstance.getSportifService();

        Call<List<Sportif>> call = sportifService.getSportifs();
        call.enqueue(new Callback<List<Sportif>>() {
            @Override
            public void onResponse(Call<List<Sportif>> call, Response<List<Sportif>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Sportif> sportifs = response.body();
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

            }
        });
    }

    private void displaySportifs(List<Sportif> sportifs) {
        ListAdapter2 listAdapter = new ListAdapter2(listteamactivity.this, sportifs);
        Binding.lisvieww.setAdapter(listAdapter);
    }
}
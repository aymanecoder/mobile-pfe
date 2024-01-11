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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Binding = ActivityListviewteamBinding.inflate(getLayoutInflater());
        setContentView(Binding.getRoot());

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
    }

    private void displaySportifs(List<Sportif> sportifs) {
        ArrayList<User> userArrayList = new ArrayList<>();

        for (Sportif sportif : sportifs) {
            String fullName = sportif.getFirstName() + " " + sportif.getLastName();
            int imageId = R.drawable.default_image; // Set a default image or load from the URL

            // Assuming you have a method to load images from the URL, update imageId accordingly

            User user = new User(fullName, imageId);
            userArrayList.add(user);
        }

        ListAdapter2 listAdapter = new ListAdapter2(listteamactivity.this, userArrayList);
        Binding.lisvieww.setAdapter(listAdapter);
    }
}
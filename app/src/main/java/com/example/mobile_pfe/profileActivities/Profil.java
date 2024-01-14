package com.example.mobile_pfe.profileActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mobile_pfe.R;
import com.example.mobile_pfe.model.Profil.profil;
import com.example.mobile_pfe.Network.RetrofitInstance;
import com.example.mobile_pfe.sevices.CompetitionService;
import com.example.mobile_pfe.sevices.ProfilService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profil extends AppCompatActivity{
    private ProfilService apiService;
    private TextView etName, etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        apiService = RetrofitInstance.getRetrofitInstance().create(ProfilService.class);

        etName = findViewById(R.id.Name);
        etEmail = findViewById(R.id.email);

        fetchUserProfile();

        Button editButton = findViewById(R.id.button_edit);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profil.this, EditProfil.class);
                startActivity(intent);
            }
        });
    }

    private void fetchUserProfile() {
        Call<profil> call = apiService.getUserProfile();
        call.enqueue(new Callback<profil>() {
            @Override
            public void onResponse(Call<profil> call, Response<profil> response) {
                if (response.isSuccessful()) {
                    profil userProfile = response.body();
                    displayUserProfile(userProfile);
                } else {
                    // Gérez l'échec de la requête API
                }
            }

            @Override
            public void onFailure(Call<profil> call, Throwable t) {
                // Gérez l'échec de la requête API
            }
        });
    }

    private void displayUserProfile(profil userProfile) {
        etName.setText(userProfile.getFirstName() + " " + userProfile.getLastName());
        etEmail.setText(userProfile.getEmail());
    }
}


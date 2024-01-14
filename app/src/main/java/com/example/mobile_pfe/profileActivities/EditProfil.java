package com.example.mobile_pfe.profileActivities;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile_pfe.model.Profil.profil;
import com.example.mobile_pfe.R;
import com.example.mobile_pfe.sevices.ProfilService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditProfil extends AppCompatActivity {
    private EditText etNewFirstName;
    private EditText etNewLastName;
    private EditText etNewEmail;
    private EditText etNewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        etNewFirstName = findViewById(R.id.editText);
        etNewLastName = findViewById(R.id.editText2);
        etNewEmail = findViewById(R.id.editText3);
        etNewPassword = findViewById(R.id.editText4);

        Button updateButton = findViewById(R.id.loginButton);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile();
            }
        });
    }

    private void updateProfile() {
        String newFirstName = etNewFirstName.getText().toString();
        String newLastName = etNewLastName.getText().toString();
        String newEmail = etNewEmail.getText().toString();
        String newPassword = etNewPassword.getText().toString();

        // Vérifiez si les champs obligatoires sont remplis
        if (TextUtils.isEmpty(newFirstName) || TextUtils.isEmpty(newLastName) ||
                TextUtils.isEmpty(newEmail) || TextUtils.isEmpty(newPassword)) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }

        // Créez un objet de profil avec les nouvelles données
        profil updatedProfile = new profil(newFirstName, newLastName, newEmail, newPassword);

        // Initialisez Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.11.108:8080") // Remplacez par votre URL de base
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Créez l'instance de votre interface ApiService
        ProfilService apiService = retrofit.create(ProfilService.class);

        // Effectuez l'appel d'API pour mettre à jour le profil
        Call<profil> call = apiService.updateProfile(updatedProfile);
        call.enqueue(new Callback<profil>() {
            @Override
            public void onResponse(Call<profil> call, Response<profil> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(EditProfil.this, "Profil mis à jour avec succès", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(EditProfil.this, "Échec de la mise à jour du profil", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<profil> call, Throwable t) {
                Toast.makeText(EditProfil.this, "Erreur lors de la mise à jour du profil", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

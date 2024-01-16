package com.example.mobile_pfe.profileActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.mobile_pfe.model.Profile;
import com.example.mobile_pfe.R;
import com.example.mobile_pfe.Network.RetrofitInstance;
import com.example.mobile_pfe.UI.MainActivity;
import com.example.mobile_pfe.loginActivities.login;
import com.example.mobile_pfe.programActivity.ListCompetitionActivity;
import com.example.mobile_pfe.sevices.ProfilService;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilActivity extends AppCompatActivity{

    private TextView first_name, LastName,email,address,age,taille,poids;
    private View back;
private Button updateButton,LogOutButton;
    private Profile receivedProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        receivedProfile = (Profile) getIntent().getSerializableExtra("profile");

        first_name = findViewById(R.id.first_name);
        LastName = findViewById(R.id.LastName);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        age = findViewById(R.id.age);
        taille = findViewById(R.id.taille);
        poids = findViewById(R.id.poids);
        CircleImageView profileImageView = findViewById(R.id.imageprofil);

        // Initialize the buttons
        updateButton = findViewById(R.id.button_edit);
        LogOutButton = findViewById(R.id.log_out);
        back=findViewById(R.id.back);

        if (receivedProfile != null) {
            first_name.setText(receivedProfile.getFirstName());
            LastName.setText(receivedProfile.getLastName());
            email.setText(receivedProfile.getEmail());
            address.setText(receivedProfile.getAddress());
            age.setText(receivedProfile.getAge());
            taille.setText(receivedProfile.getTaille());
            poids.setText(receivedProfile.getPoids());

            // Load image using Glide or your preferred image loading library
            Glide.with(this)
                    .load(receivedProfile.getPicturePath())
                    .placeholder(R.drawable.notfound)
                    .error(R.drawable.notfound)
                    .into(profileImageView);
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfilActivity.this, MainActivity.class);
                intent.putExtra("profile", receivedProfile);
                startActivity(intent);
            }
        });
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfilActivity.this, EditProfilActivity.class);
                intent.putExtra("profile", receivedProfile);
                startActivity(intent);
            }
        });

        LogOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfilActivity.this, login.class);
                startActivity(intent);
            }
        });
    }

}


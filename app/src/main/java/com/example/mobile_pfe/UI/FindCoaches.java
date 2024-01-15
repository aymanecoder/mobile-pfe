package com.example.mobile_pfe.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_pfe.Adapter.CoachAdapter;
import com.example.mobile_pfe.Adapter.FriendAdapter;
import com.example.mobile_pfe.Network.RetrofitInstance;
import com.example.mobile_pfe.model.Coach;
import com.example.mobile_pfe.R;
import com.example.mobile_pfe.model.User1.User;
import com.example.mobile_pfe.sevices.UserService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FindCoaches extends AppCompatActivity {

    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_coaches);


        recyclerView = findViewById(R.id.CoachesList_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        TextView backText = findViewById(R.id.Back);
        backText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindCoaches.this, MainActivity.class);
                startActivity(intent);
            }
        });
        loadCoaches();

    }

    private void loadCoaches() {
        RetrofitInstance retrofitInstance = new RetrofitInstance();
        UserService userService = retrofitInstance.getRetrofitInstance().create(UserService.class);
        userService.getCoachs()
                .enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                        populateListView(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {
                        Toast.makeText(FindCoaches.this, "Failed to load coaches", Toast.LENGTH_SHORT).show();
                    }
                });


    }
    private void populateListView(List<User> coachesList) {
        CoachAdapter adapter = new CoachAdapter(coachesList);
        recyclerView.setAdapter(adapter);
    }



}

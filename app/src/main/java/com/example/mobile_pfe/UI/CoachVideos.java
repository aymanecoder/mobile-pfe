package com.example.mobile_pfe.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobile_pfe.model.Video;
import com.example.mobile_pfe.Adapter.VideoAdapter;
import com.example.mobile_pfe.Network.RetrofitInstance;
import com.example.mobile_pfe.R;
import com.example.mobile_pfe.sevices.UserService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoachVideos extends AppCompatActivity {


    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coach_videos);

        recyclerView = findViewById(R.id.videosList_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        TextView backText = findViewById(R.id.Back);
        backText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CoachVideos.this, CoachContent.class);
                startActivity(intent);
            }
        });

        loadVideos();
    }

    private void loadVideos() {
        RetrofitInstance retrofitInstance = new RetrofitInstance();
        UserService userService = retrofitInstance.getRetrofitInstance().create(UserService.class);
        userService.getAllVideos()
                .enqueue(new Callback<List<Video>>() {
                    @Override
                    public void onResponse(Call<List<Video>> call, Response<List<Video>> response) {
                        populateListView(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Video>> call, Throwable t) {
                        t.printStackTrace();
                        Log.d("error video",t.getMessage());
                        Toast.makeText(CoachVideos.this, "Failed to load videos", Toast.LENGTH_SHORT).show();
                    }
                });


    }
    private void populateListView(List<Video> videosList) {
        VideoAdapter adapter = new VideoAdapter(videosList);
        recyclerView.setAdapter(adapter);
    }


}
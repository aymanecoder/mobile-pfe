package com.example.mobile_pfe.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mobile_pfe.Adapter.VideoAdapter;
import com.example.mobile_pfe.Model.Video;
import com.example.mobile_pfe.R;

import java.util.ArrayList;

public class CoachVideos extends AppCompatActivity {

    private ArrayList<Video> videosList = new ArrayList<>();

    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coach_videos);

        fillVideosList();

        recyclerView = findViewById(R.id.videosList_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        VideoAdapter adapter = new VideoAdapter(videosList);
        recyclerView.setAdapter(adapter);

        TextView backText = findViewById(R.id.Back);
        backText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CoachVideos.this, CoachContent.class);
                startActivity(intent);
            }
        });
    }

    private void fillVideosList(){
        videosList.add(new Video("Full Body Workout","30 minutes","200 views","Advanced", R.drawable.img));
        videosList.add(new Video("Lifting Belly","15 minutes","300 views","Beginner", R.drawable.img_1));
    }

}
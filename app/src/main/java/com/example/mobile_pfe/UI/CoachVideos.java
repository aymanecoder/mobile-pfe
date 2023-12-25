package com.example.mobile_pfe.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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

        fillVideosList();

        recyclerView = findViewById(R.id.videosList_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        VideoAdapter adapter = new VideoAdapter(videosList);
        recyclerView.setAdapter(adapter);
    }

    private void fillVideosList(){
        videosList.add(new Video("Full Body Workout","30 minutes","200 views","Advanced", R.drawable.img));
        videosList.add(new Video("Lifting Belly","15 minutes","300 views","Beginner", R.drawable.img_1));
    }

}
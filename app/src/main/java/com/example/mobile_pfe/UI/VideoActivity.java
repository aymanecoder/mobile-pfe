package com.example.mobile_pfe.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.mobile_pfe.Network.RetrofitInstance;
import com.example.mobile_pfe.R;
import com.example.mobile_pfe.model.Video;
import com.example.mobile_pfe.sevices.UserService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoActivity extends AppCompatActivity {

 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video);


        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("VideoId")) {
            int VideoId = intent.getIntExtra("videoId", 0);

            RetrofitInstance retrofitInstance = new RetrofitInstance();
            UserService userService = retrofitInstance.getRetrofitInstance().create(UserService.class);

            Call<Video> call = userService.getVideoById(VideoId);
            call.enqueue(new Callback<Video>() {
                @Override
                public void onResponse(Call<Video> call, Response<Video> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        Video video = response.body();
                        String videoUrl = video.getUrlVideo();

                        VideoView videoView = findViewById(R.id.video_view);

                        Uri uri = Uri.parse(videoUrl);
                        videoView.setVideoURI(uri);

                        MediaController mediaController = new MediaController(VideoActivity.this);
                        videoView.setMediaController(mediaController);
                        mediaController.setAnchorView(videoView);


                    } else {
                        Log.e("API Error", "Failed to fetch video data");
                    }
                }
                @Override
                public void onFailure(Call<Video> call, Throwable t) {
                    Log.e("API Error", "Failed to make API call", t);
                }
            });


        } else {
            Log.d("Received Value", "Key not found");
        }





        //VideoView videoView = findViewById(R.id.video_view);
        //String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video1;
        //Uri uri = Uri.parse(videoPath);



        ImageView backText = findViewById(R.id.Back);
        backText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VideoActivity.this, CoachVideos.class);
                startActivity(intent);
            }
        });
    }
}
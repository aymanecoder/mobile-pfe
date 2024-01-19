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
import com.example.mobile_pfe.Model.Video;
import com.example.mobile_pfe.sevices.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoActivity extends AppCompatActivity {

    private VideoView videoView;
    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video);

        videoView = findViewById(R.id.video_view);
        mediaController = new MediaController(this);

        Intent intent = getIntent();
        if (intent.hasExtra("VideoId")) {
            int videoId = intent.getIntExtra("VideoId", 1);

            RetrofitInstance retrofitInstance = new RetrofitInstance();
            UserService userService = retrofitInstance.getRetrofitInstance().create(UserService.class);

            Call<Video> call = userService.getVideoById(videoId);
            call.enqueue(new Callback<Video>() {
                @Override
                public void onResponse(Call<Video> call, Response<Video> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        Video video = response.body();
                        String videoUrl = video.getUrlVideo();

                        if (videoUrl != null && !videoUrl.isEmpty()) {
                            playVideo(videoUrl);
                        } else {
                            Toast.makeText(VideoActivity.this, "Invalid video URL", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(VideoActivity.this, "Failed to fetch video data", Toast.LENGTH_SHORT).show();
                        Log.e("API Error", "Failed to fetch video data");
                    }
                }

                @Override
                public void onFailure(Call<Video> call, Throwable t) {
                    Toast.makeText(VideoActivity.this, "Failed to make API call", Toast.LENGTH_SHORT).show();
                    Log.e("API Error", "Failed to make API call", t);
                }
            });
        } else {
            Log.d("Received Value", "Key not found");
        }

        ImageView backText = findViewById(R.id.Back);
        backText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                releaseResources();
                Intent intent = new Intent(VideoActivity.this, CoachVideos.class);
                startActivity(intent);
            }
        });
    }

    private void playVideo(String videoUrl) {
        Uri uri = Uri.parse(videoUrl);
        videoView.setVideoURI(uri);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

        videoView.setOnPreparedListener(mp -> {
            videoView.start(); // Start playing the video once it's prepared
        });

        videoView.setOnCompletionListener(mp -> {
            releaseResources(); // Release resources when the video completes
        });
    }

    private void releaseResources() {
        if (videoView != null) {
            videoView.stopPlayback();
        }

        if (mediaController != null) {
            mediaController.hide();
        }
    }
}

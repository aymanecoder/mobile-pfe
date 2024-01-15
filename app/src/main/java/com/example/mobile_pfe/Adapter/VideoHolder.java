package com.example.mobile_pfe.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_pfe.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class VideoHolder extends RecyclerView.ViewHolder{

    TextView videoTitle;
    TextView videoDuration;
    TextView videoViews;
    TextView videoLevel;
    ImageView image;


    public VideoHolder(@NonNull View itemView) {
        super(itemView);
        videoTitle = itemView.findViewById(R.id.video_title);
        videoDuration = itemView.findViewById(R.id.video_duration);
        videoViews = itemView.findViewById(R.id.video_views);
        videoLevel = itemView.findViewById(R.id.workout_level);
        image = itemView.findViewById(R.id.video_image);
    }
}

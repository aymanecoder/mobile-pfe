package com.example.mobile_pfe.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_pfe.Model.Video;
import com.example.mobile_pfe.R;

import java.util.ArrayList;
import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoHolder>{

    private List<Video> videosList;

    public VideoAdapter(List<Video> videosList) {
        this.videosList = videosList;
    }

    @NonNull
    @Override
    public VideoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.videos_list_item, parent, false);
        return new VideoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoHolder holder, int position) {
        Video video = videosList.get(position);

        holder.videoTitle.setText(video.getTitle());
        holder.videoDuration.setText(video.getDuration());
        holder.videoViews.setText(video.getViews());
        holder.videoLevel.setText(video.getLevel());
        holder.image.setImageResource(video.getImage());
    }

    @Override
    public int getItemCount() {
        return videosList.size();
    }
}

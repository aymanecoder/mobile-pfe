package com.example.mobile_pfe.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_pfe.model.Video;
import com.example.mobile_pfe.R;
import com.example.mobile_pfe.UI.VideoActivity;

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

        holder.videoTitle.setText(video.getTitre());
        holder.videoDuration.setText(video.getNumberOfTeam());
        holder.videoViews.setText("300");
        holder.videoLevel.setText(video.getDescription());
        holder.image.setImageResource(R.drawable.img);
        int id = video.getId();

        Button playButton = holder.itemView.findViewById(R.id.play_button);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), VideoActivity.class);
                intent.putExtra("VideoId", id);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return videosList.size();
    }
}

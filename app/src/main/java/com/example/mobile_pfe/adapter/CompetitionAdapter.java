package com.example.mobile_pfe.Adapter;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mobile_pfe.R;
import com.example.mobile_pfe.model.Competition.Competition;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CompetitionAdapter extends RecyclerView.Adapter<CompetitionAdapter.CompetitionViewHolder>{


    private ArrayList<Competition> dataList;

    public CompetitionAdapter(ArrayList<Competition> dataList) {
        this.dataList = dataList;
    }

    public interface OnItemClickListener {
        void onItemClick(Competition competition);

        void onButtonClick(Competition competition);
    }

    private CompetitionAdapter.OnItemClickListener listener;

    public void setOnItemClickListener(CompetitionAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }


    @Override
    public CompetitionAdapter.CompetitionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_competition, parent, false);
        return new CompetitionAdapter.CompetitionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CompetitionAdapter.CompetitionViewHolder holder, int position) {
        holder.txtPostTitle.setText(dataList.get(position).getTitle());
        //holder.post_date.setText(dataList.get(position).getCreationDate());
        String competitionEndDate = dataList.get(position).getCreationDate(); // Assuming you have an endDate field
        setCountdownTimer(holder, competitionEndDate);
        holder.teams_count.setText(String.valueOf(dataList.get(position).getNbrTeams())+" teams");
        Glide.with(holder.itemView.getContext())
                .load(dataList.get(position).getLogoPath())
                .placeholder(R.drawable.notfound) // Placeholder image while loading
                .error(R.drawable.notfound) // Error image if it fails to load
                .into(holder.postImage);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class CompetitionViewHolder extends RecyclerView.ViewHolder {

        TextView txtPostTitle, teams_count,post_date;
        ImageView postImage;

        Button joinChallengeButton;

        CompetitionViewHolder(View itemView) {
            super(itemView);
            txtPostTitle = (TextView) itemView.findViewById(R.id.post_title);
            teams_count = (TextView) itemView.findViewById(R.id.teams_count);
            post_date = (TextView) itemView.findViewById(R.id.post_date);
            postImage = itemView.findViewById(R.id.post_image);

            joinChallengeButton = itemView.findViewById(R.id.join_challenge_button);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                        listener.onItemClick(dataList.get(getAdapterPosition()));
                    }
                }
            });

            joinChallengeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Handle button click here
                    if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                        listener.onButtonClick(dataList.get(getAdapterPosition()));
                    }
                }
            });
        }
    }

    private void setCountdownTimer(CompetitionViewHolder holder, String endDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Date endDateObj = sdf.parse(endDate);

            long currentTime = System.currentTimeMillis();
            long endTime = endDateObj.getTime();

            long timeDifference = endTime - currentTime;

            // Create a countdown timer
            new CountDownTimer(timeDifference, 1000) {
                public void onTick(long millisUntilFinished) {
                    // Update the TextView with the remaining time
                    long seconds = millisUntilFinished / 1000;
                    long minutes = seconds / 60;
                    long hours = minutes / 60;

                    String timeRemaining = String.format("%02d:%02d:%02d",
                            hours % 24, minutes % 60, seconds % 60);

                    holder.post_date.setText(timeRemaining);
                }

                public void onFinish() {
                    holder.post_date.setText("Challenge started");
                }
            }.start();

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}

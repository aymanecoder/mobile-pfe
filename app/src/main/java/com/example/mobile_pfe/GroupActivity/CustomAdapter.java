package com.example.mobile_pfe.GroupActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_pfe.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private final Context context;

    private int[] teamImages;
    private ArrayList<String> teamNames;
    private ArrayList<String> teamNamesList;
    private OnItemClickListener listener;
    private int[] teamNumbers;


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public CustomAdapter(Context context, String[] teamNames, int[] teamImages,int[] teamNumbers) {
        this.context = context;
        this.teamImages = teamImages;
        this.teamNumbers = teamNumbers;
        this.teamNames = new ArrayList<>(Arrays.asList(teamNames));
        this.teamNamesList = new ArrayList<>(Arrays.asList(teamNames));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String teamName = teamNames.get(position);
        int teamImageRes = teamImages[position];
        int teamNumber = teamNumbers[position];
        holder.bindData(teamName, teamImageRes, teamNumber);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int clickedPosition = holder.getAdapterPosition();
                if (listener != null && clickedPosition != RecyclerView.NO_POSITION) {
                    listener.onItemClick(clickedPosition);
                    //Toast.makeText(context, "clickedPosition: " + clickedPosition, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listviewteam_activi1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return teamNames.size();
    }

    public void filter(String text) {
        teamNames.clear();

        if (text.isEmpty()) {
            teamNames.addAll(teamNamesList);
        } else {
            text = text.toLowerCase(Locale.getDefault());
            for (String team : teamNamesList) {
                if (team.toLowerCase(Locale.getDefault()).contains(text)) {
                    teamNames.add(team);
                }
            }
        }
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView teamNameTextView;
        private final ImageView teamImageView;
        private final TextView teammembersNumber;
        public ViewHolder(View itemView) {
            super(itemView);
            teamNameTextView = itemView.findViewById(R.id.team_a);
            teamImageView = itemView.findViewById(R.id.profile_pic1);
            teammembersNumber = itemView.findViewById(R.id.members);
        }

        public void bindData(String teamName, int teamImageRes,int teamNumber) {
            teamNameTextView.setText(teamName);
            //transforme the teamNumber to string
            teammembersNumber.setText(String.valueOf(teamNumber));
            teamImageView.setImageResource(teamImageRes);
        }
    }

    public void updateData(String[] newTeamNames, int[] newTeamImages , int[] newTeamNumbers) {
        teamNames.clear();
        teamNames.addAll(Arrays.asList(newTeamNames));
        teamImages = newTeamImages;
        teamNumbers = newTeamNumbers;
        notifyDataSetChanged();
    }
}


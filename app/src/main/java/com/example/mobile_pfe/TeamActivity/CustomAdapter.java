package com.example.mobile_pfe.TeamActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mobile_pfe.R;
import com.example.mobile_pfe.TeamActivity.TeamResponse;
import com.example.mobile_pfe.TeamActivity.CustomAdapter.OnItemClickListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private final Context context;

    private final int[] teamImages;
    private final ArrayList<String> teamNames;
    private final ArrayList<String> teamNamesList;
    private OnItemClickListener listener;


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public CustomAdapter(Context context, String[] teamNames, int[] teamImages) {
        this.context = context;
        this.teamImages = teamImages;
        this.teamNames = new ArrayList<>(Arrays.asList(teamNames));
        this.teamNamesList = new ArrayList<>(Arrays.asList(teamNames));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String teamName = teamNames.get(position);
        int teamImageRes = teamImages[position];
        holder.bindData(teamName, teamImageRes);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int clickedPosition = holder.getAdapterPosition();
                if (listener != null && clickedPosition != RecyclerView.NO_POSITION) {
                    listener.onItemClick(clickedPosition);
                }
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listviewteam_activi, parent, false);
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

        public ViewHolder(View itemView) {
            super(itemView);
            teamNameTextView = itemView.findViewById(R.id.team_a);
            teamImageView = itemView.findViewById(R.id.profile_pic1);
        }

        public void bindData(String teamName, int teamImageRes) {
            teamNameTextView.setText(teamName);
            teamImageView.setImageResource(teamImageRes);
        }
    }
}


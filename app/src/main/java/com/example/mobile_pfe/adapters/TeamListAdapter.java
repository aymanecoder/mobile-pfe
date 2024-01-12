package com.example.mobile_pfe.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_pfe.R;
import com.example.mobile_pfe.Model.TeamItem;

import java.util.List;

public class TeamListAdapter extends RecyclerView.Adapter<TeamListAdapter.TeamViewHolder> {

    private Context context;
    private List<TeamItem> teamList;

    public TeamListAdapter(Context context, List<TeamItem> teamList) {
        this.context = context;
        this.teamList = teamList;
    }

    @NonNull
    @Override
    public TeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_team, parent, false);
        return new TeamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamViewHolder holder, int position) {
        TeamItem team = teamList.get(position);
        holder.team1Logo.setImageResource(team.getSportifLogo());
        holder.team1Name.setText(team.getSportifName());
        // Set click listener or any other operations related to each item
    }

    @Override
    public int getItemCount() {
        return teamList.size();
    }

    public static class TeamViewHolder extends RecyclerView.ViewHolder {
        ImageView team1Logo;
        TextView team1Name;

        public TeamViewHolder(@NonNull View itemView) {
            super(itemView);
            team1Logo = itemView.findViewById(R.id.SportifLogo);
            team1Name = itemView.findViewById(R.id.SportifName);
        }
    }
}

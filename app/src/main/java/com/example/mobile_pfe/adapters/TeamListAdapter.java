package com.example.mobile_pfe.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mobile_pfe.Network.RetrofitInstance;
import com.example.mobile_pfe.R;
import com.example.mobile_pfe.TeamActivity.TeamDetails;
import com.example.mobile_pfe.model.MatchItem;
import com.example.mobile_pfe.model.Sportif;
import com.example.mobile_pfe.model.TeamItem;

import java.util.List;

public class TeamListAdapter extends RecyclerView.Adapter<TeamListAdapter.TeamViewHolder> {

    private Context context;
    private List<Sportif> teamList;

    public TeamListAdapter(Context context, List<Sportif> teamList) {
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
        Sportif sportif = teamList.get(position);

        holder.team1Name.setText(sportif.getFirstName() +" "+ sportif.getLastName());
        if (sportif.getPicturePath()!=null) {
            Glide.with(context)
                    .load(sportif.getPicturePath().replace("localhost", RetrofitInstance.BASE_URL_IP))
                    .apply(RequestOptions.circleCropTransform())
                    .into(holder.team1Logo);
        }
        else{
            holder.team1Logo.setImageResource(R.drawable.images);
        }
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

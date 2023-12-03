package com.example.mobile_pfe.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_pfe.R;
import com.example.mobile_pfe.model.Equipe;

import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamHolder> {

    private List<Equipe> teamList;

    public TeamAdapter(List<Equipe> teamList) {
        this.teamList = teamList;
    }

    @NonNull
    @Override
    public TeamHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_team_item, parent, false);
        return new TeamHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamHolder holder, int position) {

        Equipe employee = teamList.get(position);
        holder.name.setText(employee.getName());
        holder.body.setText(employee.getBody());
        holder.id.setText(employee.getId());
    }

    @Override
    public int getItemCount() {
        return teamList.size();
    }
}
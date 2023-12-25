package com.example.mobile_pfe.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_pfe.Model.Coach;
import com.example.mobile_pfe.R;

import java.util.List;

public class CoachAdapter extends RecyclerView.Adapter<CoachHolder>{

    private List<Coach> coachesList;

    public CoachAdapter(List<Coach> coachesList) {
        this.coachesList = coachesList;
    }

    @NonNull
    @Override
    public CoachHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.coaches_list_item, parent, false);
        return new CoachHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoachHolder holder, int position) {
       Coach coach = coachesList.get(position);

        holder.name.setText(coach.getName());
        holder.rating.setText(coach.getRating());
        holder.description.setText(coach.getDescription());
        holder.image.setImageResource(coach.getImage());
    }


    @Override
    public int getItemCount() {
        return coachesList.size();
    }
}

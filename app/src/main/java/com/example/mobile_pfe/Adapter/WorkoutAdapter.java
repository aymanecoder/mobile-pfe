package com.example.mobile_pfe.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_pfe.model.Workout;
import com.example.mobile_pfe.R;

import java.util.List;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutHolder>{

    private List<Workout> workoutsList;

    public WorkoutAdapter(List<Workout> workoutsList) {
        this.workoutsList = workoutsList;
    }

    @NonNull
    @Override
    public WorkoutHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.workouts_list_item, parent, false);
        return new WorkoutHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutHolder holder, int position) {
        Workout workout = workoutsList.get(position);

        holder.name.setText(workout.getName());
        holder.description.setText(workout.getDescription());
        holder.loaded_time.setText(workout.getLoaded_time());
    }

    @Override
    public int getItemCount() {
        return workoutsList.size();
    }
}

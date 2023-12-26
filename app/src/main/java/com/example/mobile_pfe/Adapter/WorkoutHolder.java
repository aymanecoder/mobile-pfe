package com.example.mobile_pfe.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_pfe.R;


public class WorkoutHolder extends RecyclerView.ViewHolder {

    TextView name;
    TextView description;
    TextView loaded_time;

    public WorkoutHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.workout_name);
        description = itemView.findViewById(R.id.workout_description);
        loaded_time = itemView.findViewById(R.id.loaded_time);
    }


}

package com.example.mobile_pfe.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_pfe.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class CoachHolder extends RecyclerView.ViewHolder{

    TextView name;
    TextView rating;
    TextView description;
    CircleImageView image;


    public CoachHolder(@NonNull View itemView) {

        super(itemView);
        name = itemView.findViewById(R.id.coach_name);
        rating = itemView.findViewById(R.id.coach_rating);
        description = itemView.findViewById(R.id.coach_description);
        image = itemView.findViewById(R.id.coach_image);

    }
}

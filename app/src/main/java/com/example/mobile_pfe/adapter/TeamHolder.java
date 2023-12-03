package com.example.mobile_pfe.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_pfe.R;

public class TeamHolder extends RecyclerView.ViewHolder{
    TextView name, body, id;

    public TeamHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.TeamListItem_name);
        body = itemView.findViewById(R.id.TeamListItem_body);
        id = itemView.findViewById(R.id.TeamListItem_id);
    }
}

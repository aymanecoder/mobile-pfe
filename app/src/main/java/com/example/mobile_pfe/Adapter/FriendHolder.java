package com.example.mobile_pfe.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_pfe.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class FriendHolder extends RecyclerView.ViewHolder{

    TextView name;
    CircleImageView image;

    public FriendHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.user_name);
        image = itemView.findViewById(R.id.user_image);
    }
}

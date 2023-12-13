package com.example.mobile_pfe.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_pfe.R;
import com.example.mobile_pfe.model.User;

import java.util.List;

public class FriendAdapter extends RecyclerView.Adapter<FriendHolder> {

    private List<User> friendsList;

    public FriendAdapter(List<User> friendsList) {
        this.friendsList = friendsList;
    }

    @NonNull
    @Override
    public FriendHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new FriendHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendHolder holder, int position) {


    }


    @Override
    public int getItemCount() {
        return friendsList.size();
    }
}

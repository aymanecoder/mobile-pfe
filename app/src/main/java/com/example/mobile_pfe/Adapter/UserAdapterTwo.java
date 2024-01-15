package com.example.mobile_pfe.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_pfe.model.User;
import com.example.mobile_pfe.R;

import java.util.List;

public class UserAdapterTwo extends RecyclerView.Adapter<UserHolder> {

    private List<User> usersList;

    public UserAdapterTwo(List<User> usersList) {
        this.usersList = usersList;
    }
    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.friends_list_item, parent, false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {

        User user = usersList.get(position);

        holder.name.setText(user.getName());
        holder.image.setImageResource(user.getImage());
    }

    @Override
    public int getItemCount() { return usersList.size();
    }
}

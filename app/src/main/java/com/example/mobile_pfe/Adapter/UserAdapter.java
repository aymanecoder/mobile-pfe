package com.example.mobile_pfe.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_pfe.GroupChatActivity.GroupChatActivity;
import com.example.mobile_pfe.R;
import com.example.mobile_pfe.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserHolder> {

    private List<User> friendsList;

    public UserAdapter(List<User> friendsList) {
        this.friendsList = friendsList;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        User user = friendsList.get(position);

        holder.name.setText(user.getName());
        holder.image.setImageResource(user.getImage());
        // Set a click listener for each item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle click event here
                //navigateToGroupChatActivity(user ,holder);
            }
        });
    }
    private void navigateToGroupChatActivity(User user,  UserHolder holder) {
        // Create an Intent to start the GroupChat activity
        Intent intent = new Intent(holder.itemView.getContext(), GroupChatActivity.class);

        // Pass any necessary data to the next activity (if needed)
        intent.putExtra("user_name", user.getName());

        // Start the activity
        holder.itemView.getContext().startActivity(intent);
        ((Activity) holder.itemView.getContext()).finish();
    }


    @Override
    public int getItemCount() {
        return friendsList.size();
    }
}

package com.example.mobile_pfe.Adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.mobile_pfe.model.User1.User;
import com.example.mobile_pfe.R;

import java.util.List;

public class FriendAdapter extends RecyclerView.Adapter<FriendHolder> {

    private List<User> usersList;

    public FriendAdapter(List<User> usersList) {
        this.usersList = usersList;
    }
    @NonNull
    @Override
    public FriendHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.friends_list_item, parent, false);
        return new FriendHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendHolder holder, int position) {
        User user = usersList.get(position);

        holder.name.setText(user.getFirstName());

        String picturePath = user.getPicturePath();

        if (picturePath != null && !picturePath.isEmpty()) {

            Glide.with(holder.itemView.getContext())
                    .load(picturePath)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            Log.e("Glide", "Image load failed: " + e);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            Log.d("Glide", "Image loaded successfully");
                            return false;
                        }
                    })
                    .placeholder(R.drawable.user1)
                    .error(R.drawable.user1)
                    .into(holder.image);
        } else {
            holder.image.setImageResource(R.drawable.user1);
        }
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }
}

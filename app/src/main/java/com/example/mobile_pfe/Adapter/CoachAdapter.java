package com.example.mobile_pfe.Adapter;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.mobile_pfe.Model.Coach;
import com.example.mobile_pfe.R;
import com.example.mobile_pfe.UI.CoachContent;
import com.example.mobile_pfe.Model.User1.User;

import java.util.List;

public class CoachAdapter extends RecyclerView.Adapter<CoachHolder>{

    private List<User> coachesList;

    public CoachAdapter(List<User> coachesList) {
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
       User coach = coachesList.get(position);

        holder.name.setText(coach.getFirstName());
        //holder.rating.setText(coach.getRating());
        //holder.description.setText(coach.getDescription());

        holder.rating.setText("5");
        holder.description.setText("Experience 3 years");


        String picturePath = coach.getPicturePath();

        if (picturePath != null && !picturePath.isEmpty()) {

            Glide.with(holder.itemView.getContext())
                    .load(picturePath)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object Model, Target<Drawable> target, boolean isFirstResource) {
                            Log.e("Glide", "Image load failed: " + e);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object Model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
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

        Button moreButton = holder.itemView.findViewById(R.id.more_button);
        moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CoachContent.class);
                intent.putExtra("CoachId", (int) coach.getId());
                v.getContext().startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return coachesList.size();
    }
}

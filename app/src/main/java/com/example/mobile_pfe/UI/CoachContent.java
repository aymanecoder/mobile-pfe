package com.example.mobile_pfe.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.mobile_pfe.Model.User1.User;
import com.example.mobile_pfe.Network.RetrofitInstance;
import com.example.mobile_pfe.R;
import com.example.mobile_pfe.programActivity.ListCompetitionActivity;
import com.example.mobile_pfe.programActivity.ListProgramActivity;
import com.example.mobile_pfe.sevices.UserService;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoachContent extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coach_content);

        CircleImageView image = findViewById(R.id.coach_image);
        TextView coachName = findViewById(R.id.coach_name);

        Intent intent = getIntent();
        int CoachId = intent.getIntExtra("CoachId", 2);

        RetrofitInstance retrofitInstance = new RetrofitInstance();
        UserService userService = retrofitInstance.getRetrofitInstance().create(UserService.class);

        Call<User> call = userService.getCoachById(CoachId);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    User coach = response.body();

                    String picture = coach.getPicturePath();
                    String fullName = coach.getFirstName() + " " + coach.getLastName();

                    coachName.setText(fullName);
                    if (picture != null && !picture.isEmpty()) {

                        Glide.with(image)
                                .load(picture)
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
                                .placeholder(R.drawable.notfound)
                                .error(R.drawable.notfound)
                                .into(image);
                    } else {
                        image.setImageResource(R.drawable.notfound);
                    }



                } else {

                    Log.e("API Error", "Failed to fetch video data");
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("API Error", "Failed to make API call", t);
            }
        });



        ScrollView scrollView = findViewById(R.id.scrollView);
        scrollView.setScrollbarFadingEnabled(true);



        TextView back = findViewById(R.id.Back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CoachContent.this, FindCoaches.class);
                startActivity(intent);

            }
        });

        ImageView workout = findViewById(R.id.workout_image);

        workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CoachContent.this, ListProgramActivity.class);
                startActivity(intent);

            }
        });


        ImageView nutrition = findViewById(R.id.nutrition_image);
        nutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CoachContent.this, ListProgramActivity.class);
                startActivity(intent);

            }
        });

        ImageView videos = findViewById(R.id.videos_image);
        videos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CoachContent.this, CoachVideos.class);
                startActivity(intent);

            }
        });

        ImageView challenges = findViewById(R.id.challenge_image);
        challenges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CoachContent.this, ListCompetitionActivity.class);
                startActivity(intent);
            }
        });
    }

}
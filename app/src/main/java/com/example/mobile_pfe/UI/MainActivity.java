package com.example.mobile_pfe.UI;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.example.mobile_pfe.GroupActivity.GroupActivity;
import com.example.mobile_pfe.model.Profile;
import com.example.mobile_pfe.Network.RetrofitInstance;
import com.example.mobile_pfe.R;
import com.example.mobile_pfe.TeamActivity.TeamActivity;
import com.example.mobile_pfe.matchActivities.ShowMatches;
import com.example.mobile_pfe.profileActivities.metricprofil;
import com.example.mobile_pfe.programActivity.ListCompetitionActivity;
import com.example.mobile_pfe.programActivity.ListProgramActivity;
import com.example.mobile_pfe.sevices.ProfilService;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Profile profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


      getUserProfile();

        CircleImageView userImage = findViewById(R.id.user_image);

        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event, for example, navigate to another activity
                Intent intent = new Intent(MainActivity.this, metricprofil.class);

                intent.putExtra("profile", profile);
                startActivity(intent);
            }
        });

        TextView myFriends = findViewById(R.id.my_friends);
        myFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView , FriendsFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name")
                        .commit();

            }
        });


        TextView myCoaches = findViewById(R.id.myCoaches);
        myCoaches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView , CoachesFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name")
                        .commit();

            }
        });

        TextView myPrograms = findViewById(R.id.myPrograms);
        myPrograms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView , ProgramsFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name")
                        .commit();

            }
        });

        TextView myTeams = findViewById(R.id.myTeams);
        myTeams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView , TeamsFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name")
                        .commit();

            }
        });

        TextView myGroups = findViewById(R.id.myGroups);
        myGroups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView , GroupsFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name")
                        .commit();

            }
        });

        TextView myMatches = findViewById(R.id.myMatches);
        myMatches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView , MatchesFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name")
                        .commit();

            }
        });

        RelativeLayout friendsRelativeLayout = findViewById(R.id.friends);
        friendsRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FindFriends.class);
                startActivity(intent);
            }
        });

        ImageButton friendsButton = findViewById(R.id.friends_btn_img);
        TextView  friendsTextView = findViewById(R.id.friends_textView);
        RelativeLayout coachesRelativeLayout = findViewById(R.id.friends);
        coachesRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FindFriends.class);
                startActivity(intent);
            }
        });

        friendsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FindFriends.class);
                startActivity(intent);
            }
        });

        friendsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FindFriends.class);
                startActivity(intent);
            }
        });

        ImageButton coachesButton = findViewById(R.id.coaches_btn_img);
        coachesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FindCoaches.class);
                startActivity(intent);
            }
        });

        ImageButton programsButton = findViewById(R.id.programs_btn_img);
        programsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListProgramActivity.class);
                startActivity(intent);
            }
        });


        RelativeLayout matchsLayout = findViewById(R.id.matchs);
        matchsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event, e.g., start the ShowMatches activity
                Intent intent = new Intent(MainActivity.this, ShowMatches.class);
                startActivity(intent);
            }
        });

        RelativeLayout teamsLayout = findViewById(R.id.teams);

        teamsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TeamActivity", "TeamActivity: Role +" );

                // Handle the click event, e.g., start the TeamActivity activity
                Intent intent = new Intent(MainActivity.this, TeamActivity.class);
                startActivity(intent);
            }
        });
        RelativeLayout groupsLayout = findViewById(R.id.groups);

        groupsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("GroupActivity", "GroupActivity: Role +" );
                // Handle the click event, e.g., start the GroupActivity activity
                Intent intent = new Intent(MainActivity.this, GroupActivity.class);
                startActivity(intent);
            }
        });

        RelativeLayout challengesLayout = findViewById(R.id.challenges);

        challengesLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event, e.g., start the ShowMatches activity
                Intent intent = new Intent(MainActivity.this, ListCompetitionActivity.class);
                startActivity(intent);
            }
        });


    }


    @Override
    protected void onResume() {
        super.onResume();

        // API call when the activity is resumed (e.g., when you navigate back to it)
        getUserProfile();
    }

    private void getUserProfile() {
        ProfilService service = RetrofitInstance.getRetrofitInstance().create(ProfilService.class);

        Call<Profile> call = service.getUserProfile();

        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                profile = response.body();
                Log.wtf("Response", " " + profile);
                if (profile != null) {
                    generateProfileData(profile);
                } else {
                    Log.e("Response", "Profile data is null");
                    Toast.makeText(MainActivity.this, "Profile data is null", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                System.out.println("get all errors");
                t.printStackTrace();
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generateProfileData(Profile profile) {
        TextView userName = findViewById(R.id.user_name);
        CircleImageView userImage = findViewById(R.id.user_image);

        // Set user name
        userName.setText(profile.getFirstName()+" "+profile.getLastName());

        // Load user image with Glide
        Glide.with(this)
                .load(profile.getPicturePath()) // Replace with the actual URL or resource of the user image
                .placeholder(R.drawable.notfound) // Placeholder image while loading
                .error(R.drawable.notfound) // Image to display in case of loading error
                .into(userImage);
    }

}

package com.example.mobile_pfe.UI;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.mobile_pfe.GroupActivity.GroupActivity;
import com.example.mobile_pfe.R;
import com.example.mobile_pfe.TeamActivity.TeamActivity;
import com.example.mobile_pfe.matchActivities.ShowMatches;
import com.example.mobile_pfe.programActivity.ListProgramActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                // Handle the click event, e.g., start the ShowMatches activity
                Intent intent = new Intent(MainActivity.this, TeamActivity.class);
                startActivity(intent);
            }
        });
        RelativeLayout groupsLayout = findViewById(R.id.groups);

        groupsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event, e.g., start the ShowMatches activity
                Intent intent = new Intent(MainActivity.this, GroupActivity.class);
                startActivity(intent);
            }
        });

    }
}

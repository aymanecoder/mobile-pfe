package com.example.mobile_pfe.TeamActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mobile_pfe.Network.RetrofitInstance;
import com.example.mobile_pfe.R;
import com.example.mobile_pfe.databinding.ActivityListvieweachteamBinding;
import com.example.mobile_pfe.model.Sportif;

import java.util.List;

public class ItemActivity extends AppCompatActivity {

    private ActivityListvieweachteamBinding Binding;
    private List<Sportif> membersList;

    private boolean fromChooseTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Binding = ActivityListvieweachteamBinding.inflate(getLayoutInflater());
        setContentView(Binding.getRoot());

        fromChooseTeam = getIntent().getBooleanExtra("fromChooseTeam", false);
        Button yourButton = findViewById(R.id.join);

//         Conditionally show/hide the button based on the flag
        if (fromChooseTeam) {
            yourButton.setVisibility(View.GONE);
        } else {

            yourButton.setVisibility(View.VISIBLE);
        }Button yourButton2 = findViewById(R.id.cancel);

//         Conditionally show/hide the button based on the flag
        if (fromChooseTeam) {
            yourButton2.setVisibility(View.GONE);
        } else {

            yourButton2.setVisibility(View.VISIBLE);
        }

        // Retrieve data from intent extras
        int teamId = getIntent().getIntExtra("teamId", 0);
        String teamName = getIntent().getStringExtra("teamName");
        String pictureUrl = getIntent().getStringExtra("picture");
        membersList = (List<Sportif>) getIntent().getSerializableExtra("members");

        // Check if membersList is not null and not empty before accessing its elements
        if (membersList != null && !membersList.isEmpty()) {
            System.out.println("ItemActivity Members List: " + membersList.get(0).getFirstName());
            // Update the UI with the retrieved data
            Binding.teamA.setText(teamName);
            Binding.members.setText(membersList.size() + " Members");
            System.out.println("ItemActivity Members List: " + membersList.size());

            // You can update other views as needed
            // For example, updating the CircleImageView with the first member's image
            // Assuming that Sportif has a getPicturePath() method that returns the image URL

            // Load image using Glide
            Glide.with(this)
                    .load(pictureUrl != null ? pictureUrl.replace("localhost",  RetrofitInstance.BASE_URL_IP) : "")
                    .apply(RequestOptions.circleCropTransform())
                    .into(Binding.profilePic1);
            System.out.println("ItemActivity Members List: " + pictureUrl);

            // Setup the ListView with the members
             ListAdapter listAdapter = new ListAdapter(ItemActivity.this, membersList);
             Binding.lisvieww.setAdapter(listAdapter);
        } else {
            // Handle the case where membersList is null or empty
            // You might want to show a message or take appropriate action
            Binding.members.setText(membersList.size() + " Members");
            System.out.println("ItemActivity Members List is null or empty");
            Toast.makeText(ItemActivity.this, "ItemActivity Members List is null or empty", Toast.LENGTH_SHORT).show();

        }
    }
}

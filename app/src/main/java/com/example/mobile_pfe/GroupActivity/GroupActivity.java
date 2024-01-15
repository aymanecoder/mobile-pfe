package com.example.mobile_pfe.GroupActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.mobile_pfe.Network.RetrofitInstance;
import com.example.mobile_pfe.R;
import com.example.mobile_pfe.databinding.ActivityLisviewgroupBinding;
import com.example.mobile_pfe.databinding.ActivityLisviewteamBinding;
import com.example.mobile_pfe.model.Group;
import com.example.mobile_pfe.model.GroupDTO;
import com.example.mobile_pfe.sevices.GroupService;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupActivity extends AppCompatActivity {
    private CustomAdapter adapter;
    private String[] teamNames;
    private int[] teamImages = {R.drawable.teama, R.drawable.teamb, R.drawable.teamc, R.drawable.teame, R.drawable.teamu};

    @NonNull ActivityLisviewgroupBinding Binding;
    List<GroupDTO> groups;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_lisviewgroup);


        Binding = ActivityLisviewgroupBinding.inflate(getLayoutInflater());
        setContentView(Binding.getRoot());

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setItemAnimator(new SlideInUpAnimator());
        recyclerView.setHasFixedSize(true);
        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        adapter = new CustomAdapter(this, new String[0], new int[0], new int[0]);
        recyclerView.setAdapter(adapter);
        fetchGroups(); // Fetch groups from the server
        EditText searchEditText = findViewById(R.id.frame_11);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = editable.toString();
                adapter.filter(text);
            }
        });
        ImageButton ellipseButton = findViewById(R.id.ellipse_1);

        ellipseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(GroupActivity.this, listgroupactivity.class);
                startActivity(intent);
            }
        });
        adapter.setOnItemClickListener(new CustomAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(GroupActivity.this, ItemActivity.class);
                //Toast.makeText(GroupActivity.this, "position: " + position, Toast.LENGTH_SHORT).show();
                intent.putExtra("MEMBER_NAMES", teamNames);
                intent.putExtra("GROUPS", (Serializable) groups);
                intent.putExtra("TEAM_INDEX", position);
                startActivity(intent);

            }
        });

        Intent intent = getIntent();
        if (intent != null) {
            int selectedTeamIndex = intent.getIntExtra("TEAM_INDEX", -1);


            if (selectedTeamIndex != -1) {
                int[] imageId = {R.drawable.teama, R.drawable.teamb, R.drawable.teamc, R.drawable.teame};
                String[] teamName = {"Team A", "Team B", "Team C", "Team D", "Team E"};


                if (selectedTeamIndex >= 0 && selectedTeamIndex < imageId.length && selectedTeamIndex < teamNames.length) {

                    String selectedTeamName = teamName[selectedTeamIndex];
                    int selectedTeamImageId = imageId[selectedTeamIndex];


                    ImageView teamImageView = findViewById(R.id.profile_pic1);
                    if (teamImageView != null) {
                        teamImageView.setImageResource(selectedTeamImageId);
                    }


                    TextView teamNameTextView = findViewById(R.id.team_a);
                    teamNameTextView.setText(selectedTeamName);
                }
            }
        }
    }
    private void fetchGroups() {
        GroupService groupService = RetrofitInstance.getRetrofitInstance().create(GroupService.class);
        Call<List<GroupDTO>> call = groupService.getAllGroups();

        call.enqueue(new Callback<List<GroupDTO>>() {
            @Override
            public void onResponse(Call<List<GroupDTO>> call, Response<List<GroupDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    groups = response.body();
                    teamNames = new String[groups.size()];
                    int[] selectedTeamImages = new int[groups.size()];
                    int[] teamMembersNumber = new int[groups.size()];
                    Random random = new Random();

                    for (int i = 0; i < groups.size(); i++) {
                        GroupDTO group = groups.get(i);
                        teamNames[i] = group.getName();
                        selectedTeamImages[i] = teamImages[random.nextInt(teamImages.length)];
                        teamMembersNumber[i] = (group.getMembers() != null) ? group.getMembers().size() : 0;
                    }

                    // Update the adapter with fetched group names and randomly selected images
                    adapter.updateData(teamNames, selectedTeamImages, teamMembersNumber);
                    // Here, you can use teamMembersNumber for further processing or display
                    // For example, logging the member counts
                    for (int count : teamMembersNumber) {
                        Log.d("GroupMemberCount", "Members: " + count);
                    }
                } else {
                    Toast.makeText(GroupActivity.this, "Failed to retrieve groups", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<GroupDTO>> call, Throwable t) {
                Log.e("Error", "onFailure Network error: " + t.getMessage());
                Toast.makeText(GroupActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
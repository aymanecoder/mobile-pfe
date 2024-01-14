package com.example.mobile_pfe.GroupActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.mobile_pfe.R;
import com.example.mobile_pfe.databinding.ActivityLisviewgroupBinding;
import com.example.mobile_pfe.databinding.ActivityLisviewteamBinding;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;
public class GroupActivity extends AppCompatActivity {
    private CustomAdapter adapter;

    @NonNull ActivityLisviewgroupBinding Binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String[] teamNames = {"Group A", "Group B", "Group C", "Group D", "Group E"};
        int[] teamImages = {R.drawable.teama, R.drawable.teamb, R.drawable.teamc, R.drawable.teame, R.drawable.teamu};
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lisviewgroup);
        Binding = ActivityLisviewgroupBinding.inflate(getLayoutInflater());
        setContentView(Binding.getRoot());

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setItemAnimator(new SlideInUpAnimator());
        recyclerView.setHasFixedSize(true);
        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        CustomAdapter adapter = new CustomAdapter(this, teamNames, teamImages);
        recyclerView.setAdapter(adapter);
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
}
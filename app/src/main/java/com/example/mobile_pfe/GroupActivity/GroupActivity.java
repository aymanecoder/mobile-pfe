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


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.mobile_pfe.R;

import com.example.mobile_pfe.GroupActivity.GroupActivity;
import com.example.mobile_pfe.GroupActivity.listGroupactivity;
import com.example.mobile_pfe.TeamActivity.CustomAdapter;
import com.example.mobile_pfe.TeamActivity.ItemActivity;
import com.example.mobile_pfe.databinding.ActivityListviewgroupBinding;

import java.util.ArrayList;

import com.example.mobile_pfe.databinding.ActivityLisviewgroupBinding;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class GroupActivity extends AppCompatActivity{
    ActivityListviewgroupBinding Binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String[] GroupNames = {"Group A", "Group B", "Group C", "Group D", "Group E"};
        int[] GroupImages = {R.drawable.teama, R.drawable.teamb, R.drawable.teamc, R.drawable.teame, R.drawable.teamu};
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lisviewgroup);
        Binding = ActivityListviewgroupBinding.inflate(getLayoutInflater());
        setContentView(Binding.getRoot());

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setItemAnimator(new SlideInUpAnimator());
        recyclerView.setHasFixedSize(true);
        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        CustomAdapter adapter = new CustomAdapter(this, GroupNames, GroupImages);
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

                Intent intent = new Intent(GroupActivity.this, listGroupactivity.class);
                startActivity(intent);
            }
        });
        adapter.setOnItemClickListener(new CustomAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(GroupActivity.this, ItemActivity.class);
                intent.putExtra("Group_INDEX", position);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        if (intent != null) {
            int selectedGroupIndex = intent.getIntExtra("Group_INDEX", -1);


            if (selectedGroupIndex != -1) {
                int[] imageId = {R.drawable.teama, R.drawable.teamb, R.drawable.teamc, R.drawable.teame};
                String[] GroupName = {"Group A", "Group B", "Group C", "Group D", "Group E"};


                if (selectedGroupIndex >= 0 && selectedGroupIndex < imageId.length && selectedGroupIndex < GroupNames.length) {

                    String selectedGroupName = GroupName[selectedGroupIndex];
                    int selectedGroupImageId = imageId[selectedGroupIndex];


                    ImageView GroupImageView = findViewById(R.id.profile_pic1);
                    if (GroupImageView != null) {
                        GroupImageView.setImageResource(selectedGroupImageId);
                    }


                    TextView GroupNameTextView = findViewById(R.id.team_a);
                    GroupNameTextView.setText(selectedGroupName);
                }
            }
        }
    }
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Binding = ActivityListviewgroupBinding.inflate(getLayoutInflater());
//        setContentView(Binding.getRoot());
//
//
//        int[] imageId={R.drawable.d,R.drawable.d,R.drawable.e ,
//                R.drawable.g ,R.drawable.m,R.drawable.a };
//        String[] name={"Alex","Ayman Biti","Boutaib Mohamed","ZOZO YASSINE","MOMO LGHISSI","KOKO ADIL"};
//
//        ArrayList<User> userArrayList = new  ArrayList<>();
//        for(int i=0;i<6;i++)
//        {
//            User user = new User(name[i],imageId[i]);
//            userArrayList.add(user);
//        }
//
//        ListAdapter listAdapter = new ListAdapter(GroupActivity.this, userArrayList);
//
//        Binding.lisvieww.setAdapter(listAdapter);
//        Binding.lisvieww.setClickable(true);
//
//
//    }

}





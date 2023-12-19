package com.example.mobile_pfe.TeamActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile_pfe.R;


import java.util.ArrayList;

import com.example.mobile_pfe.databinding.ActivityListvieweachteamBinding;

public class ItemActivity extends AppCompatActivity {
    ActivityListvieweachteamBinding Binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Binding = ActivityListvieweachteamBinding.inflate(getLayoutInflater());
        setContentView(Binding.getRoot());


        int[] imageId = {R.drawable.d, R.drawable.d, R.drawable.e,
                R.drawable.g, R.drawable.m, R.drawable.a};
        String[] name = {"Alex", "Ayman Biti", "Boutaib Mohamed", "ZOZO YASSINE", "MOMO LGHISSI", "KOKO ADIL"};

        ArrayList<User> userArrayList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            User user = new User(name[i], imageId[i]);
            userArrayList.add(user);
        }

        ListAdapter listAdapter = new ListAdapter(ItemActivity.this, userArrayList);

        Binding.lisvieww.setAdapter(listAdapter);
        Binding.lisvieww.setClickable(true);

        Button cancelButton = findViewById(R.id.button3);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });


    }
}








package com.example.mobile_pfe.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_pfe.Adapter.CoachAdapter;
import com.example.mobile_pfe.model.Coach;
import com.example.mobile_pfe.R;

import java.util.ArrayList;

public class FindCoaches extends AppCompatActivity {
    private ArrayList<Coach> coachesList = new ArrayList<>();

    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_coaches);

        fillFriendsList();

        recyclerView = findViewById(R.id.CoachesList_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CoachAdapter adapter = new CoachAdapter(coachesList);
        recyclerView.setAdapter(adapter);

        TextView backText = findViewById(R.id.Back);
        backText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindCoaches.this, MainActivity.class);
                startActivity(intent);
            }
        });




    }

    private void fillFriendsList(){
        coachesList.add(new Coach("Will Smith","3","Experience 3 years", R.drawable.coach2));
        coachesList.add(new Coach("Clarice Starling","5","Experience 4 years", R.drawable.profile2));
        coachesList.add(new Coach("justin Timberlake","4","Experience 5 years", R.drawable.profile1));
        coachesList.add(new Coach("Clarice Starling","3","Experience 2 years", R.drawable.profile1));
        coachesList.add(new Coach("Clarice Starling","3","Experience 1 years", R.drawable.profile1));
    }
}

package com.example.mobile_pfe.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_pfe.Adapter.UserAdapterTwo;
import com.example.mobile_pfe.model.User;
import com.example.mobile_pfe.R;

import java.util.ArrayList;

public class FindFriends extends AppCompatActivity {

    private ArrayList<User> usersList = new ArrayList<>();

    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_friends);

        fillFriendsList();

        recyclerView = findViewById(R.id.UsersList_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        UserAdapterTwo adapter = new UserAdapterTwo(usersList);
        recyclerView.setAdapter(adapter);


        TextView backText = findViewById(R.id.Back);
        backText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindFriends.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }



    private void fillFriendsList(){
       // usersList.add(new User("Tongkun Lee", R.drawable.user1));
        //usersList.add(new User("Rehmem Khihal", R.drawable.user2));
       // usersList.add(new User("Fazur Nalim", R.drawable.user3));
       // usersList.add(new User("Boa Palegleam", R.drawable.user4));
       // usersList.add(new User("Gurkir Glorymore", R.drawable.user5));
    }
}

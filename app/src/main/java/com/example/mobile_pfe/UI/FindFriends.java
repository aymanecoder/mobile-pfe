package com.example.mobile_pfe.UI;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_pfe.Adapter.UserAdapter;
import com.example.mobile_pfe.Adapter.UserAdapterTwo;
import com.example.mobile_pfe.Model.User;
import com.example.mobile_pfe.R;

import java.util.ArrayList;

public class FindFriends extends AppCompatActivity {

    private ArrayList<User> usersList = new ArrayList<>();

    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_friends);

        recyclerView = findViewById(R.id.UsersList_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        UserAdapterTwo adapter = new UserAdapterTwo(usersList);
        recyclerView.setAdapter(adapter);

        fillFriendsList();
    }

    private void fillFriendsList(){
        usersList.add(new User("Clarice Starling", R.drawable.profile1));
        usersList.add(new User("Clarice Starling", R.drawable.profile1));
        usersList.add(new User("Clarice Starling", R.drawable.profile1));
        usersList.add(new User("Clarice Starling", R.drawable.profile1));
        usersList.add(new User("Clarice Starling", R.drawable.profile1));
    }
}

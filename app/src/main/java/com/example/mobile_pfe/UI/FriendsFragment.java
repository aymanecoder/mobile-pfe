package com.example.mobile_pfe.UI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_pfe.Adapter.UserAdapter;
import com.example.mobile_pfe.R;
import com.example.mobile_pfe.model.User;

import java.util.ArrayList;


public class FriendsFragment extends Fragment {

    private ArrayList<User> friendsList = new ArrayList<>();

    private RecyclerView recyclerView;

    public FriendsFragment() {

    }

    private void fillFriendsList(){
      //  friendsList.add(new User("Clarice Starling", R.drawable.profile2));
      //  friendsList.add(new User("Clarice Starling", R.drawable.profile2));
     //   friendsList.add(new User("Clarice Starling", R.drawable.profile2));
     //   friendsList.add(new User("Clarice Starling", R.drawable.profile2));
     //   friendsList.add(new User("Clarice Starling", R.drawable.profile2));
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fillFriendsList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_friends, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        UserAdapter adapter = new UserAdapter(friendsList);
        recyclerView.setAdapter(adapter);

        return rootView;
    }
}


package com.example.mobile_pfe.UI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mobile_pfe.Adapter.UserAdapter;
import com.example.mobile_pfe.model.User;
import com.example.mobile_pfe.R;

import java.util.ArrayList;


public class TeamsFragment extends Fragment {

    private ArrayList<User> teamsList = new ArrayList<>();

    private RecyclerView recyclerView;

    public TeamsFragment() {
        // Required empty public constructor
    }

    private void fillTeamsList(){
      //  teamsList.add(new User("Team A", R.drawable.team_a));
      //  teamsList.add(new User("Team A", R.drawable.team_a));
      //  teamsList.add(new User("Team A", R.drawable.team_a));
     //   teamsList.add(new User("Team A", R.drawable.team_a));
      //  teamsList.add(new User("Team B", R.drawable.team_b));
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fillTeamsList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_friends, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        UserAdapter adapter = new UserAdapter(teamsList);
        recyclerView.setAdapter(adapter);

        return rootView;
    }
}
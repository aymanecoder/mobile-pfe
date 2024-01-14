package com.example.mobile_pfe.UI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mobile_pfe.Adapter.GroupAdapter;
import com.example.mobile_pfe.model.User;
import com.example.mobile_pfe.R;

import java.util.ArrayList;


public class GroupsFragment extends Fragment {


    private ArrayList<User> groupsList = new ArrayList<>();

    private RecyclerView recyclerView;

    public GroupsFragment() {
        // Required empty public constructor
    }

   private void fillGroupsList(){
        //
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fillGroupsList();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_friends, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        GroupAdapter adapter = new GroupAdapter(groupsList);
        recyclerView.setAdapter(adapter);

        return rootView;
    }
}
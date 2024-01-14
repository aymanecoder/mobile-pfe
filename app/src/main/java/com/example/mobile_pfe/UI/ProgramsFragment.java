package com.example.mobile_pfe.UI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_pfe.Adapter.MyCoachAdapter;
import com.example.mobile_pfe.model.User;
import com.example.mobile_pfe.R;

import java.util.ArrayList;


public class ProgramsFragment extends Fragment {

    private ArrayList<User> coachesList = new ArrayList<>();

    private RecyclerView recyclerView;

    public ProgramsFragment() {

    }


    private void fillCoachesList(){
       // coachesList.add(new User("Jaden smith", R.drawable.coach2));
       // coachesList.add(new User("Jaden smith", R.drawable.coach2));
        //coachesList.add(new User("Jaden smith", R.drawable.coach2));
       // coachesList.add(new User("Jaden smith", R.drawable.coach2));
      //  coachesList.add(new User("Jaden smith", R.drawable.profile1));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fillCoachesList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_friends, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        MyCoachAdapter adapter = new MyCoachAdapter(coachesList);
        recyclerView.setAdapter(adapter);

        return rootView;
    }
}
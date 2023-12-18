package com.example.mobile_pfe.matchActivities;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.mobile_pfe.R;
import com.example.mobile_pfe.adapters.MatchListAdapter;
import com.example.mobile_pfe.model.MatchItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UpcomingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpcomingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UpcomingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UpcomingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UpcomingFragment newInstance(String param1, String param2) {
        UpcomingFragment fragment = new UpcomingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upcoming, container, false);

        ListView listView = view.findViewById(R.id.listView);
        listView.setDivider(null);

        // Create a list of matches
        List<MatchItem> matchList = new ArrayList<>();
        matchList.add(new MatchItem(R.drawable.team1_logo, "Team 1", R.drawable.team2_logo, "Team 2"));
        matchList.add(new MatchItem(R.drawable.team1_logo, "Team 1", R.drawable.team2_logo, "Team 2"));
        matchList.add(new MatchItem(R.drawable.team1_logo, "Team 1", R.drawable.team2_logo, "Team 2"));
        matchList.add(new MatchItem(R.drawable.team1_logo, "Team 1", R.drawable.team2_logo, "Team 2"));
        matchList.add(new MatchItem(R.drawable.team1_logo, "Team 1", R.drawable.team2_logo, "Team 2"));
        matchList.add(new MatchItem(R.drawable.team1_logo, "Team 1", R.drawable.team2_logo, "Team 2"));
        // Add more matches as needed...

        // Create and set the adapter
        MatchListAdapter adapter = new MatchListAdapter(requireContext(), matchList);
        listView.setAdapter(adapter);

        return view;
    }
}
package com.example.mobile_pfe.matchActivities;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mobile_pfe.Network.RetrofitInstance;
import com.example.mobile_pfe.R;
import com.example.mobile_pfe.TeamActivity.TeamDetails;
import com.example.mobile_pfe.adapters.TeamListAdapter;
import com.example.mobile_pfe.model.TeamItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Team1Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Team1Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String ARG_TEAM_LIST = "teamList";

    private TeamDetails teamList;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Team1Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
     * @return A new instance of fragment Team1Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Team1Fragment newInstance(TeamDetails teamList) {
        Team1Fragment fragment = new Team1Fragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_TEAM_LIST, (Serializable) teamList);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            teamList = (TeamDetails) getArguments().getSerializable(ARG_TEAM_LIST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_team1, container, false);
        TextView title= view.findViewById(R.id.teamName);
        ImageView logo= view.findViewById(R.id.teamLogo);


        RecyclerView listView = view.findViewById(R.id.listViewTeam);
        if (teamList != null) {
            // Create a list of matches
            title.setText(teamList.getTeamName());
            if (teamList.getLogoPath()!=null) {
                Glide.with(requireContext())
                        .load(teamList.getLogoPath().replace("localhost",  RetrofitInstance.BASE_URL_IP))
                        .apply(RequestOptions.circleCropTransform())
                        .into(logo);
            }else{
                logo.setImageResource(R.drawable.team);
            }

            // Add more matches as needed...

            // Set up the RecyclerView with a LinearLayoutManager
            LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
            listView.setLayoutManager(layoutManager);

            // Add dividers between items (optional)
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(listView.getContext(),
                    layoutManager.getOrientation());
            listView.addItemDecoration(dividerItemDecoration);

            // Create and set the adapter
            TeamListAdapter adapter = new TeamListAdapter(requireContext(), teamList.getMembers());
            listView.setAdapter(adapter);

            // Disable nested scrolling for the RecyclerView
            listView.setNestedScrollingEnabled(false);
            listView.setOnTouchListener(new ListView.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    int action = event.getAction();
                    switch (action) {
                        case MotionEvent.ACTION_DOWN:
                            // Disallow ScrollView to intercept touch events.
                            v.getParent().requestDisallowInterceptTouchEvent(true);
                            break;
                        case MotionEvent.ACTION_UP:
                            // Allow ScrollView to intercept touch events.
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                            break;
                    }
                    // Handle ListView touch events.
                    v.onTouchEvent(event);
                    return true;
                }
            });
        }
        return view;
    }

}
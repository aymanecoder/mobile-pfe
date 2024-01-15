package com.example.mobile_pfe.matchActivities;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobile_pfe.Network.RetrofitInstance;
import com.example.mobile_pfe.R;
import com.example.mobile_pfe.TeamActivity.CustomAdapter;
import com.example.mobile_pfe.TeamActivity.TeamActivity;
import com.example.mobile_pfe.TeamActivity.TeamDetails;
import com.example.mobile_pfe.adapters.MatchListAdapter;
import com.example.mobile_pfe.model.MatchItem;
import com.example.mobile_pfe.model.MatchResponse;
import com.example.mobile_pfe.sevices.MatchService;
import com.example.mobile_pfe.sevices.TeamService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UpcomingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpcomingFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private TextView matchTextView;
    private LinearLayout buttonsLayout;
    private List<MatchResponse> originalTeamDetailsList;
    private MatchListAdapter adapter ;

    public UpcomingFragment() {
        // Required empty public constructor
    }

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
        View view = inflater.inflate(R.layout.fragment_upcoming, container, false);
        matchTextView = requireActivity().findViewById(R.id.matchTextView);
        buttonsLayout = requireActivity().findViewById(R.id.buttonsLayout);
        showViews();
        ListView listView = view.findViewById(R.id.listView);
        listView.setDivider(null);

        MatchService matchService = RetrofitInstance.getMatchService();
        Call<List<MatchResponse>> call = matchService.getMatches();



        call.enqueue(new Callback<List<MatchResponse>>() {
            @Override
            public void onResponse(Call<List<MatchResponse>> call, Response<List<MatchResponse>> response) {
                if (response.isSuccessful()) {
                    List<MatchResponse> matchDetailsList = response.body();

                    // Filter matches with type UPCOMING
                    List<MatchResponse> upcomingMatches = new ArrayList<>();
                    for (MatchResponse matchDetails : matchDetailsList) {
                        if ("UPCOMING".equals(matchDetails.getTypeMatch())) {
                            upcomingMatches.add(matchDetails);
                        }
                    }

                    // Print filtered matches
                    for (MatchResponse matchDetails : upcomingMatches) {
                        System.out.println("Match ID: " + matchDetails.getId());
                        System.out.println("Match Title: " + matchDetails.getTitle());
                    }

                    originalTeamDetailsList = new ArrayList<>(upcomingMatches);
                    adapter = new MatchListAdapter(requireContext(), upcomingMatches);
                    listView.setAdapter(adapter);

                    // Set the click listener here when the adapter is initialized
                    adapter.setOnMoreButtonClickListener(new MatchListAdapter.OnMoreButtonClickListener() {
                        @Override
                        public void onMoreButtonClick(MatchResponse matchItem) {
                            // Hide the "Match" title and the LinearLayout with buttons
                            hideViews();

                            // Pass the entire MatchResponse object as a Serializable
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("matchResponse", matchItem);

                            // Create a new instance of ItemFragment and set arguments
                            ItemFragment itemFragment = new ItemFragment();
                            itemFragment.setArguments(bundle);

                            // Replace the current fragment with ItemFragment
                            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                            transaction.replace(R.id.fragment_container, itemFragment);
                            transaction.addToBackStack(null);
                            transaction.commit();
                        }
                    });

                    Toast.makeText(getActivity(), "Success to fetch upcoming match details", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Failed to fetch match details", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<MatchResponse>> call, Throwable t) {
                Toast.makeText(getActivity(), "Failed to connect to the server", Toast.LENGTH_SHORT).show();
            }
        });


        Button addMore = view.findViewById(R.id.addMatch);
        addMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                // Hide the "Match" title and the LinearLayout with buttons
                hideViews();

                // Create a new instance of AddMatchFragment
                AddMatchFragment addMatchFragment = new AddMatchFragment();

                // Replace the current fragment with AddMatchFragment
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, addMatchFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }

    private void hideViews() {
        // Delay the execution by 1 second (1000 milliseconds)
        long delayMillis = 10;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Code to hide views
                matchTextView.setVisibility(View.GONE);
                buttonsLayout.setVisibility(View.GONE);
            }
        }, delayMillis);
    }

    private void showViews() {
        matchTextView.setVisibility(View.VISIBLE);
        buttonsLayout.setVisibility(View.VISIBLE);
    }
}
package com.example.mobile_pfe.matchActivities;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobile_pfe.Network.RetrofitInstance;
import com.example.mobile_pfe.R;
import com.example.mobile_pfe.adapters.MatchCompletedAdapter;
import com.example.mobile_pfe.adapters.MatchListAdapter;
import com.example.mobile_pfe.model.MatchItem;
import com.example.mobile_pfe.model.MatchResponse;
import com.example.mobile_pfe.sevices.MatchService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CompletedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CompletedFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private TextView matchTextView;
    private LinearLayout buttonsLayout;

    private List<MatchResponse> originalTeamDetailsList;

    private MatchCompletedAdapter adapter;


    public CompletedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CompletedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CompletedFragment newInstance(String param1, String param2) {
        CompletedFragment fragment = new CompletedFragment();
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
        View view = inflater.inflate(R.layout.fragment_completed, container, false);

        matchTextView = requireActivity().findViewById(R.id.matchTextView);
        buttonsLayout = requireActivity().findViewById(R.id.buttonsLayout);
        showViews();
        ListView listView = view.findViewById(R.id.listViewCompleted);
        listView.setDivider(null);
        System.out.println("Im here1");

        MatchService matchService = RetrofitInstance.getMatchService();
        Call<List<MatchResponse>> call = matchService.getMatches();

        call.enqueue(new Callback<List<MatchResponse>>() {
            @Override
            public void onResponse(Call<List<MatchResponse>> call, Response<List<MatchResponse>> response) {
                if (response.isSuccessful()) {
                    System.out.println("Im here2");
                    List<MatchResponse> matchDetailsList = response.body();
                    System.out.println("Im here3");

                    // Filter matches with type UPCOMING
                    List<MatchResponse> upcomingMatches = new ArrayList<>();
                    for (MatchResponse matchDetails : matchDetailsList) {
                        if ("COMPLETED".equals(matchDetails.getTypeMatch())) {
                            upcomingMatches.add(matchDetails);
                        }
                    }

                    // Print filtered matches
                    for (MatchResponse matchDetails : upcomingMatches) {
                        System.out.println("Completed Match ID: " + matchDetails.getId());
                        System.out.println("Completed Match Title: " + matchDetails.getTitle());
                    }

                    originalTeamDetailsList = new ArrayList<>(upcomingMatches);
                    adapter = new MatchCompletedAdapter(requireContext(), upcomingMatches);
                    listView.setAdapter(adapter);

                    adapter.setOnMoreButtonClickListener(new MatchCompletedAdapter.OnMoreButtonClickListener() {
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
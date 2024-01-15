package com.example.mobile_pfe.matchActivities;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.mobile_pfe.Network.RetrofitInstance;
import com.example.mobile_pfe.R;
import com.example.mobile_pfe.TeamActivity.TeamActivity;
import com.example.mobile_pfe.TeamActivity.Teamprofilactivity;
import com.example.mobile_pfe.TeamActivity.listteamactivity;
import com.example.mobile_pfe.sevices.MatchRequest;
import com.example.mobile_pfe.sevices.MatchService;
import com.example.mobile_pfe.sevices.TeamService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChooseTeamFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChooseTeamFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ChooseTeamFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChooseTeamFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChooseTeamFragment newInstance(String param1, String param2) {
        ChooseTeamFragment fragment = new ChooseTeamFragment();
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
        View view = inflater.inflate(R.layout.fragment_choose_team, container, false);
        Button addExistingTeam = view.findViewById(R.id.AddExistingteams);
        addExistingTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //her switch from this fragment to TeamActivity
                Intent intent = new Intent(getActivity(), TeamActivity.class);
                intent.putExtra("fromChooseTeam", true);
                startActivity(intent);
            }
        });
        Button addNewTeams = view.findViewById(R.id.AddNewTeams);
        addNewTeams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //her switch from this fragment to TeamActivity
                Intent intent = new Intent(getActivity(), Teamprofilactivity.class);
                intent.putExtra("fromChooseTeam", true);
                startActivity(intent);
            }
        });


        Button saveAndContinue = view.findViewById(R.id.login3Button);
        saveAndContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MatchRepository matchRepository = MatchRepository.getInstance();
                MatchRequest matchRequest = matchRepository.getMatchRequest();
                matchRequest.setSport(1);
                matchRequest.setTypeMatch("UPCOMING");

                //her switch from this fragment to TeamActivity
                MatchService matchService = RetrofitInstance.getMatchService();

                // Use the modified createTeam method with Map<String, Integer> for members
                Call<ResponseBody> call = matchService.createMatch(matchRequest);

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getActivity(), "match details uploaded successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            // Handle unsuccessful response
                            Toast.makeText(getActivity(), "Failed to upload match details", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(getActivity(), "Failed to connect " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }


        });

        return view;
    }
}
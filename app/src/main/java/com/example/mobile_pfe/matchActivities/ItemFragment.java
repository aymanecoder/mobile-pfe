package com.example.mobile_pfe.matchActivities;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.example.mobile_pfe.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;

public class ItemFragment extends Fragment implements OnMapReadyCallback {

    private TextView team1NameTextView;
    private TextView team2NameTextView;

    private GoogleMap googleMap;
    private MapView mapView;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public ItemFragment() {
        // Required empty public constructor
    }

    public static ItemFragment newInstance(String param1, String param2) {
        ItemFragment fragment = new ItemFragment();
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
        View view = inflater.inflate(R.layout.fragment_item, container, false);

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                mapView = view.findViewById(R.id.mapView);
                mapView.onCreate(savedInstanceState);
                mapView.onResume();
                mapView.getMapAsync(ItemFragment.this);
            }
        });


        team1NameTextView = view.findViewById(R.id.team1Name);
        team2NameTextView = view.findViewById(R.id.team2Name);

        // Retrieve arguments
        Bundle args = getArguments();
        if (args != null) {
            // Get team names from arguments
            String team1Name = args.getString("team1Name", "");
            String team2Name = args.getString("team2Name", "");

            // Display team names in TextViews
            team1NameTextView.setText(team1Name);
            team2NameTextView.setText(team2Name);
        }

        return view;
    }

    @Override
    public void onMapReady(GoogleMap mMap) {
        googleMap = mMap;

        // You can customize the map settings here
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        // Add markers, set camera position, etc.
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mapView != null) {
            mapView.onDestroy();
        }
    }


}

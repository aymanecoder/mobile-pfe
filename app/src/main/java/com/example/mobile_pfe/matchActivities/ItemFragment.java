package com.example.mobile_pfe.matchActivities;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import com.example.mobile_pfe.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

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


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ItemFragment.
     */
    // TODO: Rename and change types and number of parameters

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

        // Add a marker for the field in Morocco
        String fieldAddress = "Avenue Abdelkrim El Khattabi, Marrakech, Morocco"; // Replace with the actual field address
        LatLng fieldLocation = getLocationFromAddress(fieldAddress);

        if (fieldLocation != null) {
            googleMap.addMarker(new MarkerOptions().position(fieldLocation).title("Field Location"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(fieldLocation, 15)); // Adjust zoom level as needed
        }
    }

    // Helper method to get LatLng from address
    private LatLng getLocationFromAddress(String strAddress) {
        Geocoder coder = new Geocoder(requireContext());
        List<Address> address;
        LatLng latLng = null;

        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            latLng = new LatLng(location.getLatitude(), location.getLongitude());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return latLng;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mapView != null) {
            mapView.onDestroy();
        }
    }


}

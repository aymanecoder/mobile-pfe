package com.example.mobile_pfe.matchActivities;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.viewmodel.CreationExtras;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mobile_pfe.Network.RetrofitInstance;
import com.example.mobile_pfe.R;
import com.example.mobile_pfe.model.MatchResponse;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CompletedItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CompletedItemFragment extends Fragment implements OnMapReadyCallback {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private GoogleMap googleMap;
    private MapView mapView;

    private ImageView team1Logo, team2Logo;

    private ImageView score1, score2;
    private TextView team1Name, team2Name;
    private TextView dateTextView;
    private TextView hourTextView;
    private TextView cityTextView;
    public CompletedItemFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CompletedItemFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CompletedItemFragment newInstance(String param1, String param2) {
        CompletedItemFragment fragment = new CompletedItemFragment();
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
        View view = inflater.inflate(R.layout.fragment_completed_item, container, false);
        // Find references to your views
        team1Logo = view.findViewById(R.id.team1Logo);
        team2Logo = view.findViewById(R.id.team2Logo);
        team1Name = view.findViewById(R.id.team1Name);
        team2Name = view.findViewById(R.id.team2Name);
        dateTextView = view.findViewById(R.id.date1);
        hourTextView = view.findViewById(R.id.hour1);
        cityTextView = view.findViewById(R.id.city1);

        Bundle bundle = getArguments();

        if (bundle != null) {
            MatchResponse matchResponse = (MatchResponse) bundle.getSerializable("matchResponse");

            System.out.println("matchResponse :"+ matchResponse);
            if (matchResponse != null && matchResponse.getTeams().size() >= 2) {
                // Create an instance of Team1Fragment and pass the teamList data
                Team1Fragment team1Fragment = Team1Fragment.newInstance(matchResponse.getTeams().get(0));
                // Use FragmentTransaction to add the fragment to the layout
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.completedyourFragment, team1Fragment)
                        .commit();

                Team1Fragment team2Fragment = Team1Fragment.newInstance(matchResponse.getTeams().get(1));
                // Use FragmentTransaction to add the fragment to the layout
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.completedyourFragment2, team2Fragment)
                        .commit();
            }
            System.out.println("matchResponse :"+ matchResponse);
            // Format and set date and time
            if (matchResponse.getDate() != null) {
                String formattedDate = formatDate(matchResponse.getDate());
                dateTextView.setText(formattedDate);

                String formattedTime = formatTime(matchResponse.getDate());
                hourTextView.setText(formattedTime);
            }

            if (matchResponse != null && matchResponse.getTeams().size() >= 2) {
                // Set team names
                team1Name.setText(matchResponse.getTeams().get(0).getTeamName());
                team2Name.setText(matchResponse.getTeams().get(1).getTeamName());

                // Load team logos using Glide
                loadTeamLogo(matchResponse.getTeams().get(0).getLogoPath(), team1Logo);
                loadTeamLogo(matchResponse.getTeams().get(1).getLogoPath(), team2Logo);
            } else {
                team1Name.setText("Not affected");
                team2Name.setText("Not affected");
                team1Logo.setImageResource(R.drawable.team1_logo);
                team2Logo.setImageResource(R.drawable.team2_logo);
            }



            // Set city
//            cityTextView.setText(matchResponse.getCity());
        }

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                mapView = view.findViewById(R.id.mapView1);
                mapView.onCreate(savedInstanceState);
                mapView.onResume();
                mapView.getMapAsync(CompletedItemFragment.this);
            }
        });


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
    @NonNull
    @Override
    public CreationExtras getDefaultViewModelCreationExtras() {
        return super.getDefaultViewModelCreationExtras();
    }

    private String formatDate(String inputDate) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S", Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat("EEEE, d MMMM yyyy", Locale.getDefault());

        try {
            Date date = inputFormat.parse(inputDate);
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return ""; // Handle parsing error
        }
    }

    private String formatTime(String inputDate) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S", Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat("h:mm a", Locale.getDefault());

        try {
            Date date = inputFormat.parse(inputDate);
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return ""; // Handle parsing error
        }
    }

    private void loadTeamLogo(String pictureUrl, ImageView imageView) {
        if (pictureUrl != null) {
            Glide.with(requireContext())
                    .load(pictureUrl.replace("localhost",  RetrofitInstance.BASE_URL_IP))
                    .apply(RequestOptions.circleCropTransform())
                    .into(imageView);
        } else {
            imageView.setImageResource(R.drawable.team1_logo);
        }
    }
}
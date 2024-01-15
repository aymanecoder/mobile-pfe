package com.example.mobile_pfe.matchActivities;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

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

public class ItemFragment extends Fragment implements OnMapReadyCallback {

    private ImageView team1Logo, team2Logo;
    private TextView team1Name, team2Name;
    private TextView dateTextView;
    private TextView hourTextView;
    private TextView cityTextView;
    private GoogleMap googleMap;
    private MapView mapView;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

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
            // Handle arguments if needed
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item, container, false);

        // Find references to your views
        team1Logo = view.findViewById(R.id.teamItem1Logo);
        team2Logo = view.findViewById(R.id.teamItem2Logo);
        team1Name = view.findViewById(R.id.teamItem1Name);
        team2Name = view.findViewById(R.id.teamItem2Name);
        dateTextView = view.findViewById(R.id.date);
        hourTextView = view.findViewById(R.id.hour);
        cityTextView = view.findViewById(R.id.city);

        Bundle bundle = getArguments();

        if (bundle != null) {
            MatchResponse matchResponse = (MatchResponse) bundle.getSerializable("matchResponse");
            System.out.println("matchResponse :"+ matchResponse);
            if (matchResponse != null && matchResponse.getTeams().size() >= 2) {
                // Create an instance of Team1Fragment and pass the teamList data
                Team1Fragment team1Fragment = Team1Fragment.newInstance(matchResponse.getTeams().get(0));
                // Use FragmentTransaction to add the fragment to the layout
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.yourFragment1, team1Fragment)
                        .commit();

                Team1Fragment team2Fragment = Team1Fragment.newInstance(matchResponse.getTeams().get(1));
                // Use FragmentTransaction to add the fragment to the layout
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.yourFragment2, team2Fragment)
                        .commit();
            }
            // Format and set date and time
            if (matchResponse.getDate() != null) {
                String formattedDate = formatDate(matchResponse.getDate());
                dateTextView.setText(formattedDate);

                String formattedTime = formatTime(matchResponse.getDate());
                hourTextView.setText(formattedTime);
            }
            else{
                System.out.println("matchResponse : null date"+ matchResponse);
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
                mapView = view.findViewById(R.id.mapViewItem);
                mapView.onCreate(savedInstanceState);
                mapView.onResume();
                mapView.getMapAsync(ItemFragment.this);
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

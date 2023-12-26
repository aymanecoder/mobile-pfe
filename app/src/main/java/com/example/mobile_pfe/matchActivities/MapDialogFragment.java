package com.example.mobile_pfe.matchActivities;

import android.app.Dialog;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

import com.example.mobile_pfe.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapDialogFragment extends DialogFragment implements OnMapReadyCallback {

    private MapView mapView;
    private EditText editTextMatchLocation;
    private OnLocationSelectedListener onLocationSelectedListener;
    private Geocoder geocoder;

    public interface OnLocationSelectedListener {
        void onLocationSelected(String selectedLocation);
    }

    public MapDialogFragment() {
        // Required empty public constructor
    }

    public void setOnLocationSelectedListener(OnLocationSelectedListener listener) {
        this.onLocationSelectedListener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map_dialog, container, false);

        // Initialize the MapView
        mapView = view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);

        editTextMatchLocation = view.findViewById(R.id.editTextMatchLocation);
        geocoder = new Geocoder(requireContext(), Locale.getDefault());

        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setTitle("Select Location");
        return dialog;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Set up the map
        LatLng defaultLocation = new LatLng(34.0522, -118.2437); // Default location (e.g., Los Angeles)
        googleMap.addMarker(new MarkerOptions().position(defaultLocation).title("Default Location"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(defaultLocation));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(10.0f)); // Adjust zoom level as needed

        // Add map click listener to handle user selection
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                // Handle the selected location
                // For example, update the EditText with the selected address
                String selectedLocation = getReadableAddress(latLng.latitude, latLng.longitude);
                editTextMatchLocation.setText(selectedLocation);

                // Notify the listener about the selected location
                if (onLocationSelectedListener != null) {
                    onLocationSelectedListener.onLocationSelected(selectedLocation);
                }
            }
        });
    }

    private String getReadableAddress(double latitude, double longitude) {
        try {
            Geocoder geocoder = new Geocoder(requireContext(), Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);

            if (addresses != null && addresses.size() > 0) {
                Address address = addresses.get(0);
                return address.getAddressLine(0);
            } else {
                return "Address not found";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error fetching address: " + e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            return "Unexpected error: " + e.getMessage();
        }
    }

}

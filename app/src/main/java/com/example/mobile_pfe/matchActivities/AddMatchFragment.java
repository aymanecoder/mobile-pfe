package com.example.mobile_pfe.matchActivities;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.mobile_pfe.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddMatchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddMatchFragment extends Fragment implements MapDialogFragment.OnLocationSelectedListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private EditText editTextDate;
    private EditText editTextMatchLocation;

    public AddMatchFragment() {
        // Required empty public constructor
    }

    public static AddMatchFragment newInstance(String param1, String param2) {
        AddMatchFragment fragment = new AddMatchFragment();
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
        View view = inflater.inflate(R.layout.fragment_add_match, container, false);

        editTextDate = view.findViewById(R.id.editTextDate);
        editTextMatchLocation = view.findViewById(R.id.editTextMatchLocation);

        // Get the drawable from resources
        Drawable icon = getResources().getDrawable(R.drawable.ic_calendar);

        // Set the bounds of the drawable (adjust the width and height as needed)
        icon.setBounds(0, 0, icon.getIntrinsicWidth() / 2, icon.getIntrinsicHeight() / 2);

        // Set the compound drawables for the EditText
        editTextDate.setCompoundDrawablesRelative(null, null, icon, null);

        // Set click listener for the EditText to show the map dialog
        editTextMatchLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMapDialog();
            }
        });

        return view;
    }

    @Override
    public void onLocationSelected(String selectedLocation) {
        // Update the EditText with the selected location
        editTextMatchLocation.setText(selectedLocation);
    }

    private void showMapDialog() {
        MapDialogFragment mapDialogFragment = new MapDialogFragment();
        mapDialogFragment.setOnLocationSelectedListener(this);
        mapDialogFragment.show(getChildFragmentManager(), "MapDialogFragment");
    }
}

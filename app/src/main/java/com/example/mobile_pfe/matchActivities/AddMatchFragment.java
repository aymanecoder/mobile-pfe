package com.example.mobile_pfe.matchActivities;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.mobile_pfe.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddMatchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddMatchFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddMatchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddMatchFragment.
     */
    // TODO: Rename and change types and number of parameters
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_match, container, false);
        EditText editTextDate = view.findViewById(R.id.editTextDate);

// Get the drawable from resources
        Drawable icon = getResources().getDrawable(R.drawable.ic_calendar);

// Set the bounds of the drawable (adjust the width and height as needed)
        icon.setBounds(0, 0, icon.getIntrinsicWidth() / 2, icon.getIntrinsicHeight() / 2);

// Set the compound drawables for the EditText
        editTextDate.setCompoundDrawablesRelative(null, null, icon, null);

        return view;
    }
}
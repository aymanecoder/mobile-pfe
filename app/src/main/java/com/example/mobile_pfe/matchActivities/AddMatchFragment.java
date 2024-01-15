package com.example.mobile_pfe.matchActivities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TimePicker;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.mobile_pfe.R;
import com.example.mobile_pfe.sevices.MatchRequest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddMatchFragment extends Fragment implements MapDialogFragment.OnLocationSelectedListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private EditText editTextDate;
    private EditText editTextMatchLocation;
    private EditText editTextStartTime; // Added EditText for start time

    private Calendar calendar;

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
        editTextStartTime = view.findViewById(R.id.editTextStartTime); // Initialize the EditText for start time

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

        // Set click listener for the EditText to show the date picker
        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        // Set click listener for the EditText to show the time picker
        editTextStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });

        // Button click listener to navigate to the next fragment
        Button addMore = view.findViewById(R.id.saveButton);
        addMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set an initial date on the editText
                updateDateOnEditText();

                MatchRepository matchRepository = MatchRepository.getInstance();

                // Get the MatchRequest instance from the repository
                MatchRequest match = matchRepository.getMatchRequest();

                // Match Title
                EditText editTextMatchTitle = view.findViewById(R.id.editTextMatchTitle);
                match.setTitle(editTextMatchTitle.getText().toString());

                // Match Description
                EditText editTextMatchDescription = view.findViewById(R.id.editTextMatchDescription);
                match.setDescription(editTextMatchDescription.getText().toString());

                // Date
                EditText editTextDate = view.findViewById(R.id.editTextDate);
                match.setDate(editTextDate.getText().toString());

                //private
                RadioGroup radioGroupPrivacy = view.findViewById(R.id.radioGroupPrivacy);

                radioGroupPrivacy.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        boolean isPrivate = checkedId == R.id.radioButtonPrivate;
                        match.setPrivateMatch(isPrivate);
                    }
                });

                // Validate fields
                if (validateFields(view)) {
                    // Update the MatchRequest instance in the repository
                    matchRepository.saveMatchRequest(match);
                    System.out.println("Mach Details :" + match.toString());

                    // Create a new instance of ItemFragment and set arguments
                    ChooseTeamFragment addMatchFragment = new ChooseTeamFragment();

                    // Replace the current fragment with ItemFragment
                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, addMatchFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                } else {
                    // Handle the case where validation fails (show a message, etc.)
                }
            }
        });


        calendar = Calendar.getInstance();

        // Set an initial date on the editText
        updateDateOnEditText();

        return view;
    }

    public void showDatePicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                requireContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        updateDateOnEditText();
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );

        datePickerDialog.show();
    }

    public void showTimePicker() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                requireContext(),
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // Update the calendar with the selected time
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);

                        // Update the editTextStartTime
                        updateDateOnEditText();
                    }
                },
                calendar.get(Calendar.HOUR_OF_DAY), // Initial hour
                calendar.get(Calendar.MINUTE),      // Initial minute
                false                               // 24-hour format
        );

        timePickerDialog.show();
    }

    private void updateDateOnEditText() {
        String dateFormat = "yyyy-MM-dd'T'HH:mm:ss.S";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.US);

        editTextDate.setText(sdf.format(calendar.getTime()));
        String dateFormat2 = "HH:mm";
        SimpleDateFormat sdf2 = new SimpleDateFormat(dateFormat2, Locale.US);

        editTextStartTime.setText(sdf2.format(calendar.getTime()));
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

    private boolean validateFields(View view) {
        EditText editTextMatchTitle = view.findViewById(R.id.editTextMatchTitle);
        EditText editTextMatchDescription = view.findViewById(R.id.editTextMatchDescription);
        EditText editTextDate = view.findViewById(R.id.editTextDate);

        // Add more fields as needed

        return !TextUtils.isEmpty(editTextMatchTitle.getText())
                && !TextUtils.isEmpty(editTextMatchDescription.getText())
                && !TextUtils.isEmpty(editTextDate.getText());

        // Add more conditions for additional fields
    }

}
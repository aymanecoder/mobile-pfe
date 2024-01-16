package com.example.mobile_pfe.programActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mobile_pfe.Adapter.ProgramAdapter;
import com.example.mobile_pfe.R;

import com.example.mobile_pfe.UI.CoachContent;

import com.example.mobile_pfe.UI.MainActivity;
import com.example.mobile_pfe.sevices.ProgramService;
import com.example.mobile_pfe.model.Globals.AppGlobals;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListProgramActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {
    private ProgramAdapter adapter;
    private RecyclerView recyclerView;
    private Button entrainButton, nutritionButton;
    private float x1,x2;
    private static int MIN_DISTANCE =150;
    private GestureDetector gestureDetector;

    private ColorStateList originalTextColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_program);

        this.gestureDetector = new GestureDetector(ListProgramActivity.this,this);


        entrainButton = findViewById(R.id.entrainement);
        nutritionButton = findViewById(R.id.nutrition);

        // Set up ViewPager with the adapter

        // Set the default fragment to UpcomingFragment
        replaceFragment(new EntrainFragment());



        entrainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new EntrainFragment());
                setButtonStyles(entrainButton, nutritionButton);
            }
        });

        nutritionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new NutritionFragment());
                setButtonStyles(nutritionButton, entrainButton);
            }
        });

        // Move the backButton code here
        TextView backButton = findViewById(R.id.Back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        originalTextColor = backButton.getTextColors();

        backButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Pressed state: Set text color to white
                        backButton.setTextColor(Color.WHITE);
                        break;

                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        // Released or canceled state: Restore the original text color
                        backButton.setTextColor(originalTextColor);

                        // Trigger onBackPressed() when the button is released
                        Intent intent = new Intent(ListProgramActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                }
                return false;
            }
        });

        Log.d("AccessToken", "Value: " + AppGlobals.getAccessToken());
// Now you can call extractUserRole
        List<String> userRoles = AppGlobals.extractUserRoles();

        Log.d("UserRole", "Value: " + userRoles);

        if (userRoles.contains("COACH")) {
            FloatingActionButton fabAdd = findViewById(R.id.fabAdd);
            fabAdd.setVisibility(View.VISIBLE);

            fabAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Navigate to UploadActivity when fabAdd is clicked
                    Intent intent = new Intent(ListProgramActivity.this, UploadProgramActivity.class);
                    startActivity(intent);
                }
            });
        } else {
            FloatingActionButton fabAdd = findViewById(R.id.fabAdd);
            fabAdd.setVisibility(View.GONE);
        }

    }




    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void setButtonStyles(Button selectedButton, Button unselectedButton) {
        // Set the background drawables
        selectedButton.setBackgroundResource(R.drawable.button_selector);
        unselectedButton.setBackgroundResource(R.drawable.butto_not_selector);

        int textColorSelector = ContextCompat.getColor(this, R.color.colorTextPrimary);
        int textColor = ContextCompat.getColor(this, R.color.gris);

        selectedButton.setTextColor(textColorSelector);
        unselectedButton.setTextColor(textColor);
    }


    @Override
    public boolean onDown(@NonNull MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(@NonNull MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(@NonNull MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(@Nullable MotionEvent motionEvent, @NonNull MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(@NonNull MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(@Nullable MotionEvent motionEvent, @NonNull MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        // Get the current fragment
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        // Check the current fragment and set button styles accordingly
        if (currentFragment instanceof EntrainFragment) {
            setButtonStyles(entrainButton, nutritionButton);
        } else if (currentFragment instanceof NutritionFragment) {
            setButtonStyles(nutritionButton, entrainButton);
        }
    }
}
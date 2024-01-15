package com.example.mobile_pfe.matchActivities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mobile_pfe.R;
import com.google.android.material.tabs.TabLayout;

public class ShowMatches extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private Button upcomingButton, completedButton;
    private float x1,x2;
    private static int MIN_DISTANCE =150;
    private GestureDetector gestureDetector;

    private ColorStateList originalTextColor;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_matches);

        //init gesturedetector
        this.gestureDetector = new GestureDetector(ShowMatches.this,this);


        upcomingButton = findViewById(R.id.button1);
        completedButton = findViewById(R.id.completed);


        // Set up ViewPager with the adapter


        // Set the default fragment to UpcomingFragment
        replaceFragment(new UpcomingFragment());



        upcomingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new UpcomingFragment());
                setButtonStyles(upcomingButton, completedButton);
            }
        });

        completedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new CompletedFragment());
                setButtonStyles(completedButton, upcomingButton);
            }
        });

        // Move the backButton code here
        TextView backButton = findViewById(R.id.backButton);
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
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 =event.getX();
                break;

            case MotionEvent.ACTION_UP:
                x2 =event.getX();
                float X = x2 - x1;

                if(Math.abs(X)> MIN_DISTANCE ){

                    if(x2>x1){
                        replaceFragment(new UpcomingFragment());
                        setButtonStyles(upcomingButton, completedButton);
                    }
                    else{
                        replaceFragment(new CompletedFragment());
                        setButtonStyles(completedButton, upcomingButton);
                    }
                }

        }

        return super.onTouchEvent(event);
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

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
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
        if (currentFragment instanceof UpcomingFragment) {
            setButtonStyles(upcomingButton, completedButton);
        } else if (currentFragment instanceof CompletedFragment) {
            setButtonStyles(completedButton, upcomingButton);
        }
    }



}
package com.example.mobile_pfe.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;

import com.example.mobile_pfe.Adapter.UserAdapterTwo;
import com.example.mobile_pfe.Adapter.WorkoutAdapter;
import com.example.mobile_pfe.Model.User;
import com.example.mobile_pfe.Model.Workout;
import com.example.mobile_pfe.R;

import java.util.ArrayList;

public class CoachWorkout extends AppCompatActivity {

    private ArrayList<Workout> workoutsList = new ArrayList<>();

    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coach_workout);

        fillCoachesList();

        recyclerView = findViewById(R.id.CoachesList_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        WorkoutAdapter adapter = new WorkoutAdapter(workoutsList);
        recyclerView.setAdapter(adapter);
    }

    private void fillCoachesList(){
        workoutsList.add(new Workout("Workout 1", "You'll find here the decription of the workout.You'll find here the description of the workout.", "8m ago"));
        workoutsList.add(new Workout("Workout 2", "You'll find here the decription of the workout.You'll find here the description of the workout.", "7m ago"));
        workoutsList.add(new Workout("Workout 3", "You'll find here the decription of the workout.You'll find here the description of the workout.", "5m ago"));
        workoutsList.add(new Workout("Workout 4", "You'll find here the decription of the workout.You'll find here the description of the workout.", "m ago"));
        workoutsList.add(new Workout("Workout 5", "You'll find here the decription of the workout.You'll find here the description of the workout.", "8m ago"));
        workoutsList.add(new Workout("Workout 6", "You'll find here the decription of the workout.You'll find here the description of the workout.", "8m ago"));
        workoutsList.add(new Workout("Workout 7", "You'll find here the decription of the workout.You'll find here the description of the workout.", "8m ago"));
        workoutsList.add(new Workout("Workout 8", "You'll find here the decription of the workout.You'll find here the description of the workout.", "8m ago"));
        workoutsList.add(new Workout("Workout 9", "You'll find here the decription of the workout.You'll find here the description of the workout.", "8m ago"));
        workoutsList.add(new Workout("Workout 10", "You'll find here the decription of the workout.You'll find here the description of the workout.", "8m ago"));

    }
}
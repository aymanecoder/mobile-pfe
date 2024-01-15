package com.example.mobile_pfe.GroupActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile_pfe.R;
import com.example.mobile_pfe.GroupActivity.ListAdapter2;
import com.example.mobile_pfe.GroupActivity.User;
import com.example.mobile_pfe.databinding.ActivityListviewgroupBinding;

import java.util.ArrayList;
import com.example.mobile_pfe.Network.RetrofitInstance;
import com.example.mobile_pfe.model.Group;
import com.example.mobile_pfe.model.Sportif;
import com.example.mobile_pfe.sevices.GroupService;
import com.example.mobile_pfe.sevices.SportifService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class listgroupactivity extends AppCompatActivity{
    ActivityListviewgroupBinding Binding;
    private ArrayList<Sportif> ChosenGroupMembers = new ArrayList<>(); // Field to store selected sportifs
    private List<Sportif> sportifs; // Add this field to store sportifs

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Binding = ActivityListviewgroupBinding.inflate(getLayoutInflater());
        setContentView(Binding.getRoot());

        fetchSportifNames();
    }

    private void fetchSportifNames() {
        SportifService service = RetrofitInstance.getRetrofitInstance().create(SportifService.class);
        Call<List<Sportif>> call = service.getSportifs();

        call.enqueue(new Callback<List<Sportif>>() {
            @Override
            public void onResponse(Call<List<Sportif>> call, Response<List<Sportif>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    populateSportifNames(response.body());
                } else {
                    Toast.makeText(listgroupactivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Sportif>> call, Throwable t) {
                Toast.makeText(listgroupactivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void populateSportifNames(List<Sportif> sportifs) {
        this.sportifs = sportifs; // Store the sportifs for later use

        ArrayList<User> userArrayList = new ArrayList<>();
        int defaultImageId = R.drawable.d; // Replace with your default image ID

        for (Sportif sportif : sportifs) {
            userArrayList.add(new User(sportif.getFirstName(), defaultImageId, sportif.getId()));
        }

        ListAdapter2 listAdapter = new ListAdapter2(listgroupactivity.this, userArrayList);
        Binding.lisvieww.setAdapter(listAdapter);
    }
    public void onConfirmSelectionClicked(View view) {
        ArrayList<User> selectedUsers = ((ListAdapter2) Binding.lisvieww.getAdapter()).getSelectedUsers();

        ChosenGroupMembers.clear();
        for (User selectedUser : selectedUsers) {
            for (Sportif sportif : sportifs) {
                if (sportif.getId() == selectedUser.getId()) {
                    ChosenGroupMembers.add(sportif);
                    Log.d("SelectedSportif", "ID: " + sportif.getId() + ", Name: " + sportif.getFirstName());

                }
            }
        }
        // Log the entire ChosenGroupMembers list
        Log.d("ChosenGroupMembers", "Total Selected: " + ChosenGroupMembers.size());
        for (Sportif sportif : ChosenGroupMembers) {
            Log.d("ChosenGroupMembers", "ID: " + sportif.getId() + ", Name: " + sportif.getFirstName());
        }
        createDefaultGroup();

    }
    private void createDefaultGroup() {
        GroupService groupService = RetrofitInstance.getRetrofitInstance().create(GroupService.class);

        // Prepare list of member IDs as a list of maps
        List<Map<String, Integer>> memberMaps = new ArrayList<>();
        for (Sportif sportif : ChosenGroupMembers) {
            if (sportif.getId() != -1 ) {
                memberMaps.add(Collections.singletonMap("id", sportif.getId()));
            } else {
                Log.e("GroupCreation", "Invalid ID for sportif: " + sportif.getFirstName());
            }
        }
        String groupName = RandomGroupNameGenerator.generateGroupName();


        // Create a Group object with the name "DefaultGroup" and the list of member maps
        Group group = new Group(groupName, memberMaps);

        // Send the POST request to create the group
        Call<Group> call = groupService.createGroup(group);
        call.enqueue(new Callback<Group>() {
            @Override
            public void onResponse(Call<Group> call, Response<Group> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Group creation successful
                    Log.d("GroupCreation", "Group created successfully: " + response.body());
                    Toast.makeText(listgroupactivity.this, "Group created successfully", Toast.LENGTH_SHORT).show();
                } else {
                    // Group creation failed
                    Log.e("GroupCreation", "Failed to create group: " + response.errorBody());
                    Toast.makeText(listgroupactivity.this, "Failed to create group", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Group> call, Throwable t) {
                // Network error or exception during the call
                Log.e("GroupCreation", "Network error or exception: " + t.getMessage());
                //Toast.makeText(listgroupactivity.this, "Networksssssss error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        Intent intent = new Intent(listgroupactivity.this, GroupActivity.class);
        startActivity(intent);
    }


}

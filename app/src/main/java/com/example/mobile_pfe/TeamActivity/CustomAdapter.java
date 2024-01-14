package com.example.mobile_pfe.TeamActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mobile_pfe.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;



public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private Context context;
    private List<TeamDetails> teamDetailsList;
    private List<TeamDetails> selectedTeams = new ArrayList<>(); // List to store selected teams
    private OnItemClickListener listener;

    public CustomAdapter(Context context, List<TeamDetails> teamDetailsList, OnItemClickListener listener) {
        this.context = context;
        this.teamDetailsList = teamDetailsList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listviewteam_activi, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TeamDetails teamDetails = teamDetailsList.get(position);

        holder.teamNameTextView.setText(teamDetails.getTeamName());
        holder.membersTextView.setText(context.getString(R.string.members, teamDetails.getMembersCount()));

        Glide.with(context)
                .load(teamDetails.getLogoPath().replace("localhost", "192.168.0.102"))
                .apply(RequestOptions.circleCropTransform())
                .into(holder.profilePicImageView);

        // Set the checkbox state based on whether the team is selected or not
        holder.checkBox.setOnCheckedChangeListener(null); // Remove previous listener to avoid interference
        holder.checkBox.setChecked(selectedTeams.contains(teamDetails));

        // Set click listener for the item view
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    // Notify the listener with the clicked teamDetails
                    listener.onItemClick(teamDetails);
                }
            }
        });

        // Set a listener for checkbox changes
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // If checkbox is checked, add the team to the selected teams list
                    selectedTeams.add(teamDetails);
                    Toast.makeText(context, "Selected Teams: " + getSelectedTeamsNames(), Toast.LENGTH_SHORT).show();

                } else {
                    // If checkbox is unchecked, remove the team from the selected teams list
                    selectedTeams.remove(teamDetails);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return teamDetailsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView profilePicImageView;
        TextView teamNameTextView;
        TextView membersTextView;
        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            profilePicImageView = itemView.findViewById(R.id.profilepic1);
            teamNameTextView = itemView.findViewById(R.id.teama);
            membersTextView = itemView.findViewById(R.id.members);
            checkBox = itemView.findViewById(R.id.checkBox);
        }
    }

    // Method for filtering the list
    public void filterList(List<TeamDetails> filteredList) {
        teamDetailsList = filteredList;
        notifyDataSetChanged();
    }

    // Interface for item click
    public interface OnItemClickListener {
        void onItemClick(TeamDetails teamDetails);
    }

    // Method to get the selected teams
    public List<TeamDetails> getSelectedTeams() {
        return selectedTeams;
    }
    private String getSelectedTeamsNames() {
        StringBuilder names = new StringBuilder();
        for (TeamDetails team : selectedTeams) {
            names.append(team.getTeamName()).append(", ");
        }
        // Remove the trailing comma and space
        if (names.length() > 2) {
            names.setLength(names.length() - 2);
        }
        return names.toString();
    }
}

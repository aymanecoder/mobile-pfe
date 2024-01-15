package com.example.mobile_pfe.sevices;

import com.example.mobile_pfe.TeamActivity.TeamDetails;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MatchRequest {

    private String title;
    private String description;
    private int scoreTeamA;
    private int scoreTeamB;
    @SerializedName("teams")
    private List<TeamId> teams;
    @SerializedName("participants")
    private List<ParticipantId> participants;
    @SerializedName("sport")
    private Sport sport;
    private String typeMatch;
    private String date;
    private int counter;
    private boolean privateMatch;

    public MatchRequest() {
        this.teams=new ArrayList<>();
        this.participants=new ArrayList<>();
        this.sport= new Sport(1);
    }

    public MatchRequest(String title, String description, int scoreTeamA, int scoreTeamB, List<TeamId> teams,
                        List<ParticipantId> participants, Sport sport, String typeMatch, String date,
                        int counter, boolean privateMatch) {
        this.title = title;
        this.description = description;
        this.scoreTeamA = scoreTeamA;
        this.scoreTeamB = scoreTeamB;
        this.teams = teams != null ? teams : new ArrayList<>();        this.participants = participants;
        this.sport = sport;
        this.typeMatch = typeMatch;
        this.date = date;
        this.counter = counter;
        this.privateMatch = privateMatch;
    }

    // Add getters and setters as needed


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getScoreTeamA() {
        return scoreTeamA;
    }

    public void setScoreTeamA(int scoreTeamA) {
        this.scoreTeamA = scoreTeamA;
    }

    public int getScoreTeamB() {
        return scoreTeamB;
    }

    public void setScoreTeamB(int scoreTeamB) {
        this.scoreTeamB = scoreTeamB;
    }

    public List<TeamId> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamId> teams) {
        this.teams = teams;
    }

    public List<ParticipantId> getParticipants() {
        return participants;
    }

    public void setParticipants(List<ParticipantId> participants) {
        this.participants = participants;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(int sport) {
        this.sport = new Sport(sport);
    }

    public String getTypeMatch() {
        return typeMatch;
    }

    public void setTypeMatch(String typeMatch) {
        this.typeMatch = typeMatch;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public boolean isPrivateMatch() {
        return privateMatch;
    }

    public void setPrivateMatch(boolean privateMatch) {
        this.privateMatch = privateMatch;
    }

    public static class TeamId {
        private int id;

        public TeamId(int id) {
            this.id = id;
        }
    }

    public static class ParticipantId {
        private int id;

        public ParticipantId(int id) {
            this.id = id;
        }
    }

    public static class Sport {
        private int id;

        public Sport(int id) {
            this.id = id;
        }
    }

    @Override
    public String toString() {
        return "MatchRequest{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", scoreTeamA=" + scoreTeamA +
                ", scoreTeamB=" + scoreTeamB +
                ", teams=" + teams +
                ", participants=" + participants +
                ", sport=" + sport +
                ", typeMatch='" + typeMatch + '\'' +
                ", date='" + date + '\'' +
                ", counter=" + counter +
                ", privateMatch=" + privateMatch +
                '}';
    }

    public void setTeamIdsFromSelectedTeams(List<TeamDetails> selectedTeams) {
        // Clear existing teams
        teams.clear();

        // Iterate over selectedTeams and add TeamId objects to the teams list
        for (TeamDetails team : selectedTeams) {
            teams.add(new TeamId(team.getId()));
        }
    }

}

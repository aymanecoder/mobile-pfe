package com.example.mobile_pfe.model;

import com.example.mobile_pfe.TeamActivity.Sport;
import com.example.mobile_pfe.TeamActivity.TeamDetails;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MatchResponse implements Serializable {

    private int id;
    private String title;
    private String description;
    @SerializedName("scoreTeamA")
    private int scoreTeamA;
    @SerializedName("scoreTeamB")
    private int scoreTeamB;
    private List<TeamDetails> teams;
    private Sport sport;
    @SerializedName("typeMatch")
    private String typeMatch;
    private String date;
    private int counter;
    @SerializedName("private")
    private boolean isPrivate;

    // Add getters and setters as needed

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public List<TeamDetails> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamDetails> teams) {
        this.teams = teams;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
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

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    @Override
    public String toString() {
        return "MatchResponse{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", scoreTeamA=" + scoreTeamA +
                ", scoreTeamB=" + scoreTeamB +
                ", teams=" + teams +
                ", sport=" + sport +
                ", typeMatch='" + typeMatch + '\'' +
                ", date='" + date + '\'' +
                ", counter=" + counter +
                ", isPrivate=" + isPrivate +
                '}';
    }
}
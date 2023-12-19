package com.example.mobile_pfe.TeamActivity;

public class Team {
    private String teamName;
    private int teamImageResource;

    public Team(String teamName, int teamImageResource) {
        this.teamName = teamName;
        this.teamImageResource = teamImageResource;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getTeamImageResource() {
        return teamImageResource;
    }
}


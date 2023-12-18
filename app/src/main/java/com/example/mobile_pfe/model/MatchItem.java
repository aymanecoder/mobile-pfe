package com.example.mobile_pfe.model;

public class MatchItem {

    private int team1Logo;
    private String team1Name;
    private int team2Logo;
    private String team2Name;

    public MatchItem(int team1Logo, String team1Name, int team2Logo, String team2Name) {
        this.team1Logo = team1Logo;
        this.team1Name = team1Name;
        this.team2Logo = team2Logo;
        this.team2Name = team2Name;
    }

    public int getTeam1Logo() {
        return team1Logo;
    }

    public String getTeam1Name() {
        return team1Name;
    }

    public int getTeam2Logo() {
        return team2Logo;
    }

    public String getTeam2Name() {
        return team2Name;
    }
}

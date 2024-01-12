package com.example.mobile_pfe.model.Competition;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CompetitionList {
    @SerializedName("competitionList")
    private ArrayList<Competition> competitionList;

    public ArrayList<Competition> getCompetitionArrayList() {
        return competitionList;
    }

    public void setCompetitionArrayList(ArrayList<Competition> CompetitionArrayList) {
        this.competitionList = CompetitionArrayList;
    }
}


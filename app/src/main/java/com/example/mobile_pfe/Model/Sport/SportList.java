package com.example.mobile_pfe.model.Sport;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SportList {

    @SerializedName("sportList")
    private ArrayList<Sport> sportList;

    public ArrayList<Sport> getSportArrayList() {
        return sportList;
    }

    public void setSportArrayList(ArrayList<Sport> sportList) {
        this.sportList = sportList;
    }
}

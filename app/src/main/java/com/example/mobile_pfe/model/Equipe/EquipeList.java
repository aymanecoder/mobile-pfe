package com.example.mobile_pfe.model.Equipe;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class EquipeList {
    @SerializedName("equipeList")
    private ArrayList<Equipe> equipeList;

    public ArrayList<Equipe> getEquipeArrayList() {
        return equipeList;
    }

    public void setEquipeArrayList(ArrayList<Equipe> EquipeArrayList) {
        this.equipeList = EquipeArrayList;
    }
}


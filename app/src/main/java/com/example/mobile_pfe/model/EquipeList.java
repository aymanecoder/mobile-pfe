package com.example.mobile_pfe.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class EquipeList {
    @SerializedName("Equipe")
    private ArrayList<Equipe> Equipe;

    public ArrayList<Equipe> getEquipeArrayList() {
        return Equipe;
    }

    public void setEmployeeArrayList(ArrayList<Equipe> employeeArrayList) {
        this.Equipe = employeeArrayList;
    }
}
package com.example.mobile_pfe.model.Program;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProgramList {
    @SerializedName("programList")
    private ArrayList<Program> programList;

    public ArrayList<Program> getProgramArrayList() {
        return programList;
    }

    public void setProgramArrayList(ArrayList<Program> ProgramArrayList) {
        this.programList = ProgramArrayList;
    }
}



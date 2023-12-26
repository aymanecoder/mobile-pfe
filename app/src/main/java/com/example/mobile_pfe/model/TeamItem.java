package com.example.mobile_pfe.model;

public class TeamItem {
    private int SportifLogo;
    private String SportifName;

    public TeamItem(int sportifLogo, String sportifName) {
        SportifLogo = sportifLogo;
        SportifName = sportifName;
    }

    public int getSportifLogo() {
        return SportifLogo;
    }

    public String getSportifName() {
        return SportifName;
    }
}

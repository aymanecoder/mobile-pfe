package com.example.mobile_pfe.model.Competition;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Competition {
    @SerializedName("title")
    private String title;
    @SerializedName("descreption")
    private String descreption;

    @SerializedName("nbrTeams")
    private int nbrTeams;

    @SerializedName("creationDate")
    private Date creationDate;

    @SerializedName("logoPath")
    private String logoPath;

    public Competition(String title, String descreption) {
        this.title = title;
        this.descreption = descreption;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescreption() {
        return descreption;
    }

    public void setDescreption(String descreption) {
        this.descreption = descreption;
    }
}


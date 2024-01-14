package com.example.mobile_pfe.model.Competition;

import com.example.mobile_pfe.Network.RetrofitInstance;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Competition {

    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;

    @SerializedName("nbrTeams")
    private int nbrTeams;

    @SerializedName("creationDate")
    private String creationDate;

    @SerializedName("logoPath")
    private String logoPath;

    public int getNbrTeams() {
        return nbrTeams;
    }

    public int getId() {
        return id;
    }

    public Competition(int id, String title, String descreption, int nbrTeams, String creationDate, String logoPath) {
        this.id = id;
        this.title = title;
        this.description = descreption;
        this.nbrTeams = nbrTeams;
        this.creationDate = creationDate;
        this.logoPath = logoPath;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNbrTeams(int nbrTeams) {
        this.nbrTeams = nbrTeams;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getLogoPath() {
        return logoPath.replace("http://localhost:8080/", RetrofitInstance.BASE_URL);
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescreption() {
        return description;
    }

    public void setDescreption(String descreption) {
        this.description = descreption;
    }
}


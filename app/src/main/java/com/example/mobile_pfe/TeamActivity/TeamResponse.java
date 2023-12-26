package com.example.mobile_pfe.TeamActivity;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class TeamResponse {
    private int id;

    @SerializedName("admin")
    private User admin;

    @SerializedName("members")
    private List<Member> membersList;

    private String description;

    @SerializedName("sport")
    private Sport sportDetails;

    @SerializedName("logoPath")
    private String logoPath;

    // Getters et Setters pour l'id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public User getAdmin() {
        return admin;
    }
    public void setAdmin(User admin) {
        this.admin = admin;
    }


    public List<Member> getMembersList() {
        return membersList;
    }

    public void setMembersList(List<Member> membersList) {
        this.membersList = membersList;
    }

    // Getters et Setters pour la description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Sport getSportDetails() {
        return sportDetails;
    }

    public void setSportDetails(Sport sportDetails) {
        this.sportDetails = sportDetails;
    }


    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }




}




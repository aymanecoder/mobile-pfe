package com.example.mobile_pfe.TeamActivity;

import java.util.List;

public class TeamDetails {
    private int id;
    private User admin;
    private List<Member> members;
    private String description;
    private Sport sport;
    private String logoPath;

    // Constructeur, getters, setters...

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    // Autres getters et setters pour les autres champs
}

package com.example.mobile_pfe.TeamActivity;

import com.example.mobile_pfe.model.Sportif;

import java.io.Serializable;
import java.util.List;

public class TeamDetails implements Serializable {
    private Integer id;
    private String name;
    private Sportif admin;
    private List<Sportif> members;
    private String description;
    private Sport sport;
    private String logoPath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTeamName() {
        return name;
    }

    public void setTeamName(String teamName) {
        this.name = teamName;
    }

    public Sportif getAdmin() {
        return admin;
    }

    public void setAdmin(Sportif admin) {
        this.admin = admin;
    }

    public List<Sportif> getMembers() {
        return members;
    }

    public void setMembers(List<Sportif> members) {
        this.members = members;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public int getMembersCount() {
        return members != null ? members.size() : 0;
    }
}

package com.example.mobile_pfe.model.Equipe;

import java.util.List;

public class TeamRequestBody {

    private String logo;
    private String name;
    private String description;
    private Integer adminId;
    private List<Integer> memberIds;
    private Integer sportId;

    public TeamRequestBody(String logo, String name, String description, Integer adminId, List<Integer> memberIds, Integer sportId) {
        this.logo = logo;
        this.name = name;
        this.description = description;
        this.adminId = adminId;
        this.memberIds = memberIds;
        this.sportId = sportId;
    }

    // Add getters and setters for the fields


    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public List<Integer> getMemberIds() {
        return memberIds;
    }

    public void setMemberIds(List<Integer> memberIds) {
        this.memberIds = memberIds;
    }

    public Integer getSportId() {
        return sportId;
    }

    public void setSportId(Integer sportId) {
        this.sportId = sportId;
    }
}

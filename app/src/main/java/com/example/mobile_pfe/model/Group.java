package com.example.mobile_pfe.model;

import java.util.List;
import java.util.Map;

public class Group {
    private String name;
    private List<Map<String, Integer>> members;

    // Constructor, getters, and setters
    public Group(String name, List<Map<String, Integer>> members) {
        this.name = name;
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Map<String, Integer>> getMembers() {
        return members;
    }

    public void setMembers(List<Map<String, Integer>> members) {
        this.members = members;
    }
}

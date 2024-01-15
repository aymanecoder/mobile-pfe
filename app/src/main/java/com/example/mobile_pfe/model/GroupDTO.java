package com.example.mobile_pfe.model;

import java.io.Serializable;
import java.util.List;

public class GroupDTO implements Serializable {
    private Long id;
    private String name;
    private List<Members> members;

    public GroupDTO() {

    }

    public Long getId() {
        return id;
    }

    public GroupDTO(Long id, String name, List<Members> members) {
        this.id = id;
        this.name = name;
        this.members = members;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Members> getMembers() {
        return members;
    }

    public void setMembers(List<Members> members) {
        this.members = members;
    }


}

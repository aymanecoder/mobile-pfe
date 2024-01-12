package com.example.mobile_pfe.model.Sport;

import com.example.mobile_pfe.Network.RetrofitInstance;
import com.google.gson.annotations.SerializedName;

public class Sport {

    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("logoPath")
    private String  logoPath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getLogoPath() {
        return logoPath.replace("http://localhost:8080/", RetrofitInstance.BASE_URL);
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public Sport(Integer id, String name, String description, String logoPath) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.logoPath = logoPath;
    }
}

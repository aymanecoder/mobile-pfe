package com.example.mobile_pfe.model.Program;

import com.example.mobile_pfe.Network.RetrofitInstance;
import com.google.gson.annotations.SerializedName;

public class Program {

    @SerializedName("id")
    private Long id;
    @SerializedName("title")
    private String title;
    @SerializedName("descreption")
    private String descreption;

    @SerializedName("typeProgramme")
    private String typeProgramme;
    @SerializedName("picturePath")
    private String picturePath;


    public Program(Long id,String title, String descreption, String picturePath,String typeProgramme) {
        this.id=id;
        this.title = title;
        this.descreption = descreption;
        this.picturePath = picturePath;
        this.typeProgramme=typeProgramme;
    }



    public Program(String title, String descreption) {
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

    public String getPicturePath() {
        return picturePath.replace("http://localhost:8080/", RetrofitInstance.BASE_URL);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getTypeProgramme() {
        return typeProgramme;
    }

    public void setTypeProgramme(String typeProgramme) {
        this.typeProgramme = typeProgramme;
    }
}


package com.example.mobile_pfe.model.Program;

import com.example.mobile_pfe.Network.RetrofitInstance;
import com.example.mobile_pfe.model.Globals.AppGlobals;
import com.google.gson.annotations.SerializedName;

import java.io.File;

public class Program {
    @SerializedName("title")
    private String title;
    @SerializedName("descreption")
    private String descreption;

    @SerializedName("typeProgramme")
    private String typeProgramme;
    @SerializedName("picturePath")
    private String picturePath;


    public Program(String title, String descreption, String picturePath,String typeProgramme) {
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


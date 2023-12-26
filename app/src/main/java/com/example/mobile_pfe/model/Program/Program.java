package com.example.mobile_pfe.model.Program;

import com.google.gson.annotations.SerializedName;

import java.io.File;

public class Program {
    @SerializedName("title")
    private String title;
    @SerializedName("descreption")
    private String descreption;

    @SerializedName("PicturePath")
    private String PicturePath;


    public Program(String title, String descreption, String picturePath) {
        this.title = title;
        this.descreption = descreption;
        PicturePath = picturePath;
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
        return PicturePath;
    }

    public void setPicturePath(String picturePath) {
        PicturePath = picturePath;
    }
}


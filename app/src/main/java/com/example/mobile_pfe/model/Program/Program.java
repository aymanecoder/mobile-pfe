package com.example.mobile_pfe.model.Program;

import com.google.gson.annotations.SerializedName;

public class Program {
    @SerializedName("title")
    private String title;
    @SerializedName("descreption")
    private String descreption;

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
    
}


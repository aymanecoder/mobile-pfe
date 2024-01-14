package com.example.mobile_pfe.model;


public class ResultModel {

    // string Result_name for storing Result_name
    // and imgid for storing image id.
    private String Result_name;
    private int imgid;

    public ResultModel(String Result_name, int imgid) {
        this.Result_name = Result_name;
        this.imgid = imgid;
    }

    public String getResult_name() {
        return Result_name;
    }

    public void setResult_name(String Result_name) {
        this.Result_name = Result_name;
    }

    public int getImgid() {
        return imgid;
    }

    public void setImgid(int imgid) {
        this.imgid = imgid;
    }
}

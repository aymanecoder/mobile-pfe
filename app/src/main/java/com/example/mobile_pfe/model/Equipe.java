package com.example.mobile_pfe.model;

import com.google.gson.annotations.SerializedName;

public class Equipe {
    @SerializedName("id")
    private Integer id;
    @SerializedName("payload")
    private String payload;
    @SerializedName("body")
    private String body;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Equipe(Integer id, String payload, String body
    ) {
        this.id = id;
        this.payload = payload;
        this.body = payload;
    }


}

package com.example.mobile_pfe.model;

public class Equipe {

    private int id ;
    private String name;
    private String body;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Equipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}

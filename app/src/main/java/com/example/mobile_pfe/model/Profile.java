package com.example.mobile_pfe.model;

import com.example.mobile_pfe.Network.RetrofitInstance;

import java.io.Serializable;

public class Profile implements Serializable {
    private int id;
    private String   firstName;
    private String    lastName;
    private String     email;
    private String    address;
    private String  age;
    private String   taille;
    private String   poids;
            private String picturePath;

    public int getId() {
        return id;
    }

    public Profile(int id, String firstName, String lastName, String email, String address, String age, String taille, String poids, String picturePath) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.age = age;
        this.taille = taille;
        this.poids = poids;
        this.picturePath = picturePath;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public String getPoids() {
        return poids;
    }

    public void setPoids(String poids) {
        this.poids = poids;
    }

    public String getPicturePath() {
        if (picturePath != null) {
            return picturePath.replace("http://localhost:8080/", RetrofitInstance.BASE_URL);
        } else {
            return null;
        }
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }
}

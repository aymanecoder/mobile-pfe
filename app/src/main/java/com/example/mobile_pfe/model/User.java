package com.example.mobile_pfe.model;

public class User {
    String name;
    int image ;

    String email;

    String Password;

    public User(String name, int image, String email, String password) {
        this.name = name;
        this.image = image;
        this.email = email;
        Password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}

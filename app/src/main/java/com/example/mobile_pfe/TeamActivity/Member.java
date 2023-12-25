package com.example.mobile_pfe.TeamActivity;

public class Member {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private int age;
    private int taille;
    private int poids;
    private String picturePath;

    // Constructeur, getters, setters...

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
    // Autres getters et setters pour les champs restants
}


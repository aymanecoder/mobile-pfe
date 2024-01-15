package com.example.mobile_pfe.model.User1;


import com.example.mobile_pfe.Network.RetrofitInstance;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    private Integer id;
    private String firstName;
    private String lastName;

    private String email;
    private String password;
    private String address;
    private Integer age;
    private Integer taille;
    private Integer poids;

    private String picturePath;
    private Role role;

    public User(Integer id, String firstName, String lastName, String email, String password, String address, Integer age, Integer taille, Integer poids, String PicturePath, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.age = age;
        this.taille = taille;
        this.poids = poids;
        this.picturePath = PicturePath;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getTaille() {
        return taille;
    }

    public void setTaille(Integer taille) {
        this.taille = taille;
    }

    public Integer getPoids() {
        return poids;
    }

    public void setPoids(Integer poids) {
        this.poids = poids;
    }

    public String getPicturePath() {
        if (picturePath != null) {
            return picturePath.replace("http://localhost:9000/", RetrofitInstance.BASE_URL);
        } else {
            return null;
        }
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

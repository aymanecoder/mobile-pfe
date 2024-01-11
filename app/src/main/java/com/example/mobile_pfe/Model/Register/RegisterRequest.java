package com.example.mobile_pfe.Model.Register;

public class RegisterRequest {
    private String email;
    private String password;
    private String Role;
    private String firstName;
    private String lastName;

    public RegisterRequest(String email, String password, String role, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        Role = role;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}

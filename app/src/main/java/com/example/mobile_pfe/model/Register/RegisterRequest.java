package com.example.mobile_pfe.model.Register;

public class RegisterRequest {
    private String email;
    private String password;
    private Role role;
    private String firstName;
    private String lastName;

    public RegisterRequest(String email, String password, String role, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.role = Role.valueOf(role);
        this.firstName = firstName;
        this.lastName = lastName;
    }
}

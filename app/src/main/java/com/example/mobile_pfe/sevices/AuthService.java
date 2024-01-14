package com.example.mobile_pfe.sevices;

import com.example.mobile_pfe.model.Login.AuthResponse;
import com.example.mobile_pfe.model.Register.RegisterResponse;
import com.example.mobile_pfe.model.Login.LoginRequest;
import com.example.mobile_pfe.model.Register.RegisterRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {
    @POST("/api/v1/auth/login")
    Call<AuthResponse> login(@Body LoginRequest loginRequest);

    @POST("/api/v1/auth/register")
    Call<RegisterResponse> Register(@Body RegisterRequest registerRequest);

}

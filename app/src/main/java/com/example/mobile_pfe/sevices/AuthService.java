package com.example.mobile_pfe.sevices;

import com.example.mobile_pfe.model.Login.AuthResponse;
import com.example.mobile_pfe.model.Login.LoginRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {
    @POST("/api/v1/auth/login")
    Call<AuthResponse> login(@Body LoginRequest loginRequest);

}

package com.example.mobile_pfe.sevices;

import com.example.mobile_pfe.model.Login.AuthResponse;
import com.example.mobile_pfe.model.Login.LoginRequest;
import com.example.mobile_pfe.model.Program.ProgramList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ProgramService {
    @GET("/api/v1/programmes")
    Call<ProgramList> getAll();

}

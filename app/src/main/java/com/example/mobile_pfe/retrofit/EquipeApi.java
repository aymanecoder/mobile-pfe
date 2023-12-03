package com.example.mobile_pfe.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import com.example.mobile_pfe.model.Equipe;

import java.util.List;

public interface EquipeApi {

    @GET("/api/v1/equipes")
    Call<List<Equipe>> getAllTeams();


    @POST("/api/v1/save")
    Call<Equipe> save(@Body Equipe equipe);
}

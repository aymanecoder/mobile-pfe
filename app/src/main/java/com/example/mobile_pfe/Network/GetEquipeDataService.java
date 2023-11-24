package com.example.mobile_pfe.Network;

import com.example.mobile_pfe.model.EquipeList;

import retrofit2.Call;
import retrofit2.http.GET;


public interface GetEquipeDataService {
    @GET("/equipes")
    Call<EquipeList> getEmployeeData();

}

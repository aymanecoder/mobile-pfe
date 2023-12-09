package com.example.mobile_pfe.sevices;

import com.example.mobile_pfe.model.Equipe.EquipeList;
import com.example.mobile_pfe.model.Program.ProgramList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EquipeService {
    @GET("/api/v1/equipes")
    Call<EquipeList> getAll();

}

package com.example.mobile_pfe.sevices;

import com.example.mobile_pfe.model.Sport.Sport;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SportService {
    @GET("/api/v1/sports")
    Call<List<Sport>> getAll();

    @GET("/api/v1/sports/{id}")
    Call<Sport> getById(@Path("id") int id);

}

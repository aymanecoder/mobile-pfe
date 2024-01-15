package com.example.mobile_pfe.sevices;

import com.example.mobile_pfe.model.Sportif;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SportifService {
    @GET("api/v1/sportifs")
    Call<List<Sportif>> getSportifs();
}

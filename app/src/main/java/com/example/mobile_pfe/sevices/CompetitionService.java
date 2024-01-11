package com.example.mobile_pfe.sevices;

import com.example.mobile_pfe.Model.Competition.Competition;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CompetitionService {
    @GET("/challenges")
    Call<List<Competition>> getAll();

}

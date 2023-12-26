package com.example.mobile_pfe.sevices;

import com.example.mobile_pfe.model.Competition.CompetitionList;
import com.example.mobile_pfe.model.Program.ProgramList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CompetitionService {
    @GET("/challenges")
    Call<CompetitionList> getAll();

}

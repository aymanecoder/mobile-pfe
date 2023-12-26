package com.example.mobile_pfe.sevices;

import com.example.mobile_pfe.model.Competition.Competition;
import com.example.mobile_pfe.model.Competition.CompetitionList;
import com.example.mobile_pfe.model.Program.ProgramList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CompetitionService {
    @GET("/challenges")
    Call<List<Competition>> getAll();

}

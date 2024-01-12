package com.example.mobile_pfe.sevices;

import com.example.mobile_pfe.model.Equipe.TeamRequestBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TeamService {

    @POST("api/v1/teams")
    Call<Void> createTeam(@Body TeamRequestBody requestBody);

    // Add other necessary API endpoints here
}

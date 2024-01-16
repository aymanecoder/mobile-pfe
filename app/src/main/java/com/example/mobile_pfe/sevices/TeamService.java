package com.example.mobile_pfe.sevices;

import com.example.mobile_pfe.TeamActivity.TeamDetails;
import com.example.mobile_pfe.model.Equipe.TeamRequestBody;
import com.example.mobile_pfe.model.Sportif;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

public interface TeamService {

    @POST("/api/v1/teams")
    @Multipart
    Call<ResponseBody> createTeam(
            @Part MultipartBody.Part logo,
            @Part("name") RequestBody teamName,
            @Part("description") RequestBody description,
            @Part("sport.id") RequestBody sportId,
            @PartMap Map<String, Integer> memberIds
    );

    @GET("/api/v1/teams")
    Call<List<TeamDetails>> getTeams();

    @POST("/api/vl/teams/{teamld}/join")
    Call<ResponseBody> joinTeam(@Path("teamId") int teamId);


    // Add other necessary API endpoints here
}

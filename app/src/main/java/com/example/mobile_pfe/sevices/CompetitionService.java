package com.example.mobile_pfe.sevices;

import com.example.mobile_pfe.model.Competition.Competition;
import com.example.mobile_pfe.model.Competition.CompetitionList;
import com.example.mobile_pfe.model.Program.ProgramList;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface CompetitionService {
    @GET("/api/v1/challenges")
    Call<List<Competition>> getAll();

    @GET("/api/v1/challenges/bySport/{sport}")
    Call<List<Competition>> getChallengesBySportAndTypeProgram(@Path("sport") int sport);

    @GET("/api/v1/challenges/{id}")
    Call<Competition> getById(@Path("id") int id);

    @GET("/api/v1/challenges/joinChallenge/{id}")
    Call<Map<String, String>> joinChallenge(@Path("id") int id);
    @POST("/api/v1/challenges")
    @Multipart
    Call<ResponseBody> createCompetition(
            @Part MultipartBody.Part file,
            @Part("title") RequestBody title,
            @Part("description") RequestBody description,
            @Part("nbrTeams") RequestBody nbrTeams,
            @Part("creationDate") RequestBody creationDate,
            @Part("sport.id") RequestBody sportId
    );
}

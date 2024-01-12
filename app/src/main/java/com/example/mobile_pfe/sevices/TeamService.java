package com.example.mobile_pfe.sevices;

import com.example.mobile_pfe.model.Equipe.TeamRequestBody;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface TeamService {

    @POST("/api/v1/teams")
    @Multipart
    Call<ResponseBody> createTeam(
            @Part MultipartBody.Part logo,
            @Part("title") RequestBody teamName,
            @Part("description") RequestBody description,
            @Part("sport.id") RequestBody sportId,
            @Part("admin.id") RequestBody adminId,
            @PartMap Map<String, Integer> memberIds
    );


    // Add other necessary API endpoints here
}

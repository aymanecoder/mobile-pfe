package com.example.mobile_pfe.sevices;

import com.example.mobile_pfe.model.Login.AuthResponse;
import com.example.mobile_pfe.model.Login.LoginRequest;
import com.example.mobile_pfe.model.Program.ProgramList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ProgramService {
    @GET("/programmes")
    Call<ProgramList> getAll();

    @POST("/programmes")
    @Multipart
    Call<ResponseBody> createProgramme(
            @Part MultipartBody.Part file,
            @Part("title") RequestBody title,
            @Part("description") RequestBody description
    );
}

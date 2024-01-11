package com.example.mobile_pfe.sevices;

import com.example.mobile_pfe.Model.Program.Program;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ProgramService {
    @GET("/api/v1/programmes")
    Call<List<Program>> getAll();

    @POST("/api/v1/programmes")
    @Multipart
    Call<ResponseBody> createProgramme(
            @Part MultipartBody.Part file,
            @Part("title") RequestBody title,
            @Part("descreption") RequestBody description,
            @Part("typeProgramme") RequestBody typeProgramme
    );
}

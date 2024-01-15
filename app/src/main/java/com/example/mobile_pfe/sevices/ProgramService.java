package com.example.mobile_pfe.sevices;

import com.example.mobile_pfe.model.Login.AuthResponse;
import com.example.mobile_pfe.model.Login.LoginRequest;
import com.example.mobile_pfe.model.Program.Program;
import com.example.mobile_pfe.model.Program.ProgramList;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ProgramService {
    @GET("/api/v1/programmes/byTypeProgram/{typeProgram}")
    Call<List<Program>> getProgramsByTypeProgram(@Path("typeProgram") String typeProgram);

//
//    @GET("/bySportandTypeProgram/{sport}/{typeProgram}")
//    Call<List<Program>> getProgramsBySportAndTypeProgram(@Path("sport") int sport, @Path("typeProgram") String typeProgram);
    @GET("/api/v1/programmes/{id}")
    Call<Program> getById(@Path("id") int id);

    @POST("/api/v1/programmes")
    @Multipart
    Call<ResponseBody> createProgramme(
            @Part MultipartBody.Part file,
            @Part("title") RequestBody title,
            @Part("descreption") RequestBody description,
            @Part("typeProgramme") RequestBody typeProgramme
    );
}

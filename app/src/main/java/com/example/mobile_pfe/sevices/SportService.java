package com.example.mobile_pfe.sevices;

import com.example.mobile_pfe.model.Sport.Sport;

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

public interface SportService {
    @GET("/api/v1/sports")
    Call<List<Sport>> getAll();

    @GET("/api/v1/sports/{id}")
    Call<Sport> getById(@Path("id") int id);

}

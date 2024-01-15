package com.example.mobile_pfe.sevices;

import com.example.mobile_pfe.model.User1.User;
import com.example.mobile_pfe.model.Video;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserService {

    @GET("api/v1/sportifs")
    Call<List<User>> getSportifs();

    @GET("api/v1/coachs")
    Call<List<User>> getCoachs();

    @GET("api/v1/videos")
    Call<List<Video>> getAllVideos();

    @GET("api/v1/videos")
    Call<Video> getVideoById(int id);

}

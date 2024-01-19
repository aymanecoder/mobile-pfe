package com.example.mobile_pfe.sevices;

import com.example.mobile_pfe.Model.User1.User;
import com.example.mobile_pfe.Model.Video;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserService {

    @GET("api/v1/sportifs")
    Call<List<User>> getSportifs();

    @GET("api/v1/coachs")
    Call<List<User>> getCoachs();

    @GET("api/v1/videos")
    Call<List<Video>> getAllVideos();

    @GET("api/v1/videos/{id}")
    Call<Video> getVideoById(@Path("id") int id);

    @GET("api/v1/coachs/{id}")
    Call<User> getCoachById(@Path("id") int id);

}

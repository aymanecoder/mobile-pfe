package com.example.mobile_pfe.sevices;

import android.view.PixelCopy;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MatchService {

    @POST("api/v1/matches")
    Call<ResponseBody> createMatch(@Body MatchRequest matchRequest);

}
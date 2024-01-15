package com.example.mobile_pfe.sevices;

import android.view.PixelCopy;

import com.example.mobile_pfe.model.MatchResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MatchService {

    @POST("api/v1/matches")
    Call<ResponseBody> createMatch(@Body MatchRequest matchRequest);

    @GET("api/v1/matches")
    Call<List<MatchResponse>> getMatches();

}
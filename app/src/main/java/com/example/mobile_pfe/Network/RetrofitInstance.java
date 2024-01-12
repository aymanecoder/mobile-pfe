package com.example.mobile_pfe.Network;

import com.example.mobile_pfe.model.Globals.AppGlobals;
import com.example.mobile_pfe.sevices.SportifService;
import com.example.mobile_pfe.sevices.TeamService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class RetrofitInstance {
    private static Retrofit retrofit;
    public static final String BASE_URL = "http://192.168.0.102:8080/";
    private static final String AUTH_TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2huQGV4YW1wbGUuY29tIiwiaWF0IjoxNzA1MDE5ODExLCJleHAiOjE3MDUwMjg0NTF9.n8Cbz0W0Y9bfJWGpeCrHsolEhhKM9TPxLoB_eJYg0ZatdBgMGqenoI0Mfs0PerJvVlPf1dFY6hpxg3CVRjlqaw";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(new TokenInterceptor(AUTH_TOKEN));

            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }
        return retrofit;
    }

    public static SportifService getSportifService() {
        return getRetrofitInstance().create(SportifService.class);
    }
    public static TeamService getTeamService() {
        return getRetrofitInstance().create(TeamService.class);
    }
}
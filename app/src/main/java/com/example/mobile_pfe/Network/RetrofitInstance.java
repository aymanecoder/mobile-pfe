package com.example.mobile_pfe.Network;

import com.example.mobile_pfe.model.Globals.AppGlobals;
import com.example.mobile_pfe.sevices.SportifService;
import com.example.mobile_pfe.sevices.TeamService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class RetrofitInstance {
    private static final String AUTH_TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2huQGV4YW1wbGUuY29tIiwiaWF0IjoxNzA1Mjc0ODIxLCJleHAiOjE3MDUyODM0NjF9.hIp4nmilvE0tcXfyWxRYNYPex2H5SoNUBVQvBjih6UZMlCZgEZ3YD4B0AtFBpwYP8XjoDLiWU3atXSK0zr_Hfg";

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
    private static Retrofit retrofit;
    public static final String BASE_URL = "http://192.168.0.102:8080/";

    public static SportifService getSportifService() {
        return getRetrofitInstance().create(SportifService.class);
    }
    public static TeamService getTeamService() {
        return getRetrofitInstance().create(TeamService.class);
    }
}
package com.example.mobile_pfe.Network;

import androidx.appcompat.app.AppCompatDelegate;



import com.example.mobile_pfe.model.Globals.AppGlobals;
import com.example.mobile_pfe.sevices.MatchService;
import com.example.mobile_pfe.sevices.SportifService;
import com.example.mobile_pfe.sevices.TeamService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static Retrofit retrofit;
    public static final String BASE_URL = "http://192.168.1.103:8080/";
    public static final String BASE_URL_IP = "192.168.1.177";

    private static final String AUTH_TOKEN = AppGlobals.getAccessToken();
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            System.out.println("AUTH_TOKEN"+RetrofitInstance.AUTH_TOKEN);
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

    // Create an instance of MatchService
    public static MatchService getMatchService() {
        return getRetrofitInstance().create(MatchService.class);
    }
    public AppCompatDelegate getRetrofit() {
        return null;
    }
}
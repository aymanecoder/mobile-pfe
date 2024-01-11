package com.example.mobile_pfe.Network;

import com.example.mobile_pfe.model.Globals.AppGlobals;
import com.example.mobile_pfe.sevices.SportifService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class RetrofitInstance {
    private static Retrofit retrofit;
    public static final String BASE_URL = "http://192.168.0.102:8080/";
    private static final String AUTH_TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2huQGV4YW1wbGUuY29tIiwiaWF0IjoxNzA0OTgyMzU4LCJleHAiOjE3MDQ5OTA5OTh9.eqeZH_7bYddFx8bNFohjbcJBd77EFzdch5k4FenGmo4Ib9EG0MLKHFIq6bTg6nb7uSL56EJlKkWUniZzL2r7Kg";

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
}
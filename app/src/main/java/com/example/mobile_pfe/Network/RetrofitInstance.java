package com.example.mobile_pfe.Network;

import com.example.mobile_pfe.model.Globals.AppGlobals;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static Retrofit retrofit;
    public static final String BASE_URL = "http://192.168.1.103:8080/";
    private static final String AUTH_TOKEN = AppGlobals.getAccessToken();

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            System.out.println("AUTH_TOKEN"+RetrofitInstance.AUTH_TOKEN);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(new TokenInterceptor(AUTH_TOKEN));

            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory((GsonConverterFactory.create(new GsonBuilder().setLenient().create())))
                    .client(httpClient.build())
                    .build();
        }
        return retrofit;
    }


}

package com.example.mobile_pfe.sevices;

import com.example.mobile_pfe.Network.RetrofitInstance;
import com.example.mobile_pfe.model.Globals.AppGlobals;
import com.example.mobile_pfe.model.Login.AuthResponse;
import com.example.mobile_pfe.model.Login.LoginRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginTask {

    public void login(String email, String password, final LoginCallback callback) {
        Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(RetrofitInstance.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AuthService authService = retrofit.create(AuthService.class);
        Call<AuthResponse> call = authService.login(new LoginRequest(email, password));

        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    AuthResponse authResponse = response.body();
                    String accessToken = authResponse.getAccessToken();
                    AppGlobals.setAccessToken(accessToken);
                    AppGlobals.setEmail(email);
                    callback.onSuccess();
                } else {
                    callback.onError("Invalid credentials. Please try again.");
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                System.out.println("Error Trace" + t.getMessage());
                t.printStackTrace();
                callback.onError("Connection error. Please try again.");
            }
        });
    }

    public interface LoginCallback {
        void onSuccess();
        void onError(String errorMessage);
    }
}
package com.example.mobile_pfe.sevices;

import com.example.mobile_pfe.model.Globals.AppGlobals;
import com.example.mobile_pfe.model.Register.RegisterResponse;
import com.example.mobile_pfe.model.Register.RegisterRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;




public class RegisterTask {

    public void Register(String email, String password, String Role, String firstName, String lastName, final RegisterCallback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.101:8080") // Adjust the base URL accordingly
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AuthService authService = retrofit.create(AuthService.class);
        Call<RegisterResponse> call = authService.Register(new RegisterRequest(email, password,Role,firstName,lastName));

        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    RegisterResponse RegisterResponse = response.body();
                    String accessToken = RegisterResponse.getAccessToken();
                    AppGlobals.setAccessToken(accessToken);
                    callback.onSuccess();
                } else {
                    callback.onError("Invalid credentials. Please try again.");
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                System.out.println("Error Trace");
                t.printStackTrace();
                callback.onError("Connection error. Please try again.");
            }
        });
    }

    public interface RegisterCallback {
        void onSuccess();
        void onError(String errorMessage);
    }
}
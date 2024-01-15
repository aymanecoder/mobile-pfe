package com.example.mobile_pfe.sevices;

import android.util.Log;

import com.example.mobile_pfe.Network.RetrofitInstance;
import com.example.mobile_pfe.model.Globals.AppGlobals;
import com.example.mobile_pfe.model.Register.RegisterResponse;
import com.example.mobile_pfe.model.Register.RegisterRequest;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;




public class



RegisterTask {

    public void Register(String email, String password, String Role, String firstName, String lastName, final RegisterCallback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitInstance.BASE_URL) // Adjust the base URL accordingly
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AuthService authService = retrofit.create(AuthService.class);
        Call<RegisterResponse> call = authService.Register(new RegisterRequest(email, password, Role, firstName, lastName));
        Log.d("RegisterRequest", "RegisterRequest: Role " +   Role);
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    RegisterResponse RegisterResponse = response.body();
                    String accessToken = RegisterResponse.getAccessToken();
                    AppGlobals.setAccessToken(accessToken);
                    Log.d("RegisterAPI", "Access Token: " + accessToken);

                    callback.onSuccess();
                } else {
                    // Error case
                    Log.e("RegisterAPI", "Response Code: " + response.code());
                    String errorMessage = "Unknown error occurred";
                    if (response.errorBody() != null) {
                        try {
                            errorMessage = response.errorBody().string();
                            if (errorMessage.isEmpty()) {
                                errorMessage = "Error body is empty";
                            }
                        } catch (IOException e) {
                            Log.e("RegisterAPI", "Error reading error body", e);
                            errorMessage = "Exception occurred: " + e.getMessage();
                        }
                    } else {
                        errorMessage = "Error body is null";
                    }
                    Log.e("RegisterAPI", "Response Error: " + errorMessage);
                    callback.onError("Please try again. Error: " + errorMessage);


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
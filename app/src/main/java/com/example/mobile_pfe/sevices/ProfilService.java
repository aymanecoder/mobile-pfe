package com.example.mobile_pfe.sevices;

import com.example.mobile_pfe.model.Profile;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.PUT;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;

public interface ProfilService {
    @GET("api/v1/sportifs/profile")
    Call<Profile> getUserProfile();

    @Multipart
    @PUT("api/v1/sportifs/profile")
    Call<Profile> updateUserProfile(
            @Part MultipartBody.Part file,
            @Part("firstName") RequestBody firstName,
            @Part("lastName") RequestBody lastName,
            @Part("email") RequestBody email,
            @Part("password") RequestBody password,
            @Part("address") RequestBody address,
            @Part("age") RequestBody age,
            @Part("taille") RequestBody taille,
            @Part("poids") RequestBody poids
    );
}

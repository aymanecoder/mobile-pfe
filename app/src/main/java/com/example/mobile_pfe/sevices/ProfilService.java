package com.example.mobile_pfe.sevices;

import com.example.mobile_pfe.model.Profil.profil;

import retrofit2.http.Body;
import retrofit2.http.PUT;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProfilService {
    @GET("api/v1/profile/profile")
    Call<profil> getUserProfile();

    @PUT("/api/v1/profile/profile")
    Call<profil> updateProfile(@Body profil updatedProfile);
}

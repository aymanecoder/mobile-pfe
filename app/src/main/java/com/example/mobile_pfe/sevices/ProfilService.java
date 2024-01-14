package com.example.mobile_pfe.sevices;

import com.example.mobile_pfe.Model.Profil.profil;

import retrofit2.http.Body;
import retrofit2.http.PUT;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ProfilService {
    @GET("api/v1/profile/profile")
    Call<profil> getUserProfile();

    @PUT("/api/v1/profile/profile")
    Call<profil> updateProfile(@Body profil updatedProfile);
}

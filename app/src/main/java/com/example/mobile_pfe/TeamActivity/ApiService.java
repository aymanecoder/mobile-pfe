package com.example.mobile_pfe.TeamActivity;



import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;



public interface ApiService {
    @GET("/api/v1/teams") // Remplacez "votre_endpoint" par votre chemin d'API approprié
    Call<List<TeamResponse>> getTeams(); // Modifier pour retourner une liste de TeamResponse
}





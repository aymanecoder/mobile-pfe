package com.example.mobile_pfe.sevices;


import com.example.mobile_pfe.model.Group;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface GroupService {
    @POST("/api/v1/groups")
    Call<Group> createGroup(@Body Group group);
}


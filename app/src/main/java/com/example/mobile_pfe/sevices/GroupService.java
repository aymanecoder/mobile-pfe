package com.example.mobile_pfe.sevices;


import com.example.mobile_pfe.GroupActivity.User;
import com.example.mobile_pfe.model.Group;
import com.example.mobile_pfe.model.GroupDTO;
import com.example.mobile_pfe.model.Sportif;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface GroupService {
    @POST("/api/v1/groups")
    Call<Group> createGroup(@Body Group group);
    @GET("/api/v1/groups")
    Call<List<GroupDTO>> getAllGroups();

    @PUT("/api/v1/groups/{id}")
    Call<GroupDTO> updateGroup(@Path("id") Long id, @Body GroupDTO updatedGroupDto);

    @POST("/api/v1/groups/{userId}/join/{groupId}")
    Call<Sportif> joinGroup(@Path("userId") int userId, @Path("groupId") Long groupId);
}


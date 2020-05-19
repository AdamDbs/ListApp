package com.example.listapp;

import com.example.listapp.model.RestRMResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RMApi {
    @GET("/api/character/")
    Call<RestRMResponse> getRMResponse();
}

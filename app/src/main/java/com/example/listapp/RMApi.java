package com.example.listapp;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RMApi {
    @GET("/api/character/")
    Call<RestRMResponse> getRMResponse();
}

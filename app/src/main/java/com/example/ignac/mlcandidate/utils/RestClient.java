package com.example.ignac.mlcandidate.utils;

import com.example.ignac.mlcandidate.model.Result;
import com.example.ignac.mlcandidate.model.Results;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestClient {

    @GET("search")
    Call<Results> getData(@Query("state") String stateId,
                          @Query("condition") String condition,
                          @Query("q") String product);
}

package com.example.ignac.mlcandidate.utils;

import com.example.ignac.mlcandidate.model.Description;
import com.example.ignac.mlcandidate.model.ItemDetails;
import com.example.ignac.mlcandidate.model.Picture;
import com.example.ignac.mlcandidate.model.Result;
import com.example.ignac.mlcandidate.model.Results;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestClient {

    @GET("search")
    Call<Results> getData(@Query("state") String stateId,
                          @Query("condition") String condition,
                          @Query("q") String product);

    @GET("/items/{item_id}/description")
    Call<Description> getDetails(@Path("item_id") String item_id);

    @GET("/items/{item_id}")
    Call<ItemDetails> getPictureDetails(@Path("item_id") String item_id);

}

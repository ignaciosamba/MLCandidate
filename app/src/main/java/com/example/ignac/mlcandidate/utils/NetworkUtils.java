package com.example.ignac.mlcandidate.utils;

import android.widget.ImageView;

import com.example.ignac.mlcandidate.ApplicationContext;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkUtils {

    private Retrofit mRetrofit = null;


    public RestClient getRestClient(String baseQuery) {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseQuery)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        RestClient restClient = retrofit.create(RestClient.class);

        return restClient;
    }

    static public void LoadImage(String pathToImage, ImageView imageView) {
        Picasso.with(ApplicationContext.getAppContext()).load(pathToImage).into(imageView);
    }

}

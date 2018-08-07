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

    /**
     *  Method to get the RestClient and perform a search.
     *
     * @param       baseQuery  String that will be use as a base url.
     * @return      RestClient
     */
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

    /**
     *  Method to retrieve a image and show it into a ImageView using Picasso.
     *
     * @param       pathToImage  String that will be use as a path to url that have the image.
     * @param       imageView  ImageView that will be use to show the image.
     * @return      RestClient
     */
    static public void LoadImage(String pathToImage, ImageView imageView) {
        if (pathToImage != null && !pathToImage.isEmpty()) {
            Picasso.with(ApplicationContext.getAppContext()).load(pathToImage).into(imageView);
        }
    }

}

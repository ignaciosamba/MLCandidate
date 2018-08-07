package com.example.ignac.mlcandidate.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Result {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("permalink")
    @Expose
    private String permalink;

    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;

    /**
     * No args constructor for use in serialization
     *
     */
    public Result() {
    }

    /**
     * Constructor of Result.
     *
     * @param id of the product.
     * @param title of the product.
     * @param thumbnail of the product.
     * @param price of product.
     */
    public Result(String id, String title, Double price,
                  String permalink, String thumbnail) {
        super();
        this.id = id;
        this.title = title;
        this.price = price;
        this.permalink = permalink;
        this.thumbnail = thumbnail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

}

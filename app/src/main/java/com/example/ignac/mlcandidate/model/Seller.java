package com.example.ignac.mlcandidate.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Seller {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("power_seller_status")
    @Expose
    private Object powerSellerStatus;
    @SerializedName("car_dealer")
    @Expose
    private Boolean carDealer;
    @SerializedName("real_estate_agency")
    @Expose
    private Boolean realEstateAgency;
    @SerializedName("tags")
    @Expose
    private List<Object> tags = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public Seller() {
    }

    /**
     *
     * @param tags
     * @param powerSellerStatus
     * @param id
     * @param realEstateAgency
     * @param carDealer
     */
    public Seller(Integer id, Object powerSellerStatus, Boolean carDealer, Boolean realEstateAgency,
                  List<Object> tags) {
        super();
        this.id = id;
        this.powerSellerStatus = powerSellerStatus;
        this.carDealer = carDealer;
        this.realEstateAgency = realEstateAgency;
        this.tags = tags;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getPowerSellerStatus() {
        return powerSellerStatus;
    }

    public void setPowerSellerStatus(Object powerSellerStatus) {
        this.powerSellerStatus = powerSellerStatus;
    }

    public Boolean getCarDealer() {
        return carDealer;
    }

    public void setCarDealer(Boolean carDealer) {
        this.carDealer = carDealer;
    }

    public Boolean getRealEstateAgency() {
        return realEstateAgency;
    }

    public void setRealEstateAgency(Boolean realEstateAgency) {
        this.realEstateAgency = realEstateAgency;
    }

    public List<Object> getTags() {
        return tags;
    }

    public void setTags(List<Object> tags) {
        this.tags = tags;
    }

}
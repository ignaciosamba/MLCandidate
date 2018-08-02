package com.example.ignac.mlcandidate.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Result {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("site_id")
    @Expose
    private String siteId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("seller")
    @Expose
    private Seller seller;

    @SerializedName("price")
    @Expose
    private Double price;

    @SerializedName("currency_id")
    @Expose
    private String currencyId;

    @SerializedName("available_quantity")
    @Expose
    private Integer availableQuantity;

    @SerializedName("sold_quantity")
    @Expose
    private Integer soldQuantity;

    @SerializedName("buying_mode")
    @Expose
    private String buyingMode;

    @SerializedName("listing_type_id")
    @Expose
    private String listingTypeId;

    @SerializedName("stop_time")
    @Expose
    private String stopTime;

    @SerializedName("condition")
    @Expose
    private String condition;

    @SerializedName("permalink")
    @Expose
    private String permalink;

    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;

    @SerializedName("accepts_mercadopago")
    @Expose
    private Boolean acceptsMercadopago;

    @SerializedName("installments")
    @Expose

    private Address address;

    @SerializedName("shipping")
    @Expose
    private Shipping shipping;

    @SerializedName("seller_address")
    @Expose
    private SellerAddress sellerAddress;

    @SerializedName("original_price")
    @Expose
    private Object originalPrice;

    /**
     * No args constructor for use in serialization
     *
     */
    public Result() {
    }

    /**
     *
     * @param condition
     * @param currencyId
     * @param seller
     * @param sellerAddress
     * @param availableQuantity
     * @param buyingMode
     * @param stopTime
     * @param id
     * @param shipping
     * @param title
     * @param thumbnail
     * @param price
     * @param soldQuantity
     * @param listingTypeId
     * @param originalPrice
     * @param permalink
     * @param siteId
     * @param address
     * @param acceptsMercadopago
     */
    public Result(String id, String siteId, String title, Seller seller, Double price,
                  String currencyId, Integer availableQuantity, Integer soldQuantity,
                  String buyingMode, String listingTypeId, String stopTime, String condition,
                  String permalink, String thumbnail, Boolean acceptsMercadopago,
                  Address address, Shipping shipping,
                  SellerAddress sellerAddress, Object originalPrice) {
        super();
        this.id = id;
        this.siteId = siteId;
        this.title = title;
        this.seller = seller;
        this.price = price;
        this.currencyId = currencyId;
        this.availableQuantity = availableQuantity;
        this.soldQuantity = soldQuantity;
        this.buyingMode = buyingMode;
        this.listingTypeId = listingTypeId;
        this.stopTime = stopTime;
        this.condition = condition;
        this.permalink = permalink;
        this.thumbnail = thumbnail;
        this.acceptsMercadopago = acceptsMercadopago;
        this.address = address;
        this.shipping = shipping;
        this.sellerAddress = sellerAddress;
        this.originalPrice = originalPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public Integer getSoldQuantity() {
        return soldQuantity;
    }

    public void setSoldQuantity(Integer soldQuantity) {
        this.soldQuantity = soldQuantity;
    }

    public String getBuyingMode() {
        return buyingMode;
    }

    public void setBuyingMode(String buyingMode) {
        this.buyingMode = buyingMode;
    }

    public String getListingTypeId() {
        return listingTypeId;
    }

    public void setListingTypeId(String listingTypeId) {
        this.listingTypeId = listingTypeId;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
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

    public Boolean getAcceptsMercadopago() {
        return acceptsMercadopago;
    }

    public void setAcceptsMercadopago(Boolean acceptsMercadopago) {
        this.acceptsMercadopago = acceptsMercadopago;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public SellerAddress getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(SellerAddress sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    public Object getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Object originalPrice) {
        this.originalPrice = originalPrice;
    }

}

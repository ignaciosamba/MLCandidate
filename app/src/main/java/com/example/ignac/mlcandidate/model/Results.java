package com.example.ignac.mlcandidate.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Results {

    public Results() {
        resultList = new ArrayList<>();
        filtersList = new ArrayList<>();
    }

    @SerializedName("results")
    @Expose
    private List<Result> resultList;

    @SerializedName("available_filters")
    @Expose
    private List<AvailableFilter> filtersList;

    public List<Result> getResultList() {
        return resultList;
    }

    public void setResultList(List<Result> resultList) {
        this.resultList = resultList;
    }

    public List<AvailableFilter> getFiltersList() {
        return filtersList;
    }

    public void setFiltersList(List<AvailableFilter> filtersList) {
        this.filtersList = filtersList;
    }
}

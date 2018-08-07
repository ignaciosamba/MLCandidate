package com.example.ignac.mlcandidate.utils;

import java.util.HashMap;

public class LocationFilterList {

    private static LocationFilterList mInstance;
    private HashMap<String, String> mLocations;

    private LocationFilterList() {
        mLocations = new HashMap<>();
    }

    public static LocationFilterList getInstance() {
        if(mInstance == null){
            mInstance = new LocationFilterList();
        }
        return mInstance;
    }

    public HashMap<String, String> getLocationsFilter() {
        return mLocations;
    }

    public void setLocationFilter(HashMap<String, String> locations) {
        this.mLocations = locations;
    }
}

package com.jay.country.model.network.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Cities {

    @SerializedName("geonames")
    @Expose
    private List<City> geonames = new ArrayList<>();

    public List<City> getGeonames() {
        return geonames;
    }
}

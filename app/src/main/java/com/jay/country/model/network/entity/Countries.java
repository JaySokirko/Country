package com.jay.country.model.network.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Countries {

    @SerializedName("China")
    @Expose
    private List<String> china = new ArrayList<>();

    @SerializedName("Japan")
    @Expose
    private List<String> japan = new ArrayList<>();

    @SerializedName("Thailand")
    @Expose
    private List<String> thailand = new ArrayList<>();

    @SerializedName("India")
    @Expose
    private List<String> india = new ArrayList<>();

    @SerializedName("Malaysia")
    @Expose
    private List<String> malaysia = new ArrayList<>();

    private static ArrayList<String> countries = new ArrayList<>();

    public static ArrayList<String> getCountries() {
        countries.add("China");
        countries.add("Japan");
        countries.add("Thailand");
        countries.add("India");
        countries.add("Malaysia");
        return countries;
    }

    public List<String> getChina() {
        return china;
    }

    public List<String> getJapan() {
        return japan;
    }

    public List<String> getThailand() {
        return thailand;
    }

    public List<String> getIndia() {
        return india;
    }

    public List<String> getMalaysia() {
        return malaysia;
    }
}

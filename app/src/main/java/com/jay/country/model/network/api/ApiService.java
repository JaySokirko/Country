package com.jay.country.model.network.api;

import com.jay.country.model.network.entity.Countries;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("David-Haim/CountriesToCitiesJSON/master/countriesToCities.json")
    Call<Countries> getCountries();
}

package com.jay.country.model.network.api;

import com.jay.country.model.network.entity.Cities;
import com.jay.country.model.network.entity.Countries;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiService {

    @GET("David-Haim/CountriesToCitiesJSON/master/countriesToCities.json")
    Call<Countries> getCountries();

    @GET
    Call<Cities> getCities(@Url String city);
}

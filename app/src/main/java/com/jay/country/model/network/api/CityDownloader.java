package com.jay.country.model.network.api;

import android.util.Log;

import com.jay.country.contract.CityDetailedContract;
import com.jay.country.model.network.entity.Cities;
import com.jay.country.model.network.entity.City;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CityDownloader implements CityDetailedContract.Model {

    private final String baseUrl = "http://api.geonames.org/";

    private final String format = "wikipediaSearchJSON?q=";

    private final String userName = "&maxRows=10&username=jaysokirko";

    private Retrofit retrofit;
    private ApiService apiService;

    @Override
    public void loadArticle(LoadFeedback feedback, String city) {

        String url = format + city + userName;

        retrofit = ApiClient.getRetrofitClient(baseUrl);
        apiService = retrofit.create(ApiService.class);

        Call<Cities> call = apiService.getCities(url);
        call.enqueue(new Callback<Cities>() {
            @Override
            public void onResponse(Call<Cities> call, Response<Cities> response) {

                for (City city : response.body().getGeonames()) {

                    Log.d("TAG", "onResponse: " + city.getSummary() + "\n");
                }
            }

            @Override
            public void onFailure(Call<Cities> call, Throwable t) {

            }
        });
    }
}

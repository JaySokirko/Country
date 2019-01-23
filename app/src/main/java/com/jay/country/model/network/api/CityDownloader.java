package com.jay.country.model.network.api;

import android.util.Log;

import com.jay.country.contract.CityDetailedContract;
import com.jay.country.model.network.entity.Cities;
import com.jay.country.model.network.entity.City;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CityDownloader implements CityDetailedContract.Model {

    @Override
    public void loadArticle(LoadFeedback feedback, String city) {

        String format = "wikipediaSearchJSON?q=";
        String userName = "&maxRows=10&username=jaysokirko";
        String url = format + city + userName;

        String baseUrl = "http://api.geonames.org/";
        Retrofit retrofit = ApiClient.getRetrofitClient(baseUrl);
        ApiService apiService = retrofit.create(ApiService.class);

        List<String> articleList = new ArrayList<>();
        List<String> imageList = new ArrayList<>();

        Call<Cities> call = apiService.getCities(url);
        call.enqueue(new Callback<Cities>() {
            @Override
            public void onResponse(Call<Cities> call, Response<Cities> response) {

                if (response.body() != null) {

                    for (City city : response.body().getGeonames()) {

                        articleList.add(city.getSummary());
                        imageList.add(city.getThumbnailImg());
                    }
                    feedback.onLoadSuccessful(articleList, imageList);
                }
            }

            @Override
            public void onFailure(Call<Cities> call, Throwable t) {

                feedback.onLoadFailure(t);
            }
        });
    }
}

package com.jay.country.model.network.api;

import com.jay.country.contract.DownloadedCountriesContract;
import com.jay.country.model.network.entity.Countries;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CountriesDownloader implements DownloadedCountriesContract.Model {

    private final String baseURL = "https://raw.githubusercontent.com/";

    private Retrofit retrofit = ApiClient.getRetrofitClient(baseURL);
    private ApiService apiService = retrofit.create(ApiService.class);

    @Override
    public void startDownloadCountriesList(DownloadFeedback feedback) {

        Call<Countries> call = apiService.getCountries();
        call.enqueue(new Callback<Countries>() {
            @Override
            public void onResponse(Call<Countries> call, Response<Countries> response) {

                if (response.body() != null) {

                    feedback.onDownloadSuccessful(Countries.getCountries(), response.body().getChina(),
                            response.body().getJapan(), response.body().getThailand(),
                            response.body().getIndia(), response.body().getMalaysia());
                }
            }

            @Override
            public void onFailure(Call<Countries> call, Throwable t) {

                feedback.onDownloadFailure(t);
            }
        });
    }
}

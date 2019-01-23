package com.jay.country.model.network.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class ApiClient {

    static Retrofit getRetrofitClient(String baseURL) {

        return new Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create()) // Converter library used to convert response into POJO
            .build();
    }
}

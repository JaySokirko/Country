package com.jay.country.model.database;

import android.annotation.SuppressLint;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jay.country.R;
import com.jay.country.contract.RestoredCountriesContract;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DataBaseTransaction implements RestoredCountriesContract.Model.DatabaseTransaction {


    private CitiesDatabase citiesDataBase;
    private Cities cities;

    private Gson gson = new Gson();
    private Type type = new TypeToken<ArrayList<String>>() {
    }.getType();

    @SuppressLint("CheckResult")
    @Override
    public void insertIntoDataBase(Context context, DatabaseInsertFeedback feedback,
                                   List<String> countries, List<String> china,
                                   List<String> japan, List<String> thailand, List<String> india,
                                   List<String> malaysia) {

        //converting ArrayList<String> to json for saving into the database
        cities = new Cities(gson.toJson(countries), gson.toJson(china), gson.toJson(japan),
                gson.toJson(thailand), gson.toJson(india), gson.toJson(malaysia));

        citiesDataBase = CitiesDatabase.getInstance(context);

        Completable.fromAction(() ->
                citiesDataBase.citiesDAO().insert(cities))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onComplete() {

                        feedback.onSaveIntoDataBaseFinish();
                    }

                    @Override
                    public void onError(Throwable e) {

                        feedback.onFailureSaveIntoDatabase(e);
                    }
                });
    }


    @SuppressLint("CheckResult")
    @Override
    public void restoreFromDataBase(Context context, DatabaseRestoreFeedback feedback) {

        citiesDataBase = CitiesDatabase.getInstance(context);

        List<String> countries = new ArrayList<>();
        List<String> china = new ArrayList<>();
        List<String> japan = new ArrayList<>();
        List<String> thailand = new ArrayList<>();
        List<String> india = new ArrayList<>();
        List<String> malaysia = new ArrayList<>();

        citiesDataBase.citiesDAO().getAllCities()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(cities -> {

                    if (cities != null) {

                        for (Cities city : cities) {

                            //Converting json to ArrayList<String>
                            countries.addAll(gson.fromJson(city.getCountry(), type));

                            china.addAll(gson.fromJson(city.getChina(), type));

                            japan.addAll(gson.fromJson(city.getJapan(), type));

                            thailand.addAll(gson.fromJson(city.getThailand(), type));

                            india.addAll(gson.fromJson(city.getIndia(), type));

                            malaysia.addAll(gson.fromJson(city.getMalaysia(), type));
                        }

                        feedback.onRestoreFromDatabaseSuccessful(countries, china, japan, thailand,
                                india, malaysia);

                    } else {
                        feedback.onRestoreFromDatabaseFailure(context.getResources()
                                .getString(R.string.loading_error));
                    }
                });
    }
}

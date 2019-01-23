package com.jay.country.model.database;

import android.annotation.SuppressLint;
import android.content.Context;

import com.google.gson.Gson;
import com.jay.country.contract.RestoredCountriesContract;

import org.json.JSONObject;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class DataBaseTransaction implements RestoredCountriesContract.Model.DatabaseTransactor {


    private CitiesDatabase citiesDataBase;
    private Cities cities;
    private Gson gson = new Gson();

    @SuppressLint("CheckResult")
    @Override
    public void insertIntoDataBase(Context context, DataBaseInferFeedback feedback,
                                   List<String> countries, List<String> china,
                                   List<String> japan, List<String> thailand, List<String> india,
                                   List<String> malaysia) {

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


    @Override
    public void restoreFromDataBase() {

    }
}

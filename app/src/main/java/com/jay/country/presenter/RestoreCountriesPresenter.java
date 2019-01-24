package com.jay.country.presenter;

import android.content.Context;

import com.jay.country.contract.RestoredCountriesContract;
import com.jay.country.model.database.DataBaseTransaction;

import java.util.List;

import javax.inject.Inject;

public class RestoreCountriesPresenter implements RestoredCountriesContract.Presenter,
        RestoredCountriesContract.Model.DatabaseTransaction.DatabaseRestoreFeedback {


    private RestoredCountriesContract.View view;

    private RestoredCountriesContract.Model.DatabaseTransaction model = new DataBaseTransaction();

    @Inject
    public RestoreCountriesPresenter(RestoredCountriesContract.View view) {
        this.view = view;
    }


    @Override
    public void getDataFromDatabase(Context context) {

        if (view != null){

            view.showProgressBar();

            model.restoreFromDataBase(context,this);
        }
    }


    @Override
    public void onDestroy() {

        view = null;
        model = null;
    }


    @Override
    public void onRestoreFromDatabaseSuccessful(List<String> countries,
                                                List<String> china, List<String> japan,
                                                List<String> thailand, List<String> india,
                                                List<String> malaysia) {

        if (view != null){

            view.onGetDataSuccessful(countries, china, japan, thailand, india, malaysia);
            view.hideProgressBarr();
        }
    }

    @Override
    public void onRestoreFromDatabaseFailure(String error) {

        if (view != null){

            view.onGetDataFailure(error);
            view.hideProgressBarr();
        }
    }
}

package com.jay.country.presenter;

import com.jay.country.contract.DownloadedCountriesContract;
import com.jay.country.model.network.api.CountriesDownloader;

import java.util.List;

import javax.inject.Inject;

public class DownloadedCountriesPresenter implements DownloadedCountriesContract.Presenter,
        DownloadedCountriesContract.Model.DownloadFeedback {

    DownloadedCountriesContract.View view;

    DownloadedCountriesContract.Model model = new CountriesDownloader();

    @Inject
    public DownloadedCountriesPresenter(DownloadedCountriesContract.View view) {
        this.view = view;
    }


    @Override
    public void downloadCountriesList() {

        if (view != null) {

            view.showProgressBar();

            model.startDownloadCountriesList(this);
        }
    }


    @Override
    public void onCitySelected(String city) {

        if (view != null) {

            //todo start detailed screen
        }
    }


    @Override
    public void onDestroy() {

        view = null;
        model = null;
    }


    @Override
    public void onDownloadSuccessful(List<String> china, List<String> japan, List<String> thailand,
                                     List<String> india, List<String> malaysia) {

        if (view != null) {

            view.downloadSuccessful(china, japan, thailand, india, malaysia);
            view.hideProgressBar();

            china.clear();
            japan.clear();
            thailand.clear();
            india.clear();
            malaysia.clear();
        }
    }


    @Override
    public void onDownloadFailure(Throwable throwable) {

        if (view != null) {

            view.downloadFailure(throwable);
            view.hideProgressBar();
        }
    }
}

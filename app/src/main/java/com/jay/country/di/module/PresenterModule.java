package com.jay.country.di.module;

import com.jay.country.contract.CityDetailedContract;
import com.jay.country.contract.DownloadedCountriesContract;
import com.jay.country.contract.RestoredCountriesContract;
import com.jay.country.presenter.RestoreCountriesPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    private DownloadedCountriesContract.View downloadedCountriesView;

    private CityDetailedContract.View cityDetailedView;

    private RestoredCountriesContract.View restoredCountriesView;

    public PresenterModule(DownloadedCountriesContract.View downloadedCountriesView) {
        this.downloadedCountriesView = downloadedCountriesView;
    }

    @Provides
    DownloadedCountriesContract.View provideDownloadedCountriesView(){
        return downloadedCountriesView;
    }


    public PresenterModule(CityDetailedContract.View cityDetailedView) {
        this.cityDetailedView = cityDetailedView;
    }

    @Provides
    CityDetailedContract.View provideCityDetailedView(){
        return cityDetailedView;
    }


    public PresenterModule(RestoredCountriesContract.View restoredCountriesView) {
        this.restoredCountriesView = restoredCountriesView;
    }

    @Provides
    RestoredCountriesContract.View provideRestoredCountriesView(){
        return restoredCountriesView;
    }
}

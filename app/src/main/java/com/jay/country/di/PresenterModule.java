package com.jay.country.di;

import com.jay.country.contract.DownloadedCountriesContract;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    private DownloadedCountriesContract.View downloadedCountriesView;

    public PresenterModule(DownloadedCountriesContract.View downloadedCountriesView) {
        this.downloadedCountriesView = downloadedCountriesView;
    }

    @Provides
    DownloadedCountriesContract.View provideDownloadedCountriesView(){
        return downloadedCountriesView;
    }
}

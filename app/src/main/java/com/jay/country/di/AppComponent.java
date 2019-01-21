package com.jay.country.di;

import com.jay.country.view.DownloadedCountriesActivity;

import dagger.Component;

@Component(modules = {PresenterModule.class, SharedPreferenciesModule.class})
public interface AppComponent {

    void inject(DownloadedCountriesActivity downloadedCountriesActivity);
}

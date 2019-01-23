package com.jay.country.di.component;

import com.jay.country.di.module.PresenterModule;
import com.jay.country.di.module.SharedPreferencesModule;
import com.jay.country.view.DownloadedCountriesActivity;

import dagger.Component;

@Component(modules = {PresenterModule.class, SharedPreferencesModule.class})
public interface AppComponent {

    void inject(DownloadedCountriesActivity downloadedCountriesActivity);
}

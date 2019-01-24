package com.jay.country.di.component;

import com.jay.country.di.module.PresenterModule;
import com.jay.country.di.module.SharedPreferencesModule;
import com.jay.country.view.CityDetailedActivity;
import com.jay.country.view.DownloadCountriesActivity;
import com.jay.country.view.RestoredCountriesActivity;

import dagger.Component;

@Component(modules = {PresenterModule.class, SharedPreferencesModule.class})
public interface AppComponent {

    void inject(DownloadCountriesActivity downloadCountriesActivity);

    void inject(CityDetailedActivity cityDetailedActivity);

    void inject(RestoredCountriesActivity restoredCountriesActivity);
}

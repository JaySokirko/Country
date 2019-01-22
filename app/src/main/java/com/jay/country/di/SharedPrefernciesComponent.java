package com.jay.country.di;

import com.jay.country.view.LauncherActivity;

import dagger.Component;

@Component(modules = SharedPreferencesModule.class)
public interface SharedPrefernciesComponent {

    void inject(LauncherActivity launcherActivity);
}

package com.jay.country.di.component;

import com.jay.country.di.module.SharedPreferencesModule;
import com.jay.country.view.LauncherActivity;

import dagger.Component;

@Component(modules = SharedPreferencesModule.class)
public interface SharedPreferencesComponent {

    void inject(LauncherActivity launcherActivity);
}

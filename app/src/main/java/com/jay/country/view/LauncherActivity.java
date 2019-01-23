package com.jay.country.view;

import android.content.Intent;
import android.os.Bundle;

import com.jay.country.di.component.DaggerSharedPreferencesComponent;
import com.jay.country.di.module.SharedPreferencesModule;
import com.jay.country.model.sharedpreferencies.SharedPreferencesManager;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;

public class LauncherActivity extends AppCompatActivity {


    @Inject
    SharedPreferencesManager preferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerSharedPreferencesComponent.builder()
                .sharedPreferencesModule(new SharedPreferencesModule(this))
                .build()
                .inject(this);

        //todo isCountriesAlreadyDownloaded
        boolean isDataAlreadyDownloaded = preferencesManager.getBoolean("downloadSuccess");

        if (isDataAlreadyDownloaded) {

            startActivity(new Intent(this, RestoredCountriesActivity.class));
        } else {
            startActivity(new Intent(this, DownloadedCountriesActivity.class));
        }
        finish();
    }
}

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

        boolean isDataAlreadyDownloaded = preferencesManager.getBoolean("downloadSuccess");

        //Check whether the list of countries has already been downloaded
        if (isDataAlreadyDownloaded) {

            startActivity(new Intent(this, RestoredCountriesActivity.class));
        } else {
            startActivity(new Intent(this, DownloadCountriesActivity.class));
        }
        finish();
    }
}

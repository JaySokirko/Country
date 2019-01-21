package com.jay.country.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.jay.country.di.DaggerSharedPrefernciesComponent;
import com.jay.country.di.SharedPreferenciesModule;
import com.jay.country.model.SharedPreferencesManager;

import javax.inject.Inject;

public class LauncherActivity extends AppCompatActivity {


    @Inject
    SharedPreferencesManager preferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerSharedPrefernciesComponent.builder()
                .sharedPreferenciesModule(new SharedPreferenciesModule(this))
                .build()
                .inject(this);

        //todo isCountriesAlreadyDownloaded

        startActivity(new Intent(this, DownloadedCountriesActivity.class));

        finish();
    }
}
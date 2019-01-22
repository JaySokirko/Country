package com.jay.country.model.sharedpreferencies;

import android.content.SharedPreferences;

import javax.inject.Inject;

public class SharedPreferencesManager {

    private SharedPreferences sharedPreferences;

    @Inject
    public SharedPreferencesManager(SharedPreferences mSharedPreferences) {
        this.sharedPreferences = mSharedPreferences;
    }

    public void putBoolean(String key, boolean b) {
        sharedPreferences.edit().putBoolean(key, b).apply();
    }

    public boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }
}

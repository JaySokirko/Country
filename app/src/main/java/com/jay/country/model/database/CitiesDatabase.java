package com.jay.country.model.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import static com.jay.country.model.database.CitiesDatabase.DATA_BASE_VERSION;

@Database(entities = Cities.class, version = DATA_BASE_VERSION)
public abstract class CitiesDatabase extends RoomDatabase {

    static final int DATA_BASE_VERSION = 1;
    private static final String DATA_BASE_NAME = "countries";

    private static CitiesDatabase citiesDataBase;

    public abstract CitiesDAO citiesDAO();

    public static CitiesDatabase getInstance(Context context) {

        if (citiesDataBase == null){

            citiesDataBase = Room.databaseBuilder(context, CitiesDatabase.class, DATA_BASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return citiesDataBase;
    }
}

package com.jay.country.model.database;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import io.reactivex.Flowable;

@Dao
public interface CitiesDAO {

    @Query("SELECT * FROM cities")
    Flowable<List<Cities>> getAllCities();

    @Insert
    void insert(Cities cities);
}

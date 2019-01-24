package com.jay.country.model.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cities")
public class Cities {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "country")
    private String country;

    @ColumnInfo(name = "china")
    private String china;

    @ColumnInfo(name = "japan")
    private String japan;

    @ColumnInfo(name = "thailand")
    private String thailand;

    @ColumnInfo(name = "india")
    private String india;

    @ColumnInfo(name = "malaysia")
    private String malaysia;

    Cities(String country, String china, String japan, String thailand, String india, String malaysia) {
        this.country = country;
        this.china = china;
        this.japan = japan;
        this.thailand = thailand;
        this.india = india;
        this.malaysia = malaysia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    String getChina() {
        return china;
    }

    String getJapan() {
        return japan;
    }

    String getThailand() {
        return thailand;
    }

    String getIndia() {
        return india;
    }

    String getMalaysia() {
        return malaysia;
    }
}

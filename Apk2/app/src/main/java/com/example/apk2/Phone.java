package com.example.apk2;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "phone")
public class Phone {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;

    @NonNull
    @ColumnInfo(name = "manufacturer")
    private String manufacturer;

    @NonNull
    @ColumnInfo(name = "model")
    private String model;

    @NonNull
    @ColumnInfo(name = "androidVersion")
    private String androidVersion;

    @NonNull
    @ColumnInfo(name = "webPage")
    private String webPage;

    public Phone(@NonNull String manufacturer, @NonNull String model, @NonNull String androidVersion, @NonNull String webPage) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.androidVersion = androidVersion;
        this.webPage = webPage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(@NonNull String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @NonNull
    public String getModel() {
        return model;
    }

    public void setModel(@NonNull String model) {
        this.model = model;
    }

    @NonNull
    public String getAndroidVersion() {
        return androidVersion;
    }

    public void setAndroidVersion(@NonNull String androidVersion) {
        this.androidVersion = androidVersion;
    }

    @NonNull
    public String getWebPage() {
        return webPage;
    }

    public void setWebPage(@NonNull String webPage) {
        this.webPage = webPage;
    }
}

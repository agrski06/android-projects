package com.example.apk2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PhoneDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(Phone phone);

    @Query("DELETE FROM Phone")
    void deleteAll();

    @Query("UPDATE Phone SET " +
            "manufacturer = :manuf, " +
            "model = :model, " +
            "androidVersion = :ver, " +
            "webPage = :page " +
            "WHERE id = :id")
    void update(long id, String manuf, String model, String ver, String page);

    @Query("SELECT * FROM Phone")
    LiveData<List<Phone>> getAllPhones();
}

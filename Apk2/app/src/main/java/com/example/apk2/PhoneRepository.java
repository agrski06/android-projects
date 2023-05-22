package com.example.apk2;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PhoneRepository {
    private final PhoneDao phoneDao;
    private final LiveData<List<Phone>> allPhones;

    PhoneRepository(Application application) {
        PhoneRoomDatabase phoneRoomDatabase = PhoneRoomDatabase.getDatabase(application);
        phoneDao = phoneRoomDatabase.elementDao();
        allPhones = phoneDao.getAllPhones();
    }

    LiveData<List<Phone>> getAllElements() {
        return allPhones;
    }

    void deleteAll() {
        PhoneRoomDatabase.databaseWriteExecutor.execute(phoneDao::deleteAll);
    }

    public void insert(Phone phone) {
        phoneDao.insert(phone);
    }

    public void udpate(long id, String manuf, String model, String ver, String page) {
        phoneDao.update(id, manuf, model, ver, page);
    }
}

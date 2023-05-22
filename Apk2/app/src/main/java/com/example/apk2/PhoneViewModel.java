package com.example.apk2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PhoneViewModel extends AndroidViewModel {
    private final PhoneRepository phoneRepository;
    private final LiveData<List<Phone>> livePhones;

    public PhoneViewModel(@NonNull Application application) {
        super(application);
        phoneRepository = new PhoneRepository(application);
        livePhones = phoneRepository.getAllElements();
    }

    LiveData<List<Phone>> getAllPhones() {
        return livePhones;
    }

    public void insert(Phone phone) {
        phoneRepository.insert(phone);
    }

    public void deleteAll() {
        phoneRepository.deleteAll();
    }

    public void update(long id, String manuf, String model, String ver, String page) {
        phoneRepository.udpate(id, manuf, model, ver, page);
    }
}


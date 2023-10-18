package com.example.taskeando.ui.config;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class ConfigViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ConfigViewModel() {

        mText = new MutableLiveData<>();
        mText.setValue("Fragment Config\nApp Tareas.");

    }

    public LiveData<String> getText() {
        return mText;
    }
}
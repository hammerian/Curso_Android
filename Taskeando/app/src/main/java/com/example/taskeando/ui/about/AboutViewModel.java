package com.example.taskeando.ui.about;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AboutViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AboutViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Aplicación de Tareas\nPara Curso de Android 2023");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
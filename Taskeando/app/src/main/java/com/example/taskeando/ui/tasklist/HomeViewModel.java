package com.example.taskeando.ui.tasklist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HomeViewModel() {

        mText = new MutableLiveData<>();
        mText.setValue("Fragment Home\nPulsa el botón para ir al listado.");

    }

    public LiveData<String> getText() {
        return mText;
    }
}
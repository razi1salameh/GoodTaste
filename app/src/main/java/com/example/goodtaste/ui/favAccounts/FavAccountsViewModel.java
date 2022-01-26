package com.example.goodtaste.ui.favAccounts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FavAccountsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FavAccountsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is grocery list fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
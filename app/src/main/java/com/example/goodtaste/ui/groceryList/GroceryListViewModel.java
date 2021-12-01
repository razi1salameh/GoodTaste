package com.example.goodtaste.ui.groceryList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GroceryListViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public GroceryListViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is grocery list fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
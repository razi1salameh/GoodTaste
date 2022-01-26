package com.example.goodtaste.ui.favRecipes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FavRecipesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FavRecipesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is grocery list fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
package com.example.goodtaste.ui.newRecipe;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NewRecipeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public NewRecipeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is my new recipe fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
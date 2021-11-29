package com.example.goodtaste.ui.myRecipes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyRecipesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MyRecipesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is my recipes fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
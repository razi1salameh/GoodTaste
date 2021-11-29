package com.example.goodtaste.ui.addFriend;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddFriendViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AddFriendViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is add Friend fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
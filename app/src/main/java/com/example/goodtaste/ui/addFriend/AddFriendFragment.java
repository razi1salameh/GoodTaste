package com.example.goodtaste.ui.addFriend;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.goodtaste.R;
import com.example.goodtaste.databinding.FragmentAddFriendBinding;

public class AddFriendFragment extends Fragment {

    private AddFriendViewModel addFriendViewModel;
    private FragmentAddFriendBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        addFriendViewModel = new ViewModelProvider(this).get(AddFriendViewModel.class);

        binding = FragmentAddFriendBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textAddFriend;
        addFriendViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
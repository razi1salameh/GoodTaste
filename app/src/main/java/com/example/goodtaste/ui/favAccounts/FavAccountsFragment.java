package com.example.goodtaste.ui.favAccounts;

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

import com.example.goodtaste.databinding.FragmentFavAccountsBinding;


public class FavAccountsFragment extends Fragment {

    private FavAccountsViewModel favAccountsViewModel;
    private FragmentFavAccountsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        favAccountsViewModel = new ViewModelProvider(this).get(FavAccountsViewModel.class);

        binding = FragmentFavAccountsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textViewFavAccounts;
//        groceryListViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

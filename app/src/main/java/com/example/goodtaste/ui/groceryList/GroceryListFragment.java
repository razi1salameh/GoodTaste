package com.example.goodtaste.ui.groceryList;

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

import com.example.goodtaste.databinding.FragmentGroceryListBinding;

public class GroceryListFragment extends Fragment {

    private GroceryListViewModel groceryListViewModel;
    private FragmentGroceryListBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        groceryListViewModel = new ViewModelProvider(this).get(GroceryListViewModel.class);

        binding = FragmentGroceryListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textViewGroceryList;
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

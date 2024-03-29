package com.example.goodtaste.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goodtaste.CardViewAdapterMinRecipe;
import com.example.goodtaste.R;
import com.example.goodtaste.Recipe;
import com.example.goodtaste.databinding.FragmentHomeBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private List<Recipe> recipes = new ArrayList<>();
    private RecyclerView recyclerView;
    private CardViewAdapterMinRecipe adapter;

    //gets the root of the real time DB in the FB console
    private FirebaseDatabase db = FirebaseDatabase.getInstance("https://goodtaste-30dbb-default-rtdb.europe-west1.firebasedatabase.app/");


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.recyclerViewMinRecipeList);

        //build a ref for user related data in real time DB using UID
        DatabaseReference myRef = db.getReference("Recipe");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                   Recipe re1 = dataSnapshot.getValue(Recipe.class);
                   recipes.add(re1);
                   adapter = new CardViewAdapterMinRecipe(getContext(), recipes);
                   GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1, LinearLayoutManager.VERTICAL, false);
                   recyclerView.setLayoutManager(gridLayoutManager);
                   recyclerView.setHasFixedSize(true);
                   recyclerView.setAdapter(adapter);
               }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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

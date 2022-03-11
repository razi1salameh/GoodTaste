package com.example.goodtaste.ui.newRecipe;

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
import com.example.goodtaste.Recipe;
import com.example.goodtaste.databinding.FragmentNewRecipeBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.List;

public class NewRecipeFragment extends Fragment {

    private FragmentNewRecipeBinding binding;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference reference;
    private FirebaseDatabase  db;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNewRecipeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        db = FirebaseDatabase.getInstance("https://goodtaste-30dbb-default-rtdb.europe-west1.firebasedatabase.app/");
        reference = db.getReference("Recipe");

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();


        //TODO: get all the values from the UI
        //public Recipe(String title, String image, String time, boolean favorite, String video, User creator, String category, ArrayList<Ingredient> ingredients, ArrayList<String> steps) {
        //
        Recipe recipe = new Recipe("Tabule","llll","456",true, "lll", user.getUid(),"cat" );
        addRecipe(recipe);
        return root;
    }

    public void addRecipe(Recipe recipe){
        String key = reference.push().getKey();
        reference = db.getReference("Recipe/"+key);
        recipe.setKey(key);
        reference.setValue(recipe);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
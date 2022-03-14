package com.example.goodtaste.ui.newRecipe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.goodtaste.Ingredient;
import com.example.goodtaste.R;
import com.example.goodtaste.Recipe;
import com.example.goodtaste.databinding.FragmentNewRecipeBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class NewRecipeFragment extends Fragment {

    private TextView textViewRecipesPicture, textViewRecipesVideo;
    private EditText editTextRecipesName, editTextRecipesHours, editTextRecipesMinutes, editTextRecipesCategory;
    private EditText editTextRecipesIngredientsName, editTextRecipesIngredientsAmount, editTextRecipesIngredientsUnit;
    private EditText editTextRecipesSteps;
    private Button buttonAddIngredients, buttonCreateRecipe;
    
    private FragmentNewRecipeBinding binding;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference reference;
    private FirebaseDatabase  db;
    private ArrayList<Ingredient> ingredients;


    //todo: findviewbyid, getText from all et + convert list to string attributes +button to add recipse to FB
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNewRecipeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        db = FirebaseDatabase.getInstance("https://goodtaste-30dbb-default-rtdb.europe-west1.firebasedatabase.app/");
        reference = db.getReference("Recipe");

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        ingredients = new ArrayList<>();

        textViewRecipesPicture = root.findViewById(R.id.textViewRecipesPicture);
        textViewRecipesVideo = root.findViewById(R.id.textViewRecipesVideo);
        editTextRecipesName = root.findViewById(R.id.editTextRecipesName);
        editTextRecipesHours = root.findViewById(R.id.editTextRecipesHours);
        editTextRecipesMinutes = root.findViewById(R.id.editTextRecipesMinutes);
        editTextRecipesCategory = root.findViewById(R.id.editTextRecipesCategory);
        editTextRecipesIngredientsName = root.findViewById(R.id.editTextRecipesIngredientsName);
        editTextRecipesIngredientsAmount = root.findViewById(R.id.editTextRecipesIngredientsAmount);
        editTextRecipesIngredientsUnit = root.findViewById(R.id.editTextRecipesIngredientsUnit);
        editTextRecipesSteps = root.findViewById(R.id.editTextRecipesSteps);
        buttonAddIngredients = root.findViewById(R.id.buttonAddIngredients);
        buttonCreateRecipe = root.findViewById(R.id.buttonCreateRecipe);

        buttonAddIngredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ingredients.add(new Ingredient(editTextRecipesIngredientsName.getText().toString(),
                        Double.parseDouble(editTextRecipesIngredientsAmount.getText().toString()),editTextRecipesIngredientsUnit.getText().toString() ));
            }
        });
       // buttonCreateRecipe.setOnClickListener();


        Recipe recipe = new Recipe("Tabule","llll","456", "lll", user.getUid(),"cat" );
      //  addRecipe(recipe);
        return root;
    }

    public void addRecipe(Recipe recipe){
        String key = reference.push().getKey();
        reference = db.getReference("Recipe/"+key);
        recipe.setIngredients(recipe.ArrayOfIngredientsToString(ingredients));
        recipe.setKey(key);
        reference.setValue(recipe);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
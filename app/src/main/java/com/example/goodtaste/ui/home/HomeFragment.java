package com.example.goodtaste.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.goodtaste.RecipeCustomsAdapter;
import com.example.goodtaste.Recipe;
import com.example.goodtaste.databinding.FragmentHomeBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;


    //the object of the view - design
    private ListView listViewOfRecipesFeed;
    //the object for the adapter connecting the data tp the view
    private RecipeCustomsAdapter recipeAdapter;
    //object containing the recipes to be displayed - Data
    private ArrayList<Recipe> list;


    //get instance of Authentication PROJECT IN FB console
    private FirebaseAuth maFirebaseAuth = FirebaseAuth.getInstance();
    //gets the root of the real time DB in the FB console
    private FirebaseDatabase database = FirebaseDatabase.getInstance("https://goodtaste-30dbb-default-rtdb.europe-west1.firebasedatabase.app/");


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        String UID = maFirebaseAuth.getUid();

        //build a ref for user related data in real time DB using UID
        DatabaseReference myRef = database.getReference("Users");

        //build the item you want to push into the FB
        //User u1 = new
        //Recipe re1 = new Recipe("rice", "TheIMAGE", "00:30", true, "TheVideo", new User());

        //adds an item to the FB under the referenced specified
        //myRef.push().setValue(re1);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                   Recipe re1 = dataSnapshot.getValue(Recipe.class);
               }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        /** The Array list **/


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

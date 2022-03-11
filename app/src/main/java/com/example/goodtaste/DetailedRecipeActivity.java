package com.example.goodtaste;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailedRecipeActivity extends AppCompatActivity {

    //The Image of the expanded recipe
    private ImageView imageViewExpandedRecipePicture;

    //Category, Time, Creator, Video
    private TextView textViewExpandedRecipeCategory, textViewExpandedRecipeTime, textViewExpandedRecipeCreator, textViewExpandedRecipeVideo;

    //Ingredients & Instructions
    private TextView textViewExpandedRecipeIngredients, textViewExpandedRecipeInstruction;

    //the button of the Favorites
    private TextView textViewExpandedRecipeAddToFavorites;

    //The button that clicks to minimize the recipe's details
    private ImageButton ImageButtonSeeLess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_recipe);

        Recipe recipe = (Recipe) getIntent().getSerializableExtra("recipe");

    }

}
package com.example.goodtaste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

        imageViewExpandedRecipePicture = findViewById(R.id.imageViewRecipePicture);
        textViewExpandedRecipeCategory  = findViewById(R.id.textViewExpandedRecipeCategory);
        textViewExpandedRecipeTime = findViewById(R.id.textViewExpandedRecipeTime);
        textViewExpandedRecipeCreator = findViewById(R.id.textViewExpandedRecipeCreator);
        textViewExpandedRecipeVideo = findViewById(R.id.textViewExpandedRecipeVideo);
        textViewExpandedRecipeIngredients = findViewById(R.id.textViewExpandedRecipeIngredients);
        textViewExpandedRecipeInstruction = findViewById(R.id.textViewExpandedRecipeInstruction);
        textViewExpandedRecipeAddToFavorites = findViewById(R.id.textViewExpandedRecipeAddToFavorites);
        ImageButtonSeeLess = findViewById(R.id.ImageButtonSeeLess);

        //ImageButtonSeeLess.setOnClickListener();


        if(recipe!=null){
            textViewExpandedRecipeCategory.setText(recipe.getCategory());
        }

    }

}
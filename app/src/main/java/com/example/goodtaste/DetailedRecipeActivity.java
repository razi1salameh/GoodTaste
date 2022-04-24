package com.example.goodtaste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
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

        imageViewExpandedRecipePicture = findViewById(R.id.imageViewExpandedRecipePicture);
        textViewExpandedRecipeCategory  = findViewById(R.id.textViewExpandedRecipeCategory);
        textViewExpandedRecipeTime = findViewById(R.id.textViewExpandedRecipeTime);
        textViewExpandedRecipeCreator = findViewById(R.id.textViewExpandedRecipeCreator);
        textViewExpandedRecipeVideo = findViewById(R.id.textViewExpandedRecipeVideo);
        textViewExpandedRecipeIngredients = findViewById(R.id.textViewExpandedRecipeIngredients);
        textViewExpandedRecipeInstruction = findViewById(R.id.textViewExpandedRecipeInstruction);
        textViewExpandedRecipeAddToFavorites = findViewById(R.id.textViewExpandedRecipeAddToFavorites);
        ImageButtonSeeLess = findViewById(R.id.ImageButtonSeeLess);


        if(recipe!=null){
            if(recipe.getImage() != null )
                imageViewExpandedRecipePicture.setImageBitmap(recipe.stringToBitmap(recipe.getImage()));
            textViewExpandedRecipeCategory.setText((recipe.getCategory()));
            textViewExpandedRecipeTime.setText(recipe.getTime()+"");
            textViewExpandedRecipeCreator.setText(recipe.getCreator());
            textViewExpandedRecipeIngredients.setText(StringOfIngredientsToSeparatedIngredients(recipe.getIngredients()));
            textViewExpandedRecipeInstruction.setText(recipe.getSteps());
        }

        //this button opens the gallery or camera so you can choose a picture from to the recipe
        ImageButtonSeeLess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DetailedRecipeActivity.this , NavDrawerActivity.class);
                startActivity(i);
            }
        });

    }

    //Turn object ingredient into string in order to store it in DB
    public Ingredient StringToIngredient (String si) {
        Ingredient ingr = new Ingredient("a",1,"tsp");
        int index = 0;
        String content = "";
        String[] obj = new String[3];
        for (int i=0 ; i< si.length() ; i++) {
            if (si.charAt(i) != '*')
                content += si.charAt(i);
            else {
                obj[index] = content;
                content = "";
                index++;
            }

        }
        ingr.setName(obj[0]);
        ingr.setAmount(Double.parseDouble(obj[1]));
        ingr.setUnit(obj[2]);
        return ingr;
    }

    //Turn the String of ingredients into a separatedString in oder to display it in DetailedRecipe
    public String StringOfIngredientsToSeparatedIngredients (String soi){
        String tempString = "";
        int count = 0;
        String organizedList = "";
        for(int i=0 ; i< soi.length() ; i++){
            tempString += soi.charAt(i);
            if (soi.charAt(i) == '*')
                count++;

            if(count == 3){
                Ingredient returnedIngredient = StringToIngredient(tempString);
                organizedList += returnedIngredient.getName()+" - ";
                organizedList += returnedIngredient.getAmount()+" ";
                organizedList += returnedIngredient.getUnit()+"\n";
                tempString = "";
                count = 0;
            }

        }
        return organizedList;
    }

}
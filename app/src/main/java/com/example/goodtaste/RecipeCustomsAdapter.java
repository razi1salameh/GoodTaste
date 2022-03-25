package com.example.goodtaste;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.List;

public class RecipeCustomsAdapter extends ArrayAdapter<Recipe> {

    private Context context;
    private int resource;

    public RecipeCustomsAdapter(Context context, int resource, List<Recipe> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource; //this the
    }

    @Override
    public View getView(int position, @Nullable View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null)
            view = LayoutInflater.from(context).inflate(resource, parent, false);

        Recipe recipe = getItem(position); //method from the android studio not related to the object recipe

        if (recipe != null) {
            TextView textViewRowName = view.findViewById(R.id.textViewRowName);
            ImageView imageViewRecipePicture = view.findViewById(R.id.imageViewRecipePicture);
            TextView textViewRowCategory = view.findViewById(R.id.textViewRowCategory);
            TextView textViewRowTime = view.findViewById(R.id.textViewRowTime);
            TextView textViewRowPerson = view.findViewById(R.id.textViewRowPerson);
            TextView textViewRowVideo = view.findViewById(R.id.textViewRowVideo);

            //for the this text view need to create the method the is "onClick" in the xml not sure if here or in ArrayListActivity
            TextView textViewRowFavorites = view.findViewById(R.id.textViewRowFavorites);
            ImageButton seeMoreImageButton = view.findViewById(R.id.ImageButtonSeeMore);

            seeMoreImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, DetailedRecipeActivity.class);
                    i.putExtra("Recipe",recipe);
                    context.startActivity(i);
                }
            });
            //NEED TO ADD THE OTHER PROPERTIES OT THE OBJECT AND ADD THE OBJECT USER (CREATOR)
            textViewRowName.setText(recipe.getTitle());
            //need to add the methods Bitmap --> String & String --> Bitmap
            //imageViewRecipePicture.setImageBitmap(stringToBitmap(recipe.getImage()));
            textViewRowCategory.setText(recipe.getCategory());
            textViewRowTime.setText(recipe.getTime());
            textViewRowPerson.setText(recipe.getCreator());
            textViewRowVideo.setText(recipe.getVideo());
        }
        return view;
    }

    //Need to add a cope of the object to I can add it later on in the arrayList of the favorites list in "Favorite recipes" page;
    public void addToFavoritesPage(){
        Recipe copyOfFavoriteRecipe = new Recipe();
    }
}


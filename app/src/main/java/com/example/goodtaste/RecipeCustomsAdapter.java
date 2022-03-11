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
        Recipe recipe = getItem(position); //method from the android studio not related to the item object
        if (recipe != null) {
            TextView textViewRowName = view.findViewById(R.id.textViewRowName);
            ImageView imageViewRecipePicture = view.findViewById(R.id.imageViewRecipePicture);
            TextView textViewRowCategory = view.findViewById(R.id.textViewRowCategory);
            TextView textViewRowTime = view.findViewById(R.id.textViewRowTime);
            TextView textViewRowPerson = view.findViewById(R.id.textViewRowPerson);
            TextView textViewRowVideo = view.findViewById(R.id.textViewRowVideo);
            TextView textViewRowFavorites = view.findViewById(R.id.textViewRowFavorites);
            ImageButton seeMoreImageButton = view.findViewById(R.id.ImageButtonSeeMore);

            seeMoreImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, DetailedRecipeActivity.class);
                    i.putExtra("recipe",recipe);
                    context.startActivity(i);
                }
            });
            //NEED TO ADD THE OTHER PROPERTIES OT THE OBJECT AND ADD THE OBJECT USER (CREATOR)
            textViewRowName.setText(recipe.getTitle());
        }
        return view;
    }
}


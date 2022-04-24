package com.example.goodtaste;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CardViewAdapterMinRecipe extends RecyclerView.Adapter<CardViewAdapterMinRecipe.MyViewHolder> {

    private Context context;
    private List<Recipe> recipes;

    public CardViewAdapterMinRecipe(Context context, List<Recipe> recipes){
        this.context = context;
        this.recipes = recipes;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recipe_row_min,parent,false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);
        if(recipes.get(position).getImage() != null)
            holder.imageViewRecipePicture.setImageBitmap(Recipe.stringToBitmap(recipes.get(position).getImage()));
        holder.textViewRowName.setText(recipes.get(position).getTitle());
        holder.textViewRowCategory.setText("Category : "+recipes.get(position).getCategory());
        holder.textViewRowTime.setText("Time : "+recipes.get(position).getTime());

        //this button opens the detailed recipe
        holder.ImageButtonSeeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailedRecipeActivity.class);
                intent.putExtra("recipe",recipe);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewRecipePicture;
        TextView textViewRowName, textViewRowCategory, textViewRowTime;
        TextView textViewRowPerson, textViewRowVideo, textViewRowFavorites;
        ImageButton ImageButtonSeeMore;
        static boolean isFilled = false;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            imageViewRecipePicture = itemView.findViewById(R.id.imageViewRecipePicture);
            textViewRowName = itemView.findViewById(R.id.textViewRowName);
            textViewRowCategory = itemView.findViewById(R.id.textViewRowCategory);
            textViewRowTime = itemView.findViewById(R.id.textViewRowTime);
            textViewRowPerson = itemView.findViewById(R.id.textViewRowPerson);
            textViewRowVideo = itemView.findViewById(R.id.textViewRowVideo);
            textViewRowFavorites = itemView.findViewById(R.id.textViewRowFavorites);
            ImageButtonSeeMore = itemView.findViewById(R.id.ImageButtonSeeMore);

            //this button adds the recipe to favorites and changes to fill star (as a recipe that has been added to fav)
            textViewRowFavorites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /** NEED TO ADD THE MINI RECIPE TO FAVORITES PAGE AND ALSO ADD TO FIREBASE AS ONE OF FAVORITES **/
                    if (!isFilled) {
                        textViewRowFavorites.setBackgroundResource(R.drawable.ic_star_fill);
                        isFilled = true;
                    }
                    else {
                        textViewRowFavorites.setBackgroundResource(R.drawable.ic_star_not_fill);
                        isFilled = false;
                    }
                }
            });

            //this button opens the detailed recipe
            ImageButtonSeeMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), DetailedRecipeActivity.class);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
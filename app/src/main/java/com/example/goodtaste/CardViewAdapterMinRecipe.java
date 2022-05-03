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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class CardViewAdapterMinRecipe extends RecyclerView.Adapter<CardViewAdapterMinRecipe.MyViewHolder> {

    private Context context;
    private List<Recipe> recipes;
    static boolean isFilled = false;

    //get instance of Authentication PROJECT IN FB console
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    //gets the root of the real time DB in the FB console
    private FirebaseDatabase db = FirebaseDatabase.getInstance("https://goodtaste-30dbb-default-rtdb.europe-west1.firebasedatabase.app/");

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
        Recipe clickedRecipe = recipes.get(position);
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
                intent.putExtra("recipe",clickedRecipe);
                context.startActivity(intent);
            }
        });

        /** To check why this code doean't wotk and gives an error **/
        //this button adds the recipe to favorites and changes to fill star (as a recipe that has been added to fav)
//        holder.textViewRowFavorites.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FavoriteRecipe favRecipe = new FavoriteRecipe();
//                DatabaseReference referenceToFavRecipe = db.getReference().child("Recipe").child(clickedRecipe.getKey());
//                favRecipe.setRecipesReference(referenceToFavRecipe);
//                favRecipe.setUsersFavorite(firebaseAuth.getCurrentUser().getUid());
//                String key = db.getReference().child("Favorites").push().getKey();
//                favRecipe.setKey(key);
//                if (!isFilled) {
//                    db.getReference().child("Favorites").child(key).setValue(favRecipe);
//                    holder.textViewRowFavorites.setBackgroundResource(R.drawable.ic_star_fill);
//                    isFilled = true;
//                }
//                else {
//                    db.getReference().child("Favorites").child(key).removeValue();
//                    holder.textViewRowFavorites.setBackgroundResource(R.drawable.ic_star_not_fill);
//                    isFilled = false;
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewRecipePicture;
        TextView textViewRowName, textViewRowCategory, textViewRowTime,
                textViewRowPerson, textViewRowVideo, textViewRowFavorites;
        ImageButton ImageButtonSeeMore;

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
        }
    }
}
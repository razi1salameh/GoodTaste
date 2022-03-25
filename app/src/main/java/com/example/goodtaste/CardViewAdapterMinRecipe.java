package com.example.goodtaste;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CardViewAdapterMinRecipe extends RecyclerView.Adapter<CardViewAdapterMinRecipe.MyViewHolder> {

    private Context context;
    private List<Recipe> recipes;

    public CardViewAdapterMinRecipe(Context context, List<String> titles, List<Integer> images){
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

   //     holder.imageViewRecipePicture.setImageBitmap(recipes.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageViewRecipePicture;
        TextView textViewRowName, textViewRowCategory, textViewRowTime;
        TextView textViewRowPerson, textViewRowVideo, textViewRowFavorites;
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

            ImageButtonSeeMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), DetailedRecipeActivity.class);
  //                  intent.putExtra("category",mTextview.getText());
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
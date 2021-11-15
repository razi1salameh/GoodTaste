package com.example.goodtaste;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomsAdapter extends ArrayAdapter<Recipe> {

    private Context context;
    private int resource;

    public CustomsAdapter(Context context, int resource, List<Recipe> objects) {
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
            TextView textViewRecipeName = view.findViewById(R.id.textViewRecipeName);
            ImageView imageViewRecipePicture = view.findViewById(R.id.imageViewRecipePicture);
            Button buttonOpenRecipeToView = view.findViewById(R.id.buttonOpenRecipeToView);
            Button buttonViewCreator = view.findViewById(R.id.buttonViewCreator);
            Button buttonViewVideo = view.findViewById(R.id.buttonViewVideo);
            Button buttonViewSave = view.findViewById(R.id.buttonViewSave);


            buttonOpenRecipeToView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "This item was added to shopping cart", Toast.LENGTH_LONG).show();
                }
            });
            //NEED TO ADD THE OTHER PROPERTIES OT THE OBJECT AND ADD THE OBJECT USER (CREATOR)
            textViewRecipeName.setText(recipe.getTitle());
        }
        return view;
    }
}


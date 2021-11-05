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

    public CustomsAdapter(@NonNull Context context, int resource, @NonNull List<Recipe> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource; //this the
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null)
            view = LayoutInflater.from(context).inflate(resource, parent, false);
        Recipe recipe = getItem(position); //method from the android studio not related to the item object
        if (recipe != null) {
            ImageView imageView = view.findViewById(R.id.imageItem);
            TextView textViewDescription = view.findViewById(R.id.textViewDesc);
            Button itemButton = view.findViewById(R.id.itemButton);

            itemButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "This item was added to shopping cart", Toast.LENGTH_LONG).show();
                }
            });

            imageView.setImageResource(recipe.getResid());
            textViewDescription.setText(recipe.getDescription());
        }
        return view;
    }
}


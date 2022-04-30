package com.example.goodtaste;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class GroceryListAdapter extends ArrayAdapter<GroceryList> {

    private int resource;
    private Context context;

    public GroceryListAdapter(@NonNull Context context, int resource, @NonNull List<GroceryList> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @Override
    public View getView(int position, @Nullable View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null)
            view = LayoutInflater.from(context).inflate(resource, parent, false);
        GroceryList groceryList1 = getItem(position); //method from the android studio not related to the item object
        if (groceryList1 != null) {
            TextView textViewGroceryListTitle = view.findViewById(R.id.textViewGroceryListTitle);
            TextView textViewGroceryListDescription = view.findViewById(R.id.textViewGroceryListDescription);

            textViewGroceryListTitle.setText(groceryList1.getListTitle());
            textViewGroceryListDescription.setText(groceryList1.getListDescription());
        }
        return view;
    }
}
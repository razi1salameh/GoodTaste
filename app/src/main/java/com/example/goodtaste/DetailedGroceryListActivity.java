package com.example.goodtaste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class DetailedGroceryListActivity extends AppCompatActivity {

    private TextView textViewExpandedGroceryListTitle, textViewExpandedGroceryListDescription;
    private ImageButton ImageButtonSeeLessGroceryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_grocery_list);

        GroceryList groceryList = (GroceryList) getIntent().getSerializableExtra("gl");

        textViewExpandedGroceryListTitle = findViewById(R.id.textViewExpandedGroceryListTitle);
        textViewExpandedGroceryListDescription = findViewById(R.id.textViewExpandedGroceryListDescription);
        ImageButtonSeeLessGroceryList = findViewById(R.id.ImageButtonSeeLessGroceryList);

        textViewExpandedGroceryListTitle.setText(groceryList.getListTitle());
        textViewExpandedGroceryListDescription.setText(groceryList.getListDescription());

        //this button goes to home page because still not sure how to open back the specific fragment (too complicated)
        ImageButtonSeeLessGroceryList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DetailedGroceryListActivity.this, NavDrawerActivity.class);
                startActivity(i);
            }
        });
    }
}
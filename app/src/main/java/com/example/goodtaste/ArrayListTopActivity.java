package com.example.goodtaste;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ArrayListTopActivity extends AppCompatActivity {

    //the object of the view - design
    private ListView listViewOfRecipesFeed;

    //the object for the adapter connecting the data tp the view
    private RecipeCustomsAdapter myAdapter;

    //object containing the recipes to be displayed - Data
    private ArrayList<Recipe> list;

    //the video of howto build the the arraylist activity of users
    //https://www.youtube.com/watch?v=joyKAkTX8nQ

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_list_top);
    }
}
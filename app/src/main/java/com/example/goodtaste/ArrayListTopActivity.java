package com.example.goodtaste;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ArrayListTopActivity extends AppCompatActivity {

    //the object of the view - design
    private ListView listViewOfRecipesFeed;

    //the object for the adapter connecting the data tp the view
    private CustomsAdapter myAdapter;

    //object containing the recipes to be displayed - Data
    private ArrayList<Recipe> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_list_top);
    }
}
package com.example.goodtaste;

import java.util.ArrayList;

public class Recipe {

    //properties
    //the name of the recipe
    private String title;

    //How much to prepare it
    private String time;

    //If the recipe is added to favorites it is true and star will be fill otherwise it is false and not fill
    private boolean favorite;

    //The recipe's creator
    private User creator;

    //The category of the recipe
    private String category;

    //List of the ingredients
    private ArrayList<Ingredient> ingredients;

    //Steps to make the recipe
    private ArrayList<String> steps;

}

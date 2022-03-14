package com.example.goodtaste;

import java.io.Serializable;
import java.util.ArrayList;

public class Recipe implements Serializable {

    //properties
    //the name of the recipe
    private String title;

    //Pic of the dish
    private String image;

    //How much to prepare it
    private String time;

    //Video of the steps to the recipe
    private String video;

    //The recipe's creator
    private String creator;

    //The category of the recipe
    private String category;

    //List of the ingredients
    private String ingredients;

    //Steps to make the recipe
    private String steps;

    private String key;

    public Recipe(String title, String image, String time, String video, String creator, String category) {
        this.title = title;
        this.image = image;
        this.time = time;
        this.video = video;
        this.creator = creator;
        this.category = category;
    }

    public Recipe() { }

    //Getters
    public String getTitle() { return title; }
    public String getImage() { return image; }
    public String getTime() { return time; }
    public String getVideo() { return video; }
    public String getCreator() { return creator; }
    public String getCategory() { return category; }
    public String getIngredients() { return ingredients; }
    public String getSteps() { return steps; }
    public String getKey() {
        return key;
    }

    //Setters
    public void setTitle(String title) { this.title = title; }
    public void setImage(String image) { this.image = image; }
    public void setTime(String time) { this.time = time; }
    public void setVideo(String video) { this.video = video; }
    public void setCreator(String creator) { this.creator = creator; }
    public void setCategory(String category) { this.category = category; }
    public void setIngredients(String ingredients) { this.ingredients = ingredients; }
    public void setSteps(String steps) { this.steps = steps; }
    public void setKey(String key) {
        this.key = key;
    }

    //Turn the arrayList of ingredients into a whole string in oder to store in in DB
    public String ArrayOfIngredientsToString (ArrayList<Ingredient> ai){
        String str = "";
        for (int i=0 ; i<ai.size() ; i++) {
            str += ai.get(i).ingredientToString();
        }
        return str;
    }

}

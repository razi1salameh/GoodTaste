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

    //If the recipe is added to favorites it is true and star will be fill otherwise it is false and not fill
    private boolean favorite;

    //Video of the steps to the recipe
    private String video;

    //The recipe's creator
    private String creator;

    //The category of the recipe
    private String category;

    //List of the ingredients
    private ArrayList<Ingredient> ingredients;

    //Steps to make the recipe
    private ArrayList<String> steps;

    private String key;

    public Recipe(String title, String image, String time, boolean favorite, String video, String creator, String category) {
        this.title = title;
        this.image = image;
        this.time = time;
        this.favorite = favorite;
        this.video = video;
        this.creator = creator;
        this.category = category;
        this.ingredients = new ArrayList<>();
        this.steps = new ArrayList<>();
    }

    public Recipe(){ }

    //Getters
    public String getTitle() { return title; }
    public String getImage() { return image; }
    public String getTime() { return time; }
    public boolean isFavorite() { return favorite; }
    public String getVideo() { return video; }
    public String getCreator() { return creator; }
    public String getCategory() { return category; }
    public ArrayList<Ingredient> getIngredients() { return ingredients; }
    public ArrayList<String> getSteps() { return steps; }

    //Setters
    public void setTitle(String title) { this.title = title; }
    public void setImage(String image) { this.image = image; }
    public void setTime(String time) { this.time = time; }
    public void setFavorite(boolean favorite) { this.favorite = favorite; }
    public void setVideo(String video) { this.video = video; }
    public void setCreator(String creator) { this.creator = creator; }
    public void setCategory(String category) { this.category = category; }
    public void setIngredients(ArrayList<Ingredient> ingredients) { this.ingredients = ingredients; }
    public void setSteps(ArrayList<String> steps) { this.steps = steps; }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

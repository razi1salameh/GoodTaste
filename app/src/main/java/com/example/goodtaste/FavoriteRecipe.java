package com.example.goodtaste;

import com.google.firebase.database.DatabaseReference;

public class FavoriteRecipe {

    //Reference for the favorite recipe that was clicked in the firebase
    private DatabaseReference recipesReference;

    //Uid of the user that made this favorite recipe
    private String usersFavorite;

    //Uid of the user that made this favorite recipe
    private String key;

    public FavoriteRecipe (){}

    //Getters
    public DatabaseReference getRecipesReference() {
        return recipesReference;
    }
    public String getUsersFavorite() {
        return usersFavorite;
    }
    public String getKey(){
        return key;
    }

    //Setters
    public void setRecipesReference(DatabaseReference recipesReference) {
        this.recipesReference = recipesReference;
    }
    public void setUsersFavorite(String usersFavorite) {
        this.usersFavorite = usersFavorite;
    }
    public void setKey(String key) {
        this.key = key;
    }
}

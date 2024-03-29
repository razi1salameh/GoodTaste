package com.example.goodtaste;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
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

    //The recipe unique Id
    private String key;

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


    //this function turns the image from string type to bitmap
    public static Bitmap stringToBitmap(String image){
        try {
            byte [] encodeByte= Base64.decode(image, Base64.DEFAULT);
            InputStream inputStream  = new ByteArrayInputStream(encodeByte);
            Bitmap bitmap  = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        }

        catch(Exception e) {
            e.getMessage();
            return null;
        }
    }


    //this function turns the image from string type to bitmap
    public static String bitmapToString(Bitmap image){
        ByteArrayOutputStream baos = new  ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }
}

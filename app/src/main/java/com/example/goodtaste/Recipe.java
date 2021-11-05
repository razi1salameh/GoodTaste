package com.example.goodtaste;

public class Recipe {

    //properties
    private String title;
    public static int LIKES=0;

    //constructor
    public Recipe(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

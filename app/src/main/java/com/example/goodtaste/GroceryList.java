package com.example.goodtaste;

import java.io.Serializable;

public class GroceryList implements Serializable  {
    //the notes title
    String title;

    //the notes text
    String description;

    public GroceryList(){}

    //Getters
    public String getListTitle() {
        return title;
    }
    public String getListDescription() {
        return description;
    }

    //Setters
    public void setListTitle(String title) {
        this.title = title;
    }
    public void setListDescription(String description) {
        this.description = description;
    }
}

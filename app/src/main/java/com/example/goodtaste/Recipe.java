package com.example.goodtaste;

public class Recipe {

    //properties
    private String title;
    private int time;

    //constructor
    public Recipe(String title, int time) {
        this.title = title;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public int getTime() {
        return time;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTime(int time) {
        this.time = time;
    }
}

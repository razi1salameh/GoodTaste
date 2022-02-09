package com.example.goodtaste;

public class User {

    //properties
    //The name of the user (the will be taken from for the signUp that the user make when he first enters
    private String username;

    //Image
    private String image;

    //Number of Recipes he created
    private int numOf;

    //If the user is added to favorites it is true and star will be fill otherwise it is false and not fill
    private boolean favorite;

    //Make recipe as (Chef, Hobby, Blogger, Parent) , going to be light grey
    private String type;

    //String bio
    private String bio;

    public User(String username, String image, int numOf, boolean favorite, String type, String bio) {
        this.username = username;
        this.image = image;
        this.numOf = numOf;
        this.favorite = favorite;
        this.type = type;
        this.bio = bio;
    }

    public User(){ }

    //Getters
    public String getUsername() { return username; }
    public String getImage() { return image; }
    public int getNumOf() { return numOf; }
    public boolean isFavorite() { return favorite; }
    public String getType() { return type; }
    public String getBio() { return bio; }

    //Setters
    public void setUsername(String username) { this.username = username; }
    public void setImage(String image) { this.image = image; }
    public void setNumOf(int numOf) { this.numOf = numOf; }
    public void setFavorite(boolean favorite) { this.favorite = favorite; }
    public void setType(String type) { this.type = type; }
    public void setBio(String bio) { this.bio = bio; }
}

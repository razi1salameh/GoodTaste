package com.example.goodtaste;

public class Ingredient {

    //Name of the ingredient
    private String name;

    //Amount
    private double amount;

    //Measurement unit
    private String unit;

    public Ingredient(String name, double amount, String unit) {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
    }


    //Getters
    public String getName() { return name; }
    public double getAmount() { return amount; }
    public String getUnit() { return unit; }

    //Setters
    public void setName(String name) { this.name = name; }
    public void setAmount(double amount) { this.amount = amount; }
    public void setUnit(String unit) { this.unit = unit; }

    //Turn object ingredient into string in order to store it in DB
    public String ingredientToString () {
        String str = "";
        str += this.name+"*";
        str += this.amount+"*";
        str += this.unit+"*";
        return str;
    }

}

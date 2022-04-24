package com.example.goodtaste;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class User {
    private String usersImage;
    private String FullName;
    private String Email;
    private String password;
    private int Age;
    private String personalAddress;
    private String workAddress;
    private String cellPhone;
    private String key;

    public User(){}

    //Getters
    public String getUsersImage() {
        return usersImage;
    }
    public String getFullName() {
        return FullName;
    }
    public String getEmail() {
        return Email;
    }
    public String getPassword() {
        return password;
    }
    public int getAge() {
        return Age;
    }
    public String getPersonalAddress() {
        return personalAddress;
    }
    public String getWorkAddress() {
        return workAddress;
    }
    public String getCellPhone() {
        return cellPhone;
    }
    public String getKey() {
        return key;
    }

    //Setters
    public void setUsersImage(String usersImage) {
        this.usersImage = usersImage;
    }
    public void setFullName(String fullName) {
        FullName = fullName;
    }
    public void setEmail(String email) {
        Email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setAge(int age) {
        Age = age;
    }
    public void setPersonalAddress(String personalAddress) {
        this.personalAddress = personalAddress;
    }
    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }
    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }
    public void setKey(String key) {
        this.key = key;
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

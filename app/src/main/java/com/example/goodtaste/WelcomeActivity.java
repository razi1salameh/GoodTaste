package com.example.goodtaste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void WelcomeMovePage(View view) {
        Intent i = new Intent(WelcomeActivity.this, LogInActivity.class);
        startActivity(i);
    }
}
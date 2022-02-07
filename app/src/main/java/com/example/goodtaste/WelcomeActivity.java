package com.example.goodtaste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    private TextView textViewTitle1, textViewExplore, textViewRegister, textViewClickHere, textViewNotUser;
    private Button buttonLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //this will create variables with the values from xml that belongs to this java page
        textViewTitle1 = findViewById(R.id.textViewTitle1);
        textViewExplore = findViewById(R.id.textViewExplore);
        textViewClickHere = findViewById(R.id.textViewClickHere);
        textViewRegister = findViewById(R.id.textViewRegister);
        textViewNotUser = findViewById(R.id.textViewNotUser);
        buttonLogIn = findViewById(R.id.buttonLogIn);
    }

    public void MoveToLogIn(View view) {
        Intent i = new Intent(WelcomeActivity.this, LogInActivity.class);
        startActivity(i);
    }

    public void MoveToRegister(View view) {
        startActivity(new Intent(WelcomeActivity.this, SignUpActivity.class));
    }
}
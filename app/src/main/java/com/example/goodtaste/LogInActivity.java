package com.example.goodtaste;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogInActivity extends AppCompatActivity implements DialogInterface.OnClickListener{

    private TextView textViewGoodTaste, textViewMember;
    private EditText editTextEmail, editTextPassword;
    private Button buttonLogIn, buttonSigUp;
    private Intent musicIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        textViewGoodTaste = findViewById(R.id.textViewGoodTaste);//.... NEEDS TO BE COUNTINUIED

        //this will start the service which in turn will the music
        musicIntent = new Intent(this, MusicService.class);
        startService(musicIntent);
    }


    @Override
    public void onClick(DialogInterface dialog, int which)
    {
        if(which==dialog.BUTTON_POSITIVE)
        {
            super.onBackPressed();
            dialog.cancel();
        }
        if(which==dialog.BUTTON_NEGATIVE)
        {
            dialog.cancel();
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("Are you sure?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes",this);
        builder.setNegativeButton("NO",this);
        AlertDialog dialog= builder.create();
        dialog.show();
    }

    public void logInMovePage(View view) {
        Intent intent = new Intent(this, NavDrawerActivity.class);
        startActivity(intent);
    }

}
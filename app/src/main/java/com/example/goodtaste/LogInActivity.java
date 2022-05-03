package com.example.goodtaste;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class LogInActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    private Button buttonLogIn;

    private Intent musicIntent;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        //this will create variables with the values from xml that belongs to this java page
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogIn = findViewById(R.id.buttonLogIn);

        //this will start the service which will turn on the music
        musicIntent = new Intent(this, MusicService.class);
        startService(musicIntent);

        //this gets the reference of the dataBase in the cloud
        firebaseAuth = FirebaseAuth.getInstance();


        SharedPreferences sp = getSharedPreferences("settings",MODE_PRIVATE);
        //saving the user's info in variables
        String usersEmail = sp.getString("Email", "");
        String usersPassword = sp.getString("Password", "");
        if(!usersEmail.equals("") && !usersPassword.equals("")){
            editTextEmail.setText(usersEmail);
            editTextPassword.setText(usersPassword);
        }


        buttonLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!editTextPassword.getText().toString().equals("") && !editTextEmail.getText().toString().equals(""))
                    login(editTextEmail.getText().toString(), editTextPassword.getText().toString());
                else
                    Toast.makeText(LogInActivity.this, "Please make sure email and password are filled", Toast.LENGTH_SHORT).show();
            }
        });
        }

    public void sharedP(){
        SharedPreferences sp = getSharedPreferences("settings",MODE_PRIVATE);
        //saving the user's info in variables
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("Email",editTextEmail.getText().toString());
        editor.putString("Password", editTextPassword.getText().toString());
        //saves and closes the file
        editor.commit();
    }

    public void login(String email, String password){
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //SignIn success update UI with signIn users information
                    Log.d("FIREBASE", "signInWithEmail:success");
                    Intent i = new Intent(LogInActivity.this, NavDrawerActivity.class);
                    sharedP();
                    startActivity(i);

                }else{
                    //If signIn fails display a massage to the user
                    Log.w("FIREBASE", "signInWithEmail:failure", task.getException());
                    Toast.makeText(LogInActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
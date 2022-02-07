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

public class LogInActivity extends AppCompatActivity {

    private TextView textViewMainTitle2, textViewLogIn;
    private EditText editTextEmail, editTextPassword;
    private Button buttonLogIn, buttonSigUp;

    private Intent musicIntent;

    private static final String TAG = "FIREBASE";
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        //this will create variables with the values from xml that belongs to this java page
        textViewMainTitle2 = findViewById(R.id.textViewMainTitle2);
        textViewLogIn = findViewById(R.id.textViewLogIn);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogIn = findViewById(R.id.buttonLogIn);
        buttonSigUp = findViewById(R.id.buttonSigUp);

        //this will start the service which in turn will the music
        musicIntent = new Intent(this, MusicService.class);
        startService(musicIntent);

        //this gets the reference of the dataBase in the cloud
        firebaseAuth = FirebaseAuth.getInstance();
    }


    public void login(String email, String password){
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //SignIn success update UI with signIn users information
                    Log.d(TAG, "signInWithEmail:success");
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    Intent i = new Intent(LogInActivity.this, NavDrawerActivity.class);
                    startActivity(i);

                }else{
                    //If signIn fails display a massage to the user
                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                    Toast.makeText(LogInActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void logInMovePage(View view) {
        String p = editTextPassword.getText().toString();
        String e = editTextEmail.getText().toString();
        if(!p.equals("") && !e.equals(""))
            login(editTextEmail.getText().toString(), editTextPassword.getText().toString());
        else
            Toast.makeText(LogInActivity.this, "Please make sure email and password are filled", Toast.LENGTH_SHORT).show();


    }

}
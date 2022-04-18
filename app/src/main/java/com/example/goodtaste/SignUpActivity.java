package com.example.goodtaste;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    private EditText editTextFullName, editTextEmail, editTextPassword, editTextConfirmPassword
            , editTextProfession , editTextPersonalAddress, editTextWorkAddress, editTextCellphone;
    private Button buttonSigUp;

    private static final String TAG = "FIREBASE";
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //this will create variables with the values from xml that belongs to this java page
        editTextFullName = findViewById(R.id.editTextFullName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        editTextProfession = findViewById(R.id.editTextProfession);
        editTextPersonalAddress = findViewById(R.id.editTextPersonalAddress);
        editTextWorkAddress = findViewById(R.id.editTextWorkAddress);
        editTextCellphone = findViewById(R.id.editTextCellphone);
        buttonSigUp = findViewById(R.id.buttonSigUp);

        //this gets the reference of the dataBase in the cloud
        firebaseAuth = FirebaseAuth.getInstance();
    }


    public void Submit(View view) {
        signup(editTextEmail.getText().toString(), editTextPassword.getText().toString());
    }

    public void signup(String email, String password){
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //SignIn success update UI with signIn users information
                    Log.d(TAG, "createUserWithEmail:success");
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    Intent i = new Intent(SignUpActivity.this, NavDrawerActivity.class);
                    startActivity(i);

                }else{
                    //If signIn fails display a massage to the user
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    Toast.makeText(SignUpActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
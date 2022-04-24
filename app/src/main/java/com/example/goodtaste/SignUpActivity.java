package com.example.goodtaste;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileNotFoundException;

public class SignUpActivity extends AppCompatActivity {

    private EditText editTextFullName, editTextEmail, editTextPassword, editTextConfirmPassword
            , editTextAge, editTextPersonalAddress, editTextWorkAddress, editTextCellphone;
    private Button buttonSigUp;
    private ImageView imageViewTheProfileImageInSignUp, imageViewTheEditProfileImageInSignUp;

    private static final String TAG = "FIREBASE";
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase db;
    private static final int CAMERA_REQUEST = 0;
    private static final int GALLERY_REQUEST = 1;
    private Bitmap profilesImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        db = FirebaseDatabase.getInstance("https://goodtaste-30dbb-default-rtdb.europe-west1.firebasedatabase.app/");

        //this will create variables with the values from xml that belongs to this java page
        editTextFullName = findViewById(R.id.editTextFullName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        editTextAge = findViewById(R.id.editTextAge);
        editTextPersonalAddress = findViewById(R.id.editTextPersonalAddress);
        editTextWorkAddress = findViewById(R.id.editTextWorkAddress);
        editTextCellphone = findViewById(R.id.editTextCellphone);
        imageViewTheProfileImageInSignUp = findViewById(R.id.imageViewTheProfileImageInSignUp);
        imageViewTheEditProfileImageInSignUp = findViewById(R.id.imageViewTheEditProfileImageInSignUp);
        buttonSigUp = findViewById(R.id.buttonSigUp);

        //this gets the reference of the dataBase in the cloud
        firebaseAuth = FirebaseAuth.getInstance();

        //this (the pen) opens the dialog for gallery of camera for the user to choose his profile picture
        imageViewTheEditProfileImageInSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });

        //this (the circle) opens the dialog for gallery of camera for the user to choose his profile picture
        imageViewTheProfileImageInSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });

        //this button creates a new user with his following info and checks before if fields are empty and if passwords matches
        buttonSigUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!editTextFullName.getText().toString().equals("") && !editTextConfirmPassword.getText().toString().equals("")) {
                    if (editTextPassword.getText().toString().equals(editTextConfirmPassword.getText().toString()))
                        signup(editTextEmail.getText().toString(), editTextPassword.getText().toString());
                    else
                        Toast.makeText(SignUpActivity.this, "Make sure passwords match", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(SignUpActivity.this, "Make sure your full name and email and passwords are filled", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void signup(String email, String password){
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //SignIn success update UI with signIn users information
                    Log.d(TAG, "createUserWithEmail:success");
                    User userSignUp = new User();
                    userSignUp.setFullName(editTextFullName.getText().toString());
                    userSignUp.setEmail(editTextEmail.getText().toString());
                    userSignUp.setPassword(editTextPassword.getText().toString());
                    userSignUp.setAge(Integer.parseInt(editTextAge.getText().toString()));
                    userSignUp.setPersonalAddress(editTextPersonalAddress.getText().toString());
                    userSignUp.setWorkAddress(editTextWorkAddress.getText().toString());
                    userSignUp.setCellPhone(editTextCellphone.getText().toString());
                    if (profilesImage != null)
                        userSignUp.setUsersImage(User.bitmapToString(profilesImage));
                    db.getReference().child("Users").push().child("profileInfo").setValue(userSignUp);
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

    // Select image from camera and gallery
    private void selectImage() {
        try {
            PackageManager pm = this.getPackageManager();
            int hasPerm = pm.checkPermission(Manifest.permission.CAMERA, this.getPackageName());

            if (hasPerm == PackageManager.PERMISSION_GRANTED) {
                final CharSequence[] options = {"Take Photo", "Choose From Gallery","Cancel"};
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Select Option");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (options[item].equals("Take Photo")) {
                            dialog.dismiss();
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, CAMERA_REQUEST);
                        } else if (options[item].equals("Choose From Gallery")) {
                            dialog.dismiss();
                            Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(pickPhoto, GALLERY_REQUEST);
                        } else if (options[item].equals("Cancel")) {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            }
            else
                Toast.makeText(this, "Camera Permission error", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Camera Permission error", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAMERA_REQUEST) {
            if (resultCode == RESULT_OK) {
                Bitmap picture = (Bitmap) data.getExtras().get("data");
                //set image captured to be the new image
                profilesImage = picture;
                imageViewTheProfileImageInSignUp.setImageBitmap(picture);
            }
        }
        else{
            if(resultCode == RESULT_OK){
                Uri targetUri = data.getData();
                try {
                    //Decode an input stream into bitmap
                    Bitmap picture = BitmapFactory.decodeStream(this.getContentResolver().openInputStream(targetUri));
                    profilesImage = picture;
                    imageViewTheProfileImageInSignUp.setImageBitmap(picture);
                }
                catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                }
                }
            }
        }
}

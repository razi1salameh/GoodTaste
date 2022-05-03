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
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileNotFoundException;

public class EditProfileActivity extends AppCompatActivity {

    private ImageView imageViewEditProfileImage;
    private EditText editTextEditUsersName, editTextEditUsersBio, editTextEditUsersAge, editTextEditUsersPersonalAddress
            , editTextEditUsersWorkAddress, editTextEditUsersPhoneNumber;
    private LinearLayout linearLayoutSaveProfile, linearLayoutEditProfileImageInEditProfile;

    //get instance of Authentication PROJECT IN FB console
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    //gets the root of the real time DB in the FB console
    private FirebaseDatabase db = FirebaseDatabase.getInstance("https://goodtaste-30dbb-default-rtdb.europe-west1.firebasedatabase.app/");

    private static final int CAMERA_REQUEST = 0;
    private static final int GALLERY_REQUEST = 1;
    private Bitmap updatedProfilesImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        DatabaseReference referenceForUser = db.getReference().child("Users").child(firebaseAuth.getCurrentUser().getUid());
        referenceForUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()) {
                    User currentUser = dataSnapshot.getValue(User.class);
                    if(currentUser.getUsersImage() != null)
                        imageViewEditProfileImage.setImageBitmap(User.stringToBitmap(currentUser.getUsersImage()));
                    editTextEditUsersName.setText(currentUser.getFullName());
                    if(currentUser.getBio() != null)
                        editTextEditUsersBio.setText(currentUser.getBio());
                    if(currentUser.getAge() != null)
                        editTextEditUsersAge.setText(Double.toString(currentUser.getAge()));
                    if(currentUser.getPersonalAddress() != null)
                        editTextEditUsersPersonalAddress.setText(currentUser.getPersonalAddress());
                    if(currentUser.getWorkAddress() != null)
                        editTextEditUsersWorkAddress.setText(currentUser.getWorkAddress());
                    if(currentUser.getCellPhone() != null)
                        editTextEditUsersPhoneNumber.setText(currentUser.getCellPhone());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //email is not one of the editable values because it was set in the authentication
        imageViewEditProfileImage = findViewById(R.id.imageViewEditProfileImage);
        editTextEditUsersName = findViewById(R.id.editTextEditUsersName);
        editTextEditUsersBio = findViewById(R.id.editTextEditUsersBio);
        editTextEditUsersAge = findViewById(R.id.editTextEditUsersAge);
        editTextEditUsersPersonalAddress = findViewById(R.id.editTextEditUsersPersonalAddress);
        editTextEditUsersWorkAddress = findViewById(R.id.editTextEditUsersWorkAddress);
        editTextEditUsersPhoneNumber = findViewById(R.id.editTextEditUsersPhoneNumber);
        linearLayoutSaveProfile = findViewById(R.id.linearLayoutSaveProfile);
        linearLayoutEditProfileImageInEditProfile = findViewById(R.id.linearLayoutEditProfileImageInEditProfile);

        //this (the pen) opens the dialog for gallery of camera for the user to choose his profile picture
        linearLayoutEditProfileImageInEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });

        //this text view will set the new values in the realtime database
        linearLayoutSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User updatedUser = new User();
                updatedUser.setFullName(editTextEditUsersName.getText().toString());
                updatedUser.setBio(editTextEditUsersBio.getText().toString());
                updatedUser.setAge(Double.parseDouble(editTextEditUsersAge.getText().toString()));
                updatedUser.setPersonalAddress(editTextEditUsersPersonalAddress.getText().toString());
                updatedUser.setWorkAddress(editTextEditUsersWorkAddress.getText().toString());
                updatedUser.setCellPhone(editTextEditUsersPhoneNumber.getText().toString());
                if (updatedProfilesImage != null)
                    updatedUser.setUsersImage(User.bitmapToString(updatedProfilesImage));
                String currentUser = firebaseAuth.getCurrentUser().getUid();
                db.getReference().child("Users").child(currentUser).child("ProfileInfo").setValue(updatedUser);

                Toast.makeText(EditProfileActivity.this, "Your profile information has been updated, go to your profile page to see the changes", Toast.LENGTH_LONG).show();
                Intent i = new Intent(EditProfileActivity.this, NavDrawerActivity.class);
                startActivity(i);
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
                updatedProfilesImage = picture;
                imageViewEditProfileImage.setImageBitmap(picture);
            }
        }
        else{
            if(resultCode == RESULT_OK){
                Uri targetUri = data.getData();
                try {
                    //Decode an input stream into bitmap
                    Bitmap picture = BitmapFactory.decodeStream(this.getContentResolver().openInputStream(targetUri));
                    updatedProfilesImage = picture;
                    imageViewEditProfileImage.setImageBitmap(picture);
                }
                catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
package com.example.goodtaste.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.goodtaste.DetailedGroceryListActivity;
import com.example.goodtaste.EditProfileActivity;
import com.example.goodtaste.NavDrawerActivity;
import com.example.goodtaste.R;
import com.example.goodtaste.User;
import com.example.goodtaste.databinding.FragmentProfileBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private ImageView imageViewBackToHomePage, imageViewProfileImage;
    private TextView textViewUsersName, textViewPersonalInfo, textViewUsersBio, textViewUsersAge,
            textViewUsersPersonalAddress,textViewUsersWorkAddress, textViewUsersEmail, textViewUsersPhoneNumber;
    private LinearLayout linearLayoutEditProfile;

    //get instance of Authentication PROJECT IN FB console
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    //gets the root of the real time DB in the FB console
    private FirebaseDatabase db = FirebaseDatabase.getInstance("https://goodtaste-30dbb-default-rtdb.europe-west1.firebasedatabase.app/");

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        imageViewBackToHomePage = root.findViewById(R.id.imageViewBackToHomePage);
        imageViewProfileImage = root.findViewById(R.id.imageViewProfileImage);
        textViewUsersName = root.findViewById(R.id.textViewUsersName);
        textViewPersonalInfo = root.findViewById(R.id.textViewPersonalInfo);
        textViewUsersBio = root.findViewById(R.id.textViewUsersBio);
        textViewUsersAge = root.findViewById(R.id.textViewUsersAge);
        textViewUsersPersonalAddress = root.findViewById(R.id.textViewUsersPersonalAddress);
        textViewUsersWorkAddress = root.findViewById(R.id.textViewUsersWorkAddress);
        textViewUsersEmail = root.findViewById(R.id.textViewUsersEmail);
        textViewUsersPhoneNumber = root.findViewById(R.id.textViewUsersPhoneNumber);

        linearLayoutEditProfile = root.findViewById(R.id.linearLayoutEditProfile);

        DatabaseReference referenceForUser = db.getReference().child("Users").child(firebaseAuth.getCurrentUser().getUid());
        referenceForUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()) {
                    User tempUser = dataSnapshot.getValue(User.class);
                    loadProfilePage(tempUser);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //this button goes to the edit page where is the text is already set and the user can change it
        linearLayoutEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(i);
            }
        });

        //this button goes back to home page
        imageViewBackToHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), NavDrawerActivity.class);
                startActivity(i);
            }
        });

        return root;
    }

    private void loadProfilePage(User tempUser){
        if(tempUser.getUsersImage() != null)
            imageViewProfileImage.setImageBitmap(User.stringToBitmap(tempUser.getUsersImage()));
        textViewUsersName.setText(tempUser.getFullName());
        textViewUsersEmail.setText(firebaseAuth.getCurrentUser().getEmail());
        if(tempUser.getBio() != null)
            textViewUsersBio.setText(tempUser.getBio());
        if(tempUser.getAge() != null)
            textViewUsersAge.setText(Double.toString(tempUser.getAge()));
        if(tempUser.getPersonalAddress() != null)
            textViewUsersPersonalAddress.setText(tempUser.getPersonalAddress());
        if(tempUser.getWorkAddress() != null)
            textViewUsersWorkAddress.setText(tempUser.getWorkAddress());
        if(tempUser.getCellPhone() != null)
            textViewUsersPhoneNumber.setText(tempUser.getCellPhone());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
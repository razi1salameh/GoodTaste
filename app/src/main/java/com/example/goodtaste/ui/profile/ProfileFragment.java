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

import com.example.goodtaste.NavDrawerActivity;
import com.example.goodtaste.R;
import com.example.goodtaste.databinding.FragmentProfileBinding;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private ImageView imageViewBackToHomePage, imageViewProfileImage;
    private TextView textViewUsersName, textViewPersonalInfo, textViewUsersBio, textViewUsersProfession;
    private TextView textViewUsersPersonalAddress,textViewUsersWorkAddress, textViewUsersEmail, textViewUsersPhoneNumber;
    private LinearLayout linearLayoutSwitchSectionsInProfile, linearLayoutEditProfile;
    private FirebaseAuth firebaseAuth;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //this gets the reference of the dataBase in the cloud
        firebaseAuth = FirebaseAuth.getInstance();

        String UID = firebaseAuth.getUid();


        imageViewBackToHomePage = root.findViewById(R.id.imageViewBackToHomePage);
        imageViewProfileImage = root.findViewById(R.id.imageViewProfileImage);
        textViewUsersName = root.findViewById(R.id.textViewUsersName);
        textViewPersonalInfo = root.findViewById(R.id.textViewPersonalInfo);
        textViewUsersBio = root.findViewById(R.id.textViewUsersBio);
        textViewUsersProfession = root.findViewById(R.id.textViewUsersProfession);
        textViewUsersPersonalAddress = root.findViewById(R.id.textViewUsersPersonalAddress);
        textViewUsersWorkAddress = root.findViewById(R.id.textViewUsersWorkAddress);
        textViewUsersEmail = root.findViewById(R.id.textViewUsersEmail);
        textViewUsersPhoneNumber = root.findViewById(R.id.textViewUsersPhoneNumber);

        linearLayoutSwitchSectionsInProfile = root.findViewById(R.id.linearLayoutSwitchSectionsInProfile);
        linearLayoutEditProfile = root.findViewById(R.id.linearLayoutEditProfile);

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
package com.example.goodtaste;

import android.app.AlarmManager;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.goodtaste.ui.home.HomeFragment;
import com.example.goodtaste.ui.profile.ProfileFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.goodtaste.databinding.ActivityNavDrawerBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NavDrawerActivity extends AppCompatActivity implements DialogInterface.OnClickListener {

    private static final int NOTIFICATION_REMINDER_NIGHT = 1;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityNavDrawerBinding binding;

    //get instance of Authentication PROJECT IN FB console
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    //gets the root of the real time DB in the FB console
    private FirebaseDatabase db = FirebaseDatabase.getInstance("https://goodtaste-30dbb-default-rtdb.europe-west1.firebasedatabase.app/");

    private MenuItem viewLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNavDrawerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarNavDrawer.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        DatabaseReference referenceForUser = db.getReference().child("Users").child(firebaseAuth.getCurrentUser().getUid());
        referenceForUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()) {
                    User tempUser = dataSnapshot.getValue(User.class);
                    loadNavHeader(tempUser);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_fav_recipes, R.id.nav_home, R.id.nav_myGrocery_lists, R.id.nav_myRecipes, R.id.nav_newRecipe, R.id.nav_profile).setOpenableLayout(drawer).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_nav_drawer);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        Intent notifyIntent = new Intent(this,NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, NOTIFICATION_REMINDER_NIGHT, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        //alarmManager.set(AlarmManager.RTC_WAKEUP, 100, pendingIntent); add need to add it in the button when the user adds new recipe
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),
                1000 * 60 * 2, pendingIntent);

        //when this view is clicked the the user will logOut
//        firebaseAuth.getInstance().signOut();
//        Toast.makeText(NavDrawerActivity.this, "Logged Out", Toast.LENGTH_SHORT).show();
//        startActivity(new Intent(NavDrawerActivity.this, WelcomeActivity.class));
    }

    private void loadNavHeader(User tempUser){
        NavigationView navigationView = binding.navView;
        View headerView = navigationView.getHeaderView(0);
        if(tempUser.getUsersImage() !=null){
            ImageView imageViewTheHeaderProfileImage = headerView.findViewById(R.id.imageViewTheHeaderProfileImage);
            imageViewTheHeaderProfileImage.setImageBitmap(User.stringToBitmap(tempUser.getUsersImage()));
        }
        TextView textViewTheHeaderUsername = headerView.findViewById(R.id.textViewTheHeaderUsername);
        textViewTheHeaderUsername.setText(tempUser.getFullName());

        TextView textViewTheHeaderEmail = headerView.findViewById(R.id.textViewTheHeaderEmail);
        textViewTheHeaderEmail.setText(firebaseAuth.getCurrentUser().getEmail());
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_nav_drawer);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
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
}
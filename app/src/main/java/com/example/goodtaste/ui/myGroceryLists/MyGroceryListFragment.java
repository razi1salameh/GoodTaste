package com.example.goodtaste.ui.myGroceryLists;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.goodtaste.DetailedGroceryListActivity;
import com.example.goodtaste.GroceryList;
import com.example.goodtaste.GroceryListAdapter;
import com.example.goodtaste.Ingredient;
import com.example.goodtaste.NavDrawerActivity;
import com.example.goodtaste.R;
import com.example.goodtaste.User;
import com.example.goodtaste.databinding.FragmentMyGroceryListsBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyGroceryListFragment extends Fragment {

    private FragmentMyGroceryListsBinding binding;
    private EditText editTextGroceryListTitle, editTextGroceryListDescription;
    private Button buttonCreateNewGroceryList;
    private ListView listViewGroceryLists;

    //object containing the items to be displayed - Data
    private ArrayList<GroceryList> groceryLists;
    //the object for the adapter connecting the data to the view
    private GroceryListAdapter adapterForGroceryList;

    //get instance of Authentication PROJECT IN FB console
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    //gets the root of the real time DB in the FB console
    private FirebaseDatabase db = FirebaseDatabase.getInstance("https://goodtaste-30dbb-default-rtdb.europe-west1.firebasedatabase.app/");

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMyGroceryListsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        editTextGroceryListTitle = root.findViewById(R.id.editTextGroceryListTitle);
        editTextGroceryListDescription = root.findViewById(R.id.editTextGroceryListDescription);
        buttonCreateNewGroceryList = root.findViewById(R.id.buttonCreateNewGroceryList);
        listViewGroceryLists = root.findViewById(R.id.listViewGroceryLists);

        buttonCreateNewGroceryList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editTextGroceryListTitle.getText().toString().equals("") && !editTextGroceryListDescription.getText().toString().equals(""))
                    addGroceryList();
                else
                    Toast.makeText(getActivity(), "Make sure Title and description aren't empty", Toast.LENGTH_SHORT).show();
            }
        });

        groceryLists = new ArrayList<>();
        adapterForGroceryList = new GroceryListAdapter(getActivity(), R.layout.grocery_list_item, groceryLists);
        listViewGroceryLists.setAdapter(adapterForGroceryList);

        DatabaseReference referenceForGroceryLists = db.getReference().child("Users").child(firebaseAuth.getCurrentUser().getUid()).child("GroceryLists");
        referenceForGroceryLists.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                groceryLists.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()) {
                    GroceryList tempGroceryList = dataSnapshot.getValue(GroceryList.class);
                    groceryLists.add(tempGroceryList);
                }
                adapterForGroceryList.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //on item clicked in groceryLists open detailed groceryListActivity with the specific clicked item's details
        listViewGroceryLists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), DetailedGroceryListActivity.class);
                intent.putExtra("clickedGroceryList", groceryLists.get(i));
                startActivity(intent);
            }
        });

        //on item long clicked in groceryLists gets deleted from the view and also from FB
        listViewGroceryLists.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                String clickedItemsKey = groceryLists.get(i).getKey();
                String currentUser = firebaseAuth.getCurrentUser().getUid();
                db.getReference().child("Users").child(currentUser).child("GroceryLists").child(clickedItemsKey).removeValue();
                groceryLists.remove(i);
                return true;
            }
        });

        return root;
    }

    public void addGroceryList(){
        GroceryList groceryList = new GroceryList();
        groceryList.setListTitle(editTextGroceryListTitle.getText().toString());
        groceryList.setListDescription(editTextGroceryListDescription.getText().toString());
        editTextGroceryListTitle.setText("");
        editTextGroceryListDescription.setText("");
        String currentUser = firebaseAuth.getCurrentUser().getUid();
        String key = db.getReference().child("Users").child(currentUser).child("GroceryLists").push().getKey();
        groceryList.setKey(key);
        db.getReference().child("Users").child(currentUser).child("GroceryLists").child(key).setValue(groceryList);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

package com.example.goodtaste.ui.newRecipe;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.goodtaste.Ingredient;
import com.example.goodtaste.NavDrawerActivity;
import com.example.goodtaste.R;
import com.example.goodtaste.Recipe;
import com.example.goodtaste.databinding.FragmentNewRecipeBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class NewRecipeFragment extends Fragment {

    private TextView textViewRecipesPicture, textViewRecipesVideo, textViewRecipesIngredientsList, textViewRecipesIngredientsDelete;
    private ImageView imageViewRecipesBackPicture, imageViewRecipesBackVideo;
    private EditText editTextRecipesName, editTextRecipesHours, editTextRecipesMinutes, editTextRecipesCategory,
            editTextRecipesIngredientsName, editTextRecipesIngredientsAmount, editTextRecipesIngredientsUnit,editTextRecipesSteps;
    private Button buttonAddIngredients, buttonCreateRecipe;

    private FragmentNewRecipeBinding binding;
    private ArrayList<Ingredient> ingredients;
    private String oneIngredient ="", updateView = "";
    private static int numOfIngredient = 0 ;
    private static final int CAMERA_REQUEST = 0;
    private static final int GALLERY_REQUEST = 1;
    private Bitmap recipesImage;

    private DatabaseReference reference;
    //get instance of Authentication PROJECT IN FB console
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    //gets the root of the real time DB in the FB console
    private FirebaseDatabase db = FirebaseDatabase.getInstance("https://goodtaste-30dbb-default-rtdb.europe-west1.firebasedatabase.app/");

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNewRecipeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        reference = db.getReference("Recipe");

        ingredients = new ArrayList<>();

        textViewRecipesPicture = root.findViewById(R.id.textViewRecipesPicture);
        textViewRecipesVideo = root.findViewById(R.id.textViewRecipesVideo);
        textViewRecipesIngredientsList = root.findViewById(R.id.textViewRecipesIngredientsList);
        textViewRecipesIngredientsDelete = root.findViewById(R.id.textViewRecipesIngredientsDelete);
        imageViewRecipesBackPicture = root.findViewById(R.id.imageViewRecipesBackPicture);
        imageViewRecipesBackVideo = root.findViewById(R.id.imageViewRecipesBackVideo);
        editTextRecipesName = root.findViewById(R.id.editTextRecipesName);
        editTextRecipesHours = root.findViewById(R.id.editTextRecipesHours);
        editTextRecipesMinutes = root.findViewById(R.id.editTextRecipesMinutes);
        editTextRecipesCategory = root.findViewById(R.id.editTextRecipesCategory);
        editTextRecipesIngredientsName = root.findViewById(R.id.editTextRecipesIngredientsName);
        editTextRecipesIngredientsAmount = root.findViewById(R.id.editTextRecipesIngredientsAmount);
        editTextRecipesIngredientsUnit = root.findViewById(R.id.editTextRecipesIngredientsUnit);
        editTextRecipesSteps = root.findViewById(R.id.editTextRecipesSteps);
        buttonAddIngredients = root.findViewById(R.id.buttonAddIngredients);
        buttonCreateRecipe = root.findViewById(R.id.buttonCreateRecipe);


        //this button opens the gallery or camera so you can choose a picture from to the recipe
        textViewRecipesPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });

        //this button on click adds the the new ingredient to the list of ingredients and set new value to the texView
        buttonAddIngredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!editTextRecipesIngredientsName.getText().toString().equals("") && !editTextRecipesIngredientsAmount.getText().toString().equals("") && !editTextRecipesIngredientsUnit.getText().toString().equals("")) {
                    Ingredient ing = new Ingredient(editTextRecipesIngredientsName.getText().toString(),
                            Double.parseDouble(editTextRecipesIngredientsAmount.getText().toString()),
                            editTextRecipesIngredientsUnit.getText().toString());

                    ingredients.add(ing);
                    oneIngredient += (numOfIngredient+1) + ". " + ing.ingredientToString() + "\n";
                    textViewRecipesIngredientsList.setText(oneIngredient);
                    numOfIngredient++;
                    editTextRecipesIngredientsName.getText().clear();
                    editTextRecipesIngredientsAmount.getText().clear();
                    editTextRecipesIngredientsUnit.getText().clear();
                }
                else
                    Toast.makeText(getContext(), "Make sure all fields of the ingredients are filled", Toast.LENGTH_SHORT).show();
            }
        });


        //this button deletes the last ingredient that was added
        textViewRecipesIngredientsDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ingredients != null  && !ingredients.isEmpty()){
                    numOfIngredient = numOfIngredient - 1;
                    ingredients.remove(numOfIngredient);
                    for (int i=0 ; i<numOfIngredient ; i++){
                        updateView += (i+1) +"." + ingredients.get(i).ingredientToString() + "\n" ;
                    }
                    oneIngredient = updateView;
                    textViewRecipesIngredientsList.setText(updateView);
                    updateView = "";
                }
                else {
                    textViewRecipesIngredientsList.setText("");
                    Toast.makeText(getContext(), "Make sure you have added an ingredient to the list before you delete", Toast.LENGTH_SHORT).show();
                }
            }
        });


        //this button creates new recipe by calling the function that adds it to FB
        buttonCreateRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!editTextRecipesName.getText().toString().equals("") && !editTextRecipesName.getText().toString().equals("")
                    && (ingredients != null  && !ingredients.isEmpty()) && !editTextRecipesSteps.getText().toString().equals(""))
                    addRecipe();
                else
                    Toast.makeText(getContext(), "Make sure the recipe's fields are filled", Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }


    //this function merges the time into one string
    public String mergeTime(){
        String totalTime = "";
        if(!editTextRecipesHours.getText().toString().equals(""))
            totalTime += editTextRecipesHours.getText().toString() +":";
        else
            totalTime += "00:";

        if(!editTextRecipesMinutes.getText().toString().equals(""))
            totalTime += editTextRecipesMinutes.getText().toString();
        else
            totalTime += "00";

        return totalTime;
    }


    public void addRecipe(){
        Recipe recipe = new Recipe();

        //push() push the recipe to FB and return the new object reference to the recipe (this is was we also get.key())
        String key = reference.push().getKey();

        recipe.setTitle(editTextRecipesName.getText().toString());
        if(recipesImage != null)
            recipe.setImage(recipe.bitmapToString(recipesImage));
        recipe.setTime(mergeTime());
        recipe.setVideo(textViewRecipesVideo.getText().toString());
        recipe.setCategory(editTextRecipesCategory.getText().toString());
        recipe.setCreator(firebaseAuth.getCurrentUser().getEmail());
        recipe.setIngredients(recipe.ArrayOfIngredientsToString(ingredients));
        recipe.setSteps(editTextRecipesSteps.getText().toString());
        recipe.setKey(key);

        reference = db.getReference("Recipe/"+key);
        reference.setValue(recipe);

        Intent i = new Intent(getContext(), NavDrawerActivity.class);
        startActivity(i);
    }


    // Select image from camera and gallery
    private void selectImage() {
        try {
            PackageManager pm = getActivity().getPackageManager();
            int hasPerm = pm.checkPermission(Manifest.permission.CAMERA, getActivity().getPackageName());

            if (hasPerm == PackageManager.PERMISSION_GRANTED) {
                final CharSequence[] options = {"Take Photo", "Choose From Gallery","Cancel"};
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
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
                Toast.makeText(getContext(), "Camera Permission error", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getContext(), "Camera Permission error", Toast.LENGTH_SHORT).show();
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
                imageViewRecipesBackPicture.setImageBitmap(picture);
                recipesImage = picture;
            }
        }
        else{
            if(resultCode == RESULT_OK){
                Uri targetUri = data.getData();
                try {
                    //Decode an input stream into bitmap
                    Bitmap picture = BitmapFactory.decodeStream(getContext().getContentResolver().openInputStream(targetUri));
                    recipesImage = picture;
                    imageViewRecipesBackPicture.setImageBitmap(picture);
                    }
                catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                }
                }
            }
        }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
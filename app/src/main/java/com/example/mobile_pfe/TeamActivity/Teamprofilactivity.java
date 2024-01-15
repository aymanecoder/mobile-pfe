package com.example.mobile_pfe.TeamActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.mobile_pfe.R;
import com.example.mobile_pfe.model.TeamItem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Teamprofilactivity extends AppCompatActivity {

     private EditText teamNameEditText;
     private EditText descriptionEditText;
     private Button saveButton;
     private TextView errorTextView;
     private ImageView logoImageView;
     private Uri selectedImageUri; // Add this variable to store the selected image URI
     private String defaultImagePath; // Add this variable to store the default image path
     private Bitmap selectedBitmap; // Add this variable to store the selected bitmap
     private static final int PICK_IMAGE_REQUEST = 1;

     private boolean fromChooseTeam;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_teamprofil);

          teamNameEditText = findViewById(R.id.emailEditText);
          saveButton = findViewById(R.id.Save);
          errorTextView = findViewById(R.id.errorTextView);
          logoImageView = findViewById(R.id.logoImageView);
          descriptionEditText = findViewById(R.id.desEditText);

          fromChooseTeam = getIntent().getBooleanExtra("fromChooseTeam", false);



          saveButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                    if (!teamNameEditText.getText().toString().trim().isEmpty()) {
                         if (teamNameEditText.getText().toString().trim().length() > 3) {
                              String teamName = teamNameEditText.getText().toString().trim();
                              String description = descriptionEditText.getText().toString().trim();
                              String logoImageUrl;

                              // Check if selectedImageUri is not null
                              if (selectedImageUri != null) {
                                   selectedBitmap = getBitmapFromUri(selectedImageUri);
                                   logoImageView.setImageBitmap(selectedBitmap);

                                   // Get the real path from the URI
                                   logoImageUrl = getRealPathFromUri(selectedImageUri);

                                   // Check if the real path is not null
                                   if (logoImageUrl == null) {
                                        // Handle the case when the real path is null
                                        // This might happen due to issues in obtaining the real path
                                        // You may display an error message or handle it as needed
                                        Toast.makeText(Teamprofilactivity.this, "Failed to obtain image path", Toast.LENGTH_SHORT).show();
                                        return;
                                   }
                              } else {
                                   // User didn't change the image, use the default image path
                                   Toast.makeText(Teamprofilactivity.this, "You must select a new image for your team", Toast.LENGTH_SHORT).show();
                                   return;
                              }

                              Intent intent = new Intent(Teamprofilactivity.this, listteamactivity.class);
                              intent.putExtra("teamName", teamName);
                              intent.putExtra("logoImageUrl", logoImageUrl);
                              intent.putExtra("description", description);
                              intent.putExtra("fromChooseTeam", fromChooseTeam);
                              startActivity(intent);
                         } else {
                              // Team name is too short, display an error message
                              Toast.makeText(Teamprofilactivity.this, "Team name should be at least 4 characters long", Toast.LENGTH_SHORT).show();
                         }
                    } else {
                         // Team name is empty, display an error message
                         errorTextView.setVisibility(View.VISIBLE);
                    }
               }

          });

          logoImageView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                    if (checkStoragePermission()) {
                         openGallery();;
                    } else {
                         requestStoragePermission();
                    }
               }
          });
     }

     private void openGallery() {
          Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
          startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);
     }

     @Override
     protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
          super.onActivityResult(requestCode, resultCode, data);
          if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
               selectedImageUri = data.getData();
               // Set the selected image to the logoImageView
               logoImageView.setImageURI(selectedImageUri);
          }
     }

     private Bitmap getBitmapFromUri(Uri uri) {
          try {
               return MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
          } catch (IOException e) {
               e.printStackTrace();
               return null;
          }
     }

     private String getRealPathFromUri(Uri uri) {
          String filePath = null;
          try {
               Cursor cursor = getContentResolver().query(uri, null, null, null, null);
               if (cursor != null) {
                    cursor.moveToFirst();
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    filePath = cursor.getString(index);
                    cursor.close();
               }
          } catch (Exception e) {
               e.printStackTrace();
               filePath = null;
          }
          return filePath;
     }

     private boolean checkStoragePermission() {
          int resultRead = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE);
          int resultWrite = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
          return resultRead == PackageManager.PERMISSION_GRANTED && resultWrite == PackageManager.PERMISSION_GRANTED;
     }

     private void requestStoragePermission() {
          ActivityCompat.requestPermissions(this, new String[]{
                  android.Manifest.permission.READ_EXTERNAL_STORAGE,
                  android.Manifest.permission.WRITE_EXTERNAL_STORAGE
          }, PICK_IMAGE_REQUEST);
     }





}

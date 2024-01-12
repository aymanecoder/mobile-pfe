package com.example.mobile_pfe.TeamActivity;
import android.content.Intent;
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

     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_teamprofil);

          teamNameEditText = findViewById(R.id.emailEditText);
          saveButton = findViewById(R.id.Save);
          errorTextView = findViewById(R.id.errorTextView);
          logoImageView = findViewById(R.id.logoImageView);
          descriptionEditText = findViewById(R.id.desEditText);





          saveButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                    if (!teamNameEditText.getText().toString().trim().isEmpty()) {
                         String teamName = teamNameEditText.getText().toString().trim();
                         String description = descriptionEditText.getText().toString().trim();
                         String logoImageUrl;

                         if (selectedImageUri != null) {
                              selectedBitmap = getBitmapFromUri(selectedImageUri);
                              logoImageView.setImageBitmap(selectedBitmap);

                              // Get the real path from the URI
                              logoImageUrl = getRealPathFromUri(selectedImageUri);
                              if (logoImageUrl == null) {
                                   // Handle the case when the real path is null
                                   // This might happen due to issues in obtaining the real path
                                   // You may display an error message or handle it as needed
                                   Toast.makeText(Teamprofilactivity.this, "Failed to obtain image path", Toast.LENGTH_SHORT).show();
                                   return;
                              }// Use the dynamically generated path
                         } else {
                              // User didn't change the image, use the default image path
                              logoImageUrl = null;
                         }

                         Intent intent = new Intent(Teamprofilactivity.this, listteamactivity.class);
                         intent.putExtra("teamName", teamName);
                         intent.putExtra("logoImageUrl", logoImageUrl);
                         intent.putExtra("description", description);
                         startActivity(intent);
                    } else {
                         errorTextView.setVisibility(View.VISIBLE);
                    }
               }
          });

          logoImageView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                    openGallery();
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





}

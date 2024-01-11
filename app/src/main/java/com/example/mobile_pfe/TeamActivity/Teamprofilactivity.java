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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mobile_pfe.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Teamprofilactivity extends AppCompatActivity {

     private EditText emailEditText;
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

          emailEditText = findViewById(R.id.emailEditText);
          saveButton = findViewById(R.id.Save);
          errorTextView = findViewById(R.id.errorTextView);
          logoImageView = findViewById(R.id.logoImageView);

          // Load the default image into logoImageView
          loadDefaultImage();

          saveButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                    if (!emailEditText.getText().toString().trim().isEmpty()) {
                         String teamName = emailEditText.getText().toString().trim();
                         String logoImageUrl;

                         if (selectedImageUri != null) {
                              // User changed the image from the gallery
                              selectedBitmap = getBitmapFromUri(selectedImageUri);
                              logoImageView.setImageBitmap(selectedBitmap);
                              logoImageUrl = saveBitmapToStorage(selectedBitmap); // Use the dynamically generated path
                         } else {
                              // User didn't change the image, use the default image path
                              logoImageUrl = defaultImagePath;
                         }

                         Intent intent = new Intent(Teamprofilactivity.this, listteamactivity.class);
                         intent.putExtra("teamName", teamName);
                         intent.putExtra("logoImageUrl", logoImageUrl);
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

     private String saveBitmapToStorage(Bitmap bitmap) {
          // Save the bitmap to a specific location with a dynamically generated name
          String imageName = "selected_image_" + System.currentTimeMillis() + ".png";
          File file = new File(getFilesDir(), imageName);
          try (FileOutputStream fos = new FileOutputStream(file)) {
               bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
               return file.getAbsolutePath();
          } catch (IOException e) {
               e.printStackTrace();
               return null;
          }
     }

     private void loadDefaultImage() {
          // Load your default image into logoImageView here
          // Example:
          logoImageView.setImageResource(R.drawable.background);
          defaultImagePath = saveBitmapToStorage(getBitmapFromUri(Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.background)));
     }
}

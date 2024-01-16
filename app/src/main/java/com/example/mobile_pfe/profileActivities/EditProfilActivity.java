package com.example.mobile_pfe.profileActivities;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.mobile_pfe.UI.MainActivity;
import com.example.mobile_pfe.model.Profile;
import com.example.mobile_pfe.Network.RetrofitInstance;
import com.example.mobile_pfe.R;
import com.example.mobile_pfe.programActivity.ListCompetitionActivity;
import com.example.mobile_pfe.programActivity.ListProgramActivity;
import com.example.mobile_pfe.programActivity.UploadProgramActivity;
import com.example.mobile_pfe.sevices.ProfilService;
import com.example.mobile_pfe.sevices.ProgramService;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditProfilActivity extends AppCompatActivity {


    private static final int PICK_IMAGE_REQUEST = 1;
    private EditText firstNameEditText,lastNameEditText,emailEditText,passwordEditText,addressEditText,ageEditText,tailleEditText,poidsEditText;
    private Profile profile;
    private Uri selectedImageUri;
    private CircleImageView profilePicture;
    private ColorStateList originalTextColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TextView back = findViewById(R.id.Back);
        back.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Pressed state: Set text color to white
                        back.setTextColor(Color.WHITE);
                        break;

                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        // Released or canceled state: Restore the original text color
                        back.setTextColor(originalTextColor);

                        // Trigger onBackPressed() when the button is released
                        Intent intent = new Intent(EditProfilActivity.this, ProfilActivity.class);
                        startActivity(intent);
                        break;
                }
                return false;
            }
        });

        // Initialize your EditText fields
        firstNameEditText = findViewById(R.id.FirstNameEditText);
        lastNameEditText = findViewById(R.id.LastNameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.PasswordEditText);
        addressEditText = findViewById(R.id.address);
        ageEditText = findViewById(R.id.age);
        tailleEditText = findViewById(R.id.taille);
        poidsEditText = findViewById(R.id.poids);
        profilePicture=findViewById(R.id.profilePicture);
        // Initialize other EditText fields

        // Retrieve Profile object from Intent
        profile = (Profile) getIntent().getSerializableExtra("profile");

        // Fill EditText fields with profile data
        if (profile != null) {
            firstNameEditText.setText(profile.getFirstName());
            lastNameEditText.setText(profile.getLastName());
            emailEditText.setText(profile.getEmail());
            passwordEditText.setText("");
            addressEditText.setText(profile.getAddress());
            ageEditText.setText(profile.getAge());
            tailleEditText.setText(profile.getTaille());
            poidsEditText.setText(profile.getPoids());
            // Load image using Glide or your preferred image loading library
            Glide.with(this)
                    .load(profile.getPicturePath()) // Replace with actual image URL or resource ID
                    .placeholder(R.drawable.notfound)
                    .error(R.drawable.notfound)
                    .into(profilePicture);
            // Set other EditText fields
        }

        // Set up the Save button click listener
        Button saveButton = findViewById(R.id.loginButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update the profile using Retrofit
                updateProfile(profile);
            }
        });

        profilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkStoragePermission()) {
                    openFileChooser();
                } else {
                    requestStoragePermission();
                }
            }
        });
    }


    private void openFileChooser() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }
    // Storage Permissions
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



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PICK_IMAGE_REQUEST) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openFileChooser();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            selectedImageUri = data.getData();
            // You can display a preview of the selected image here if needed
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


    private void updateProfile(Profile profile){
        String firstName=firstNameEditText.getText().toString().trim();
        String lastName=lastNameEditText.getText().toString().trim();
        String email=emailEditText.getText().toString().trim();
        String password=passwordEditText.getText().toString().trim();
        String address=addressEditText.getText().toString().trim();
        String age=ageEditText.getText().toString().trim();
        String taille=tailleEditText.getText().toString().trim();
        String poids=poidsEditText.getText().toString().trim();
        String filePath = getRealPathFromUri(selectedImageUri);

        MultipartBody.Part filePart = null;
        if (filePath != null) {
            File file = new File(filePath);

            // Rest of your upload code remains the same...
            // Create a RequestBody instance from file
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);

            // Create a MultipartBody.Part from the RequestBody
            filePart = MultipartBody.Part.createFormData("file", file.getName(), requestBody);

            // Rest of your upload code remains the same...
        } else {
//            Toast.makeText(EditProfilActivity.this, "Could not access file", Toast.LENGTH_SHORT).show();
            filePart=null;
        }

        ProfilService profilService=RetrofitInstance.getRetrofitInstance().create(ProfilService.class);

        RequestBody firstNameRequestBody = RequestBody.create(MediaType.parse("text/plain"), firstName);
        RequestBody lastNameRequestBody = RequestBody.create(MediaType.parse("text/plain"), lastName);
        RequestBody emailRequestBody = RequestBody.create(MediaType.parse("text/plain"), email);
        RequestBody passwordRequestBody = RequestBody.create(MediaType.parse("text/plain"), password);
        RequestBody addressRequestBody = RequestBody.create(MediaType.parse("text/plain"), address);
        RequestBody ageRequestBody = RequestBody.create(MediaType.parse("text/plain"), age);
        RequestBody tailleRequestBody = RequestBody.create(MediaType.parse("text/plain"), taille);
        RequestBody poidsRequestBody = RequestBody.create(MediaType.parse("text/plain"), poids);

        // Make a call to the service for uploading the program details with the image
        Call<Profile> call = profilService.updateUserProfile(filePart, firstNameRequestBody, lastNameRequestBody, emailRequestBody, passwordRequestBody, addressRequestBody, ageRequestBody, tailleRequestBody, poidsRequestBody);

        call.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                // Handle successful upload response
                if (response.isSuccessful()) {
                    Toast.makeText(EditProfilActivity.this, "Profile details uploaded successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditProfilActivity.this, ProfilActivity.class);
                    intent.putExtra("profile",(Profile) response.body());
                    startActivity(intent);
                } else {
                    // Handle unsuccessful response
                    Toast.makeText(EditProfilActivity.this, "Failed to upload profile details", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                System.out.println("Error Trace");
                t.printStackTrace();
                // Handle failure
                Toast.makeText(EditProfilActivity.this, "Failed to upload profile details: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

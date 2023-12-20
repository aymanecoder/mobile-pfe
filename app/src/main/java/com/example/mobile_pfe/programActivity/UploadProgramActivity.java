package com.example.mobile_pfe.programActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile_pfe.Network.RetrofitInstance;
import com.example.mobile_pfe.R;
import com.example.mobile_pfe.model.Program.Program;
import com.example.mobile_pfe.sevices.ProgramService;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadProgramActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri selectedImageUri;
    private EditText titleInput;
    private EditText descriptionInput;
    private Button chooseFileButton;
    private Button submitButton;
    private ProgramService programmeService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_program);

        titleInput = findViewById(R.id.title_input);
        descriptionInput = findViewById(R.id.description_input);
        chooseFileButton = findViewById(R.id.choose_file_button);
        submitButton = findViewById(R.id.submit_button);

        ProgramService programmeService = RetrofitInstance.getRetrofitInstance().create(ProgramService.class);

        chooseFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedImageUri != null) {
                    uploadProgramDetails();
                } else {
                    Toast.makeText(UploadProgramActivity.this, "Select an image first", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void openFileChooser() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            selectedImageUri = data.getData();
            // You can display a preview of the selected image here if needed
        }
    }
    private void uploadProgramDetails() {
        String title = titleInput.getText().toString().trim();
        String description = descriptionInput.getText().toString().trim();

        // Create a file object from the selected image URI
        File file = new File(selectedImageUri.getPath());

        // Create a RequestBody instance from file
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);

        // Create a MultipartBody.Part from the RequestBody
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), requestBody);

        // Create RequestBody instances from title and description strings
        RequestBody titleBody = RequestBody.create(MediaType.parse("text/plain"), title);
        RequestBody descriptionBody = RequestBody.create(MediaType.parse("text/plain"), description);

        // Make a call to the service for uploading the program details with the image
        Call<ResponseBody> call = programmeService.createProgramme(filePart, titleBody, descriptionBody);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                // Handle successful upload response
                if (response.isSuccessful()) {
                    Toast.makeText(UploadProgramActivity.this, "Program details uploaded successfully", Toast.LENGTH_SHORT).show();
                } else {
                    // Handle unsuccessful response
                    Toast.makeText(UploadProgramActivity.this, "Failed to upload program details", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // Handle failure
                Toast.makeText(UploadProgramActivity.this, "Failed to upload program details: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}

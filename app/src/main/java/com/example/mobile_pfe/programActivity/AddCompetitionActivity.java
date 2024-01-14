package com.example.mobile_pfe.programActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.mobile_pfe.Network.RetrofitInstance;
import com.example.mobile_pfe.R;
import com.example.mobile_pfe.UI.CoachContent;
import com.example.mobile_pfe.model.Globals.AppGlobals;
import com.example.mobile_pfe.sevices.CompetitionService;
import com.example.mobile_pfe.sevices.ProgramService;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCompetitionActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri selectedImageUri;
    private EditText titleInput;
    private EditText descriptionInput;
    private EditText teamsInput;

    private TextView editTextDate;
    private Button chooseFileButton;
    private Button submitButton;
    private CompetitionService competitionSerrvice;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_competition);

        TextView back = findViewById(R.id.Back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddCompetitionActivity.this, ListCompetitionActivity.class);
                startActivity(intent);

            }
        });

        titleInput = findViewById(R.id.titleInput);
        descriptionInput = findViewById(R.id.descriptionInput);
        teamsInput = findViewById(R.id.teamsInput);
        editTextDate = findViewById(R.id.editTextDate);
        chooseFileButton = findViewById(R.id.choose_file_button);
        submitButton = findViewById(R.id.addButton);
        

        this.competitionSerrvice = RetrofitInstance.getRetrofitInstance().create(CompetitionService.class);


        chooseFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkStoragePermission()) {
                    openFileChooser();
                } else {
                    requestStoragePermission();
                }

            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedImageUri != null) {
                    uploadProgramDetails();
                } else {
                    Toast.makeText(AddCompetitionActivity.this, "Select an image first", Toast.LENGTH_SHORT).show();
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
        String nmbrTeams = teamsInput.getText().toString().trim();
        String date = editTextDate.getText().toString().trim();
        String filePath = getRealPathFromUri(selectedImageUri);
        MultipartBody.Part filePart = null;
        if (filePath != null) {
            File file = new File(filePath);

            // Rest of your upload code remains the same...
            // Create a RequestBody instance from file
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);

            // Create a MultipartBody.Part from the RequestBody
            filePart = MultipartBody.Part.createFormData("logo", file.getName(), requestBody);

            // Rest of your upload code remains the same...
        } else {
            Toast.makeText(AddCompetitionActivity.this, "Could not access file", Toast.LENGTH_SHORT).show();
        }

        // RequestBody instances from title and description strings
        RequestBody titleBody = RequestBody.create(MediaType.parse("text/plain"), title);
        RequestBody descriptionBody = RequestBody.create(MediaType.parse("text/plain"), description);
        RequestBody nmbrTeamsBody = RequestBody.create(MediaType.parse("text/plain"), nmbrTeams);
        RequestBody dateBody = RequestBody.create(MediaType.parse("text/plain"), date);
        RequestBody sportIdBody = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(AppGlobals.getSportId()));
        // Make a call to the service for uploading the program details with the image
        Call<ResponseBody> call = competitionSerrvice.createCompetition(filePart, titleBody, descriptionBody,nmbrTeamsBody,dateBody,sportIdBody);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                // Handle successful upload response
                if (response.isSuccessful()) {
                    Toast.makeText(AddCompetitionActivity.this, "Program details uploaded successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddCompetitionActivity.this, ListCompetitionActivity.class);
                    startActivity(intent);
                } else {
                    // Handle unsuccessful response
                    Toast.makeText(AddCompetitionActivity.this, "Failed to upload program details", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("Error Trace");
                t.printStackTrace();
                // Handle failure
                Toast.makeText(AddCompetitionActivity.this, "Failed to upload program details: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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
    public void showDatePicker(View view) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                        showTimePicker(selectedYear, selectedMonth, selectedDay);
                    }
                },
                year,
                month,
                day
        );

        datePickerDialog.show();
    }

    private void showTimePicker(final int year, final int month, final int day) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        displayDateTime(year, month, day, selectedHour, selectedMinute);
                    }
                },
                hour,
                minute,
                true // 24-hour format
        );

        timePickerDialog.show();
    }

    private void displayDateTime(int year, int month, int day, int hour, int minute) {
        // Format the selected date and time as needed
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        Calendar selectedDateTime = new GregorianCalendar(year, month, day, hour, minute);
        String formattedDateTime = dateFormat.format(selectedDateTime.getTime());

        editTextDate.setText(formattedDateTime);
    }

}
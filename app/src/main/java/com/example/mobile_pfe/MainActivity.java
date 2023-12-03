package com.example.mobile_pfe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.mobile_pfe.model.Equipe;
import com.example.mobile_pfe.retrofit.EquipeApi;
import com.example.mobile_pfe.retrofit.RetrofitService;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComponents();
    }

    private void initializeComponents() {
        TextInputEditText inputEditTextName = findViewById(R.id.form_textFieldName);
        TextInputEditText inputEditBranch = findViewById(R.id.form_textFieldBody);
        MaterialButton buttonSave = findViewById(R.id.form_buttonSave);

        RetrofitService retrofitService = new RetrofitService();
        EquipeApi equipeApi = retrofitService.getRetrofit().create(EquipeApi.class);

        buttonSave.setOnClickListener(view -> {
            String name = String.valueOf(inputEditTextName.getText());
            String body = String.valueOf(inputEditBranch.getText());

            Equipe employee = new Equipe();
            employee.setName(name);
            employee.setBody(body);

            equipeApi.save(employee)
                    .enqueue(new Callback<Equipe>() {
                        @Override
                        public void onResponse(Call<Equipe> call, Response<Equipe> response) {
                            Toast.makeText(MainActivity.this, "Save successful!", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Equipe> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "Save failed!!!", Toast.LENGTH_SHORT).show();
                            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
                        }
                    });
        });
    }
}
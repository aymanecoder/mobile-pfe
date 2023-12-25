package com.example.mobile_pfe.TeamActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mobile_pfe.R;
import com.example.mobile_pfe.TeamActivity.ApiService;
import com.example.mobile_pfe.TeamActivity.TeamResponse;
import com.example.mobile_pfe.TeamActivity.User;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.example.mobile_pfe.TeamActivity.TeamDetails;

public class Teamprofilactivity extends AppCompatActivity {
 /**
    private EditText emailEditText;
    private Button saveButton;
    private TextView errorTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teamprofil);

        emailEditText = findViewById(R.id.emailEditText);
        saveButton = findViewById(R.id.Save);
        errorTextView = findViewById(R.id.errorTextView);

        ArrayList<User> selectedUsers = getIntent().getParcelableArrayListExtra("selectedUsers");

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();

                if (!selectedUsers.isEmpty()) {
                    // Ici, vous pouvez récupérer les informations du premier utilisateur sélectionné
                    User user = selectedUsers.get(0);
                    // Vous pouvez utiliser les informations de l'utilisateur pour créer un objet TeamResponse
                   // TeamDetails team = new User(user.name, user.imageId);

                    // Initialisation de Retrofit
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://localhost:8080/api/v1/") // L'URL de base doit être définie ici
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    // Création de l'interface ApiService pour l'appel à l'API
                    ApiService apiService = retrofit.create(ApiService.class);

                    // Envoi des données au serveur via l'appel à l'API
                    Call<Void> call = apiService.sendDataToServer(team);
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            // Gérer la réponse du serveur ici en cas de succès
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            // Gérer les erreurs de l'appel au serveur ici
                        }
                    });
                } else {
                    // Gérer le cas où aucun utilisateur n'est sélectionné
                }
            }
        });
    }**/
}

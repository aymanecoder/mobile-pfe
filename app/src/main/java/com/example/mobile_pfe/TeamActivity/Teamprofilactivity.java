package com.example.mobile_pfe.TeamActivity;
import androidx.appcompat.app.AppCompatActivity;

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
     Call<Void> call = apiService.sendDataToServer(teamResponse);
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
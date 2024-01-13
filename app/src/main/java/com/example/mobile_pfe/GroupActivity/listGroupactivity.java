package com.example.mobile_pfe.GroupActivity;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import com.example.mobile_pfe.R;
import com.example.mobile_pfe.TeamActivity.ListAdapter2;
import com.example.mobile_pfe.TeamActivity.User;
import com.example.mobile_pfe.databinding.ActivityListviewgroupBinding;
import java.util.ArrayList;

public class listGroupactivity extends AppCompatActivity{
    ActivityListviewgroupBinding Binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Binding = ActivityListviewgroupBinding.inflate(getLayoutInflater());
        setContentView(Binding.getRoot());


        int[] imageId={R.drawable.d,R.drawable.d,R.drawable.e ,
                R.drawable.g ,R.drawable.m,R.drawable.a };
        String[] name={"Alex","Ayman Biti","Boutaib Mohamed","ZOZO YASSINE","MOMO LGHISSI","KOKO ADIL"};

        ArrayList<User> userArrayList = new  ArrayList<>();
        for(int i=0;i<6;i++)
        {
            User user = new User(name[i],imageId[i]);
            userArrayList.add(user);
        }

        ListAdapter2 listAdapter = new ListAdapter2(listGroupactivity.this,userArrayList);


        Binding.lisvieww.setAdapter(listAdapter);
        Binding.lisvieww.setClickable(true);
        Button saveButton = findViewById(R.id.Save);
        /*
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            /*    // Liste pour stocker les utilisateurs sélectionnés
                ArrayList<User> selectedUsers = new ArrayList<>();
                ArrayList<User> userList = new ArrayList<>();

// Ajoutez des utilisateurs à votre liste userList
                userList.add(new User("John Doe", R.drawable.a));
                userList.add(new User("Alice Smith", R.drawable.b));

// Ajoutez des utilisateurs sélectionnés à la liste selectedUsers
                selectedUsers.add(userList.get(0));
                selectedUsers.add(userList.get(userList.size() - 1));

// Créez une intention pour passer les utilisateurs sélectionnés à une autre activité
                Intent intent = new Intent(listteamactivity.this, Teamprofilactivity.class);

// Utilisez putParcelableArrayListExtra pour passer la liste d'utilisateurs sélectionnés
                intent.putParcelableArrayListExtra("selectedUsers", selectedUsers);
                startActivity(intent);

            }
        });*/


    }

}
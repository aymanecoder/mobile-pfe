package com.example.mobile_pfe.TeamActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import com.example.mobile_pfe.R;
import com.example.mobile_pfe.databinding.ActivityListviewteamBinding;
import java.util.ArrayList;

public class listteamactivity extends AppCompatActivity{
    ActivityListviewteamBinding Binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Binding = ActivityListviewteamBinding.inflate(getLayoutInflater());
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

        ListAdapter2 listAdapter = new ListAdapter2(listteamactivity.this,userArrayList);


        Binding.lisvieww.setAdapter(listAdapter);
        Binding.lisvieww.setClickable(true);
        Button saveButton = findViewById(R.id.Save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(listteamactivity.this, Teamprofilactivity.class);
                startActivity(intent);
            }
        });


    }

}


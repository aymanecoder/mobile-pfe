package com.example.mobile_pfe.GroupActivity;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import com.example.mobile_pfe.R;
import com.example.mobile_pfe.databinding.ActivityListviewgroupBinding;

import java.util.ArrayList;
import com.example.mobile_pfe.GroupActivity.User;

public class GroupActivity extends AppCompatActivity{
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

        ListAdapter listAdapter = new ListAdapter(GroupActivity.this, userArrayList);

       Binding.lisvieww.setAdapter(listAdapter);
       Binding.lisvieww.setClickable(true);


    }

}





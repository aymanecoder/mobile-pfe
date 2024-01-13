package com.example.mobile_pfe.GroupActivity;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobile_pfe.R;

import java.util.ArrayList;

public class ListAdapter2 extends BaseAdapter {

    private Context context;
    private ArrayList<com.example.mobile_pfe.GroupActivity.User> userList;
    private ArrayList<User> selectedUsers;

    public ListAdapter2(Context context, ArrayList<com.example.mobile_pfe.GroupActivity.User> userList) {
        this.context = context;
        this.userList = userList;
        this.selectedUsers = new ArrayList<com.example.mobile_pfe.GroupActivity.User>();
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return userList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public ArrayList<com.example.mobile_pfe.GroupActivity.User> getSelectedUsers() {
        return selectedUsers;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.listwiew_activity, parent, false);
        }

        com.example.mobile_pfe.GroupActivity.User user = userList.get(position);

        ImageView imageView = convertView.findViewById(R.id.profile_pic);
        TextView username = convertView.findViewById(R.id.personName);
        CheckBox checkBox = convertView.findViewById(R.id.personNamecheck);

        imageView.setImageResource(user.getImageId());
        username.setText(user.getFirstName()); // Utilisez la méthode getName() ou le champ approprié pour obtenir le nom de l'utilisateur

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    selectedUsers.add(user);
                } else {
                    selectedUsers.remove(user);
                }
            }
        });

        return convertView;
    }
}




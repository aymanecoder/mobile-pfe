package com.example.mobile_pfe.GroupActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobile_pfe.R;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<com.example.mobile_pfe.GroupActivity.User> UserList;

    public ListAdapter(Context context, ArrayList<com.example.mobile_pfe.GroupActivity.User> UserList) {
        this.context = context;
        this.UserList = UserList;
    }
    @Override
    public int getCount() {
        return UserList.size();
    }

    @Override
    public Object getItem(int position) {
        return UserList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.lisvieweachteam_activity, parent, false);
        }

        User user = UserList.get(position);

        ImageView imageView = convertView.findViewById(R.id.profile_pic);
        TextView username = convertView.findViewById(R.id.personName);

        // Check if imageView is not null and user.getImageId() is a valid resource
        if (imageView != null && user != null && user.getImageId() != 0) {
            imageView.setImageResource(user.getImageId());
        }

        // Check if username is not null and user.getFirstName() is not null
        if (username != null && user != null && user.getFirstName() != null) {
            username.setText(user.getFirstName());
        }

        // Implement the click listener for the "More" button here

        return convertView;
    }

}
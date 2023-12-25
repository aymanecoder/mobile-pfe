package com.example.mobile_pfe.TeamActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mobile_pfe.R;
import com.example.mobile_pfe.TeamActivity.User;
import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends BaseAdapter {

    private Context context;
    private List<User> UserList;

    public ListAdapter(Context context, List<User> UserList) {
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

        imageView.setImageResource(user.getImageId());
        username.setText(user.getFirstName());


        // Implement the click listener for the "More" button here

        return convertView;
    }
}
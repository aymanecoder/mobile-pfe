package com.example.mobile_pfe.TeamActivity;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mobile_pfe.R;
import com.example.mobile_pfe.TeamActivity.User;
import com.example.mobile_pfe.model.Sportif;

import java.util.ArrayList;
import java.util.List;
public class ListAdapter2 extends BaseAdapter {

    private Context context;
    private List<Sportif> sportifList;
    private ArrayList<Sportif> selectedSportifs;

    public ListAdapter2(Context context, List<Sportif> sportifList) {
        this.context = context;
        this.sportifList = sportifList;
        this.selectedSportifs = new ArrayList<>();
    }

    public void updateData(List<Sportif> newSportifList) {
        this.sportifList.clear();
        this.sportifList.addAll(newSportifList);
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return sportifList.size();
    }

    @Override
    public Object getItem(int position) {
        return sportifList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public ArrayList<Sportif> getSelectedSportifs() {
        return selectedSportifs;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.listwiew_activity, parent, false);
        }

        Sportif sportif = sportifList.get(position);

        ImageView imageView = convertView.findViewById(R.id.profile_pic);
        TextView username = convertView.findViewById(R.id.personName);
        CheckBox checkBox = convertView.findViewById(R.id.personNamecheck);

        // Assuming you have a method to load images from the URL, update imageId accordingly
        //imageView.setImageResource(sportif.getImageId());
        imageView.setImageResource(R.drawable.default_image);

        // Update this line to display the appropriate information for the Sportif
        username.setText(sportif.getFirstName() + " " + sportif.getLastName());

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    selectedSportifs.add(sportif);
                } else {
                    selectedSportifs.remove(sportif);
                }
            }
        });

        return convertView;
    }
}
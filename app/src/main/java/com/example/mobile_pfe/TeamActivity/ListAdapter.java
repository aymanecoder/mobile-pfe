package com.example.mobile_pfe.TeamActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mobile_pfe.Network.RetrofitInstance;
import com.example.mobile_pfe.R;
import com.example.mobile_pfe.model.Sportif;

import java.util.List;
public class ListAdapter extends BaseAdapter {

    private Context context;
    private List<Sportif> sportifList;

    public ListAdapter(Context context, List<Sportif> sportifList) {
        this.context = context;
        this.sportifList = sportifList;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.lisvieweachteam_activity, parent, false);
        }

        Sportif sportif = sportifList.get(position);

        ImageView imageView = convertView.findViewById(R.id.profile_pic);
        TextView username = convertView.findViewById(R.id.personName2);
        String pictureUrl =sportif.getPicturePath();
        // Load image using Glide
        if (pictureUrl !=null) {
            Glide.with(context)
                    .load(pictureUrl != null ? pictureUrl.replace("localhost",  RetrofitInstance.BASE_URL_IP) : "")
                    .apply(RequestOptions.circleCropTransform())
                    .into(imageView);
        }


        username.setText(sportif.getFirstName());

        // Implement the click listener for the "More" button here

        return convertView;
    }
}
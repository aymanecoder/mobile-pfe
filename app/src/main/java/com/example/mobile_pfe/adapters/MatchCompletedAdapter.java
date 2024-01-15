package com.example.mobile_pfe.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mobile_pfe.Network.RetrofitInstance;
import com.example.mobile_pfe.R;
import com.example.mobile_pfe.model.MatchItem;
import com.example.mobile_pfe.model.MatchResponse;

import java.util.List;

public class MatchCompletedAdapter extends BaseAdapter {

    private Context context;
    private List<MatchResponse> matchList;


    public interface OnMoreButtonClickListener {
        void onMoreButtonClick(MatchResponse matchItem);
    }



    private MatchCompletedAdapter.OnMoreButtonClickListener onMoreButtonClickListener;



    public MatchCompletedAdapter(Context context, List<MatchResponse> matchList) {
        this.context = context;
        this.matchList = matchList;
    }

    public void setOnMoreButtonClickListener(MatchCompletedAdapter.OnMoreButtonClickListener listener) {
        this.onMoreButtonClickListener = listener;
    }



    @Override
    public int getCount() {
        return matchList.size();
    }

    @Override
    public Object getItem(int position) {
        return matchList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_match_completed, parent, false);
        }

        MatchResponse match = matchList.get(position);

        ImageView team1Logo = convertView.findViewById(R.id.team1Logo);
        TextView team1Name = convertView.findViewById(R.id.team1Name);
        TextView team1Score = convertView.findViewById(R.id.team1Score);
        ImageView team2Logo = convertView.findViewById(R.id.team2Logo);
        TextView team2Name = convertView.findViewById(R.id.team2Name);
        TextView team2Score = convertView.findViewById(R.id.team2Score);
        Button moreButton = convertView.findViewById(R.id.moreButton);

        team1Score.setText(match.getScoreTeamA());
        team2Score.setText(match.getScoreTeamB());

        if (match.getTeams() != null && match.getTeams().size() > 0) {
            team1Name.setText(match.getTeams().get(0).getTeamName());
            team1Score.setText(match.getScoreTeamA());
        }else {
            team1Name.setText("Not affected");
        }

        if (match.getTeams() != null && match.getTeams().size() > 1) {
            team2Name.setText(match.getTeams().get(1).getTeamName());
            team2Score.setText(match.getScoreTeamB());
        }else{
            team2Name.setText("Not affected");
        }

        String pictureUrl1 = null;
        String pictureUrl2 = null;

        // Check for null values before setting picture URLs
        if (match.getTeams() != null && match.getTeams().size() > 0) {
            pictureUrl1 = match.getTeams().get(0).getLogoPath();
        }

        if (match.getTeams() != null && match.getTeams().size() > 1) {
            pictureUrl2 = match.getTeams().get(1).getLogoPath();
        }

        // Load team logos using Glide
        if (pictureUrl1 != null) {
            Glide.with(context)
                    .load(pictureUrl1.replace("localhost",  RetrofitInstance.BASE_URL_IP))
                    .apply(RequestOptions.circleCropTransform())
                    .into(team1Logo);
        }else{
            team1Logo.setImageResource(R.drawable.team1_logo);
        }

        if (pictureUrl2 != null) {
            Glide.with(context)
                    .load(pictureUrl2.replace("localhost",  RetrofitInstance.BASE_URL_IP))
                    .apply(RequestOptions.circleCropTransform())
                    .into(team2Logo);
        }else{
            team2Logo.setImageResource(R.drawable.team2_logo);
        }



        moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onMoreButtonClickListener != null) {
                    // Notify the listener when the "More" button is clicked
                    onMoreButtonClickListener.onMoreButtonClick(match);
                }
            }
        });


        // Implement the click listener for the "More" button here

        return convertView;
    }
}
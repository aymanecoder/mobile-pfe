package com.example.mobile_pfe.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_pfe.R;
import com.example.mobile_pfe.Model.Competition.Competition;

import java.util.ArrayList;

public class CompetitionAdapter extends RecyclerView.Adapter<CompetitionAdapter.CompetitionViewHolder>{


    private ArrayList<Competition> dataList;

    public CompetitionAdapter(ArrayList<Competition> dataList) {
        this.dataList = dataList;
    }

    @Override
    public CompetitionAdapter.CompetitionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_program, parent, false);
        return new CompetitionAdapter.CompetitionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CompetitionAdapter.CompetitionViewHolder holder, int position) {
        holder.txtPostTitle.setText(dataList.get(position).getTitle());
        holder.txtPostDescreption.setText(dataList.get(position).getDescreption());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class CompetitionViewHolder extends RecyclerView.ViewHolder {

        TextView txtPostTitle, txtPostDescreption;

        CompetitionViewHolder(View itemView) {
            super(itemView);
            txtPostTitle = (TextView) itemView.findViewById(R.id.post_title);
            txtPostDescreption = (TextView) itemView.findViewById(R.id.post_description);
        }
    }
}

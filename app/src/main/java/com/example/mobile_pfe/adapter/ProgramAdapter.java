package com.example.mobile_pfe.Adapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mobile_pfe.R;
import com.example.mobile_pfe.model.Program.Program;

import java.util.ArrayList;

public class ProgramAdapter extends RecyclerView.Adapter<ProgramAdapter.ProgramViewHolder> {


    private ArrayList<Program> dataList;

    public ProgramAdapter(ArrayList<Program> dataList) {


        Log.wtf("dataList", dataList + "");
        this.dataList = dataList;
    }

    public interface OnItemClickListener {
        void onItemClick(Program program);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    @Override
    public ProgramViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_program, parent, false);
        return new ProgramViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProgramViewHolder holder, int position) {

        Log.d("ProgramAdapter", "onBindViewHolder called for position: " + position);
        holder.txtPostTitle.setText(dataList.get(position).getTitle());
        holder.txtPostDescreption.setText(dataList.get(position).getDescreption());
        Glide.with(holder.itemView.getContext())
                .load(dataList.get(position).getPicturePath())
                .placeholder(R.drawable.notfound) // Placeholder image while loading
                .error(R.drawable.notfound) // Error image if it fails to load
                .into(holder.postImage);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ProgramViewHolder extends RecyclerView.ViewHolder {
        TextView txtPostTitle, txtPostDescreption;
        ImageView postImage;

        ProgramViewHolder(View itemView) {
            super(itemView);
            txtPostTitle = itemView.findViewById(R.id.post_title);
            txtPostDescreption = itemView.findViewById(R.id.post_description);
            postImage = itemView.findViewById(R.id.post_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                        listener.onItemClick(dataList.get(getAdapterPosition()));
                    }
                }
            });
        }
    }
}

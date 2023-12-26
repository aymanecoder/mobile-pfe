//package com.example.mobile_pfe.adapter;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.mobile_pfe.R;
//import com.example.mobile_pfe.model.Equipe.Equipe;
//
//import java.util.ArrayList;
//
//public class EquipeAdapter extends RecyclerView.Adapter<EquipeAdapter.EquipeViewHolder> {
//
//
//    private ArrayList<Equipe> dataList;
//
//    public EquipeAdapter(ArrayList<Equipe> dataList) {
//        this.dataList = dataList;
//    }
//
//    @Override
//    public EquipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//        View view = layoutInflater.inflate(R.layout.item_program, parent, false);
//        return new EquipeViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(EquipeViewHolder holder, int position) {
//        holder.txtPostTitle.setText(dataList.get(position).getTitle());
//        holder.txtPostDescreption.setText(dataList.get(position).getDescreption());
//    }
//
//    @Override
//    public int getItemCount() {
//        return dataList.size();
//    }
//
//    class EquipeViewHolder extends RecyclerView.ViewHolder {
//
//        TextView txtPostTitle, txtPostDescreption;
//
//        EquipeViewHolder(View itemView) {
//            super(itemView);
//            txtPostTitle = (TextView) itemView.findViewById(R.id.post_title);
//            txtPostDescreption = (TextView) itemView.findViewById(R.id.post_description);
//        }
//    }
//}
//

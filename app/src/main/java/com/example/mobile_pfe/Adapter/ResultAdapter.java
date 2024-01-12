package com.example.mobile_pfe.Adapter;


import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.ImageView;
        import android.widget.TextView;
        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;

import com.example.mobile_pfe.R;
import com.example.mobile_pfe.model.ResultModel;

import java.util.ArrayList;

public class ResultAdapter extends ArrayAdapter<ResultModel> {

    public ResultAdapter(@NonNull Context context, ArrayList<ResultModel> ResultModelArrayList) {
        super(context, 0, ResultModelArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listitemView = convertView;
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView. 
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.card_item, parent, false);
        }

        ResultModel ResultModel = getItem(position);
        TextView courseTV = listitemView.findViewById(R.id.idTVCourse);
        ImageView courseIV = listitemView.findViewById(R.id.idIVcourse);

        assert ResultModel != null;
        courseTV.setText(ResultModel.getResult_name());
        courseIV.setImageResource(ResultModel.getImgid());
        return listitemView;
    }
}

package com.example.mobile_pfe.ResultActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.example.mobile_pfe.Adapter.ResultAdapter;
import com.example.mobile_pfe.R;
import com.example.mobile_pfe.model.ResultModel;

import java.util.ArrayList;



public class ResultActivity extends AppCompatActivity {

    GridView ResultsGV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ResultsGV = findViewById(R.id.idGVcourses);
        ArrayList<ResultModel> ResultModelArrayList = new ArrayList<ResultModel>();

        ResultModelArrayList.add(new ResultModel("DSA", R.drawable.ic_launcher_foreground));
        ResultModelArrayList.add(new ResultModel("JAVA", R.drawable.ic_launcher_foreground));
        ResultModelArrayList.add(new ResultModel("C++", R.drawable.ic_launcher_foreground));
        ResultModelArrayList.add(new ResultModel("Python", R.drawable.ic_launcher_foreground));
        ResultModelArrayList.add(new ResultModel("Javascript", R.drawable.ic_launcher_foreground));
        ResultModelArrayList.add(new ResultModel("DSA", R.drawable.ic_launcher_foreground));

        ResultAdapter adapter = new ResultAdapter(this, ResultModelArrayList);
        ResultsGV.setAdapter(adapter);
    }
}

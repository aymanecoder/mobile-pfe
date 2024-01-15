package com.example.mobile_pfe.programActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.mobile_pfe.Network.RetrofitInstance;
import com.example.mobile_pfe.R;
import com.example.mobile_pfe.model.Equipe.Equipe;
import com.example.mobile_pfe.model.Equipe.EquipeList;
import com.example.mobile_pfe.sevices.EquipeService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListEquipeActivity extends AppCompatActivity {

    //private EquipeAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_equipe);



        /*Create handle for the RetrofitInstance interface*/
        EquipeService service = RetrofitInstance.getRetrofitInstance().create(EquipeService.class);

        /*Call the method with parameter in the interface to get the employee data*/
        Call<EquipeList> call = service.getAll();

        /*Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<EquipeList>() {
            @Override
            public void onResponse(Call<EquipeList> call, Response<EquipeList> response) {
                generateEquipeList(response.body().getEquipeArrayList());
            }

            @Override
            public void onFailure(Call<EquipeList> call, Throwable t) {
                Toast.makeText(ListEquipeActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generateEquipeList(ArrayList<Equipe> equipeDataList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

//        adapter = new EquipeAdapter(equipeDataList);
//
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListEquipeActivity.this);
//
//        recyclerView.setLayoutManager(layoutManager);
//
//        recyclerView.setAdapter(adapter);
    }

}
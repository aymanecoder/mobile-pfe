package com.example.mobile_pfe;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mobile_pfe.Network.GetEquipeDataService;
import com.example.mobile_pfe.Network.RetrofitInstance;
import com.example.mobile_pfe.model.EquipeList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    private final String url = "http://192.168.0.102:8081/api/v1/equipes";
    private ArrayList<String> items = new ArrayList<>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





    }

    @Override
    protected void onResume(){
        super.onResume();
        ListView mylistview = findViewById(R.id.simpleListView);

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        JSONObject embedded = jsonResponse.getJSONObject("_embedded");
                        JSONArray produitsArray = embedded.getJSONArray("equipes");
                        for (int i = 0; i < produitsArray.length(); i++) {
                            JSONObject equipe = produitsArray.getJSONObject(i);
                            String equipeName = equipe.getString("payload");

                            System.out.println("equipe Name: " + equipeName);
                            items.add(equipeName);
                        }

                        ArrayAdapter adapter = new ArrayAdapter<>(MainActivity.this, R.layout.simplelistv, items);
                        mylistview.setAdapter(adapter);

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }



                }, error -> {
                    items.add("That didn't work!");
                    ArrayAdapter adapter = new ArrayAdapter<>(MainActivity.this, R.layout.simplelistv, items);
                    mylistview.setAdapter(adapter);

                });

        queue.add(stringRequest);

        String[] stringArray = items.toArray(new String[0]);
        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.simplelistv,stringArray);
        mylistview.setAdapter(adapter);
    }

}
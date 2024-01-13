package com.example.mobile_pfe.GroupActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile_pfe.R;
import com.example.mobile_pfe.GroupActivity.ListAdapter2;
import com.example.mobile_pfe.GroupActivity.User;
import com.example.mobile_pfe.databinding.ActivityListviewgroupBinding;

import java.util.ArrayList;
import com.example.mobile_pfe.Network.RetrofitInstance;
import com.example.mobile_pfe.model.Sportif;
import com.example.mobile_pfe.sevices.SportifService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class listgroupactivity extends AppCompatActivity{
    ActivityListviewgroupBinding Binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Binding = ActivityListviewgroupBinding.inflate(getLayoutInflater());
        setContentView(Binding.getRoot());

        fetchSportifNames();
    }

    private void fetchSportifNames() {
        SportifService service = RetrofitInstance.getRetrofitInstance().create(SportifService.class);
        Call<List<Sportif>> call = service.getAllSportifs(); // Replace with your actual method to fetch sportifs

        call.enqueue(new Callback<List<Sportif>>() {
            @Override
            public void onResponse(Call<List<Sportif>> call, Response<List<Sportif>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Sportif> sportifs = response.body();
                    populateSportifNames(sportifs);
                } else {
                    Log.e("ListSportifActivity", "Response not successful");
                    Toast.makeText(listgroupactivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Sportif>> call, Throwable t) {
                Log.e("ListSportifActivity", "Network call failed", t);
                Toast.makeText(listgroupactivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void populateSportifNames(List<Sportif> sportifs) {
        ArrayList<User> userArrayList = new ArrayList<>();
        int defaultImageId = R.drawable.d; // Replace with your default image ID

        for (Sportif sportif : sportifs) {
            userArrayList.add(new User(sportif.getFirstName(), defaultImageId));
        }

        ListAdapter2 listAdapter = new ListAdapter2(listgroupactivity.this, userArrayList);
        Binding.lisvieww.setAdapter(listAdapter);
    }
}

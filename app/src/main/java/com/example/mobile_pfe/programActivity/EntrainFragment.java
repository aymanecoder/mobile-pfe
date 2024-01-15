package com.example.mobile_pfe.programActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_pfe.Adapter.ProgramAdapter;
import com.example.mobile_pfe.Network.RetrofitInstance;
import com.example.mobile_pfe.R;
import com.example.mobile_pfe.sevices.ProgramService;
import  com.example.mobile_pfe.model.Program.Program;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EntrainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EntrainFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View view;


    //Vars
    private TextView matchTextView;
    private LinearLayout buttonsLayout;

    private ProgramAdapter adapter;
    private RecyclerView recyclerView;

    public EntrainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UpcomingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EntrainFragment newInstance(String param1, String param2) {
        EntrainFragment fragment = new EntrainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_entrain, container, false);


        /*Create handle for the RetrofitInstance interface*/
        ProgramService service = RetrofitInstance.getRetrofitInstance().create(ProgramService.class);

        /*Call the method with parameter in the interface to get the employee data*/
        Call<List<Program>> call = service.getProgramsByTypeProgram("ENTRAINEMENT");

        /*Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<List<Program>>() {
            @Override
            public void onResponse(Call<List<Program>> call, Response<List<Program>> response){
                Log.wtf("reponse", response + "");
                generateEmployeeList((ArrayList<Program>) response.body());
            }

            @Override
            public void onFailure(Call<List<Program>> call, Throwable t) {
                System.out.println("get all errors");
                t.printStackTrace();
                Toast.makeText(requireContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }






    private void generateEmployeeList(ArrayList<Program> programDataList) {
        recyclerView = view.findViewById(R.id.recycler_view);

        if (programDataList == null || programDataList.isEmpty()) {
            // Handle the case where data is null or empty
            Log.e("EntrainFragment", "No data available");
            Toast.makeText(requireContext(), "No data available", Toast.LENGTH_SHORT).show();
            return;
        }

        adapter = new ProgramAdapter(programDataList);

        adapter.setOnItemClickListener(new ProgramAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Program program) {
                // Handle item click, for example, navigate to another activity with program ID
                Intent intent = new Intent(requireContext(), ProgramActivity.class);
                intent.putExtra("programId", program.getId());
                startActivity(intent);
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext());

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }


}
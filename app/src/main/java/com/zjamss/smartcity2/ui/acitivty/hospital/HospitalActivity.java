package com.zjamss.smartcity2.ui.acitivty.hospital;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.zjamss.smartcity2.databinding.ActivityHospitalBinding;
import com.zjamss.smartcity2.http.CallBackImpl;
import com.zjamss.smartcity2.http.dto.HospitalListDTO;
import com.zjamss.smartcity2.http.http;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class HospitalActivity extends AppCompatActivity {

    ActivityHospitalBinding binding;
    List<HospitalListDTO.RowsDTO> currentHospitals = new ArrayList<>();
    List<HospitalListDTO.RowsDTO> hospitals = new ArrayList<>();
    HospitalAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHospitalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        updateHospitalList();
        eventRegister();
    }

    private void eventRegister() {
        binding.back.setOnClickListener(v-> finish());

        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                currentHospitals.clear();
                for(HospitalListDTO.RowsDTO hospital : hospitals){
                    if(hospital.getHospitalName().contains(query)){
                        currentHospitals.add(hospital);
                        adapter.notifyDataSetChanged();
                    }
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    void updateHospitalList(){
        http.request.getHospitalList().enqueue(new CallBackImpl<HospitalListDTO>() {
            @Override
            public void onResponse(Call<HospitalListDTO> call, Response<HospitalListDTO> response) {
                super.onResponse(call, response);
                if(response.body().getCode() == 200){
                    binding.hospitalList.setLayoutManager(new LinearLayoutManager(HospitalActivity.this));
                    hospitals.addAll(response.body().getRows());
                    currentHospitals.addAll(response.body().getRows());
                    adapter = new HospitalAdapter(currentHospitals);
                    binding.hospitalList.setAdapter(adapter);
                }
            }
        });
    }


}
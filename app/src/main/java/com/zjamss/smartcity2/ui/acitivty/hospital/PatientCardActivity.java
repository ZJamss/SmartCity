package com.zjamss.smartcity2.ui.acitivty.hospital;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.zjamss.smartcity2.constant.Constants;
import com.zjamss.smartcity2.databinding.ActivityPatientCardBinding;
import com.zjamss.smartcity2.http.CallBackImpl;
import com.zjamss.smartcity2.http.dto.PatientCardDTO;
import com.zjamss.smartcity2.http.dto.UserInfoDTO;
import com.zjamss.smartcity2.http.http;
import com.zjamss.smartcity2.util.Utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class PatientCardActivity extends AppCompatActivity {

    ActivityPatientCardBinding binding;
    SharedPreferences sp;
    List<PatientCardDTO.RowsDTO> patients = new ArrayList<>();
    PatientCardAdapter adapter = new PatientCardAdapter(patients);
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPatientCardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sp = Utils.getSp(this);
        eventRegister();
        binding.patientCardList.setAdapter(adapter);
        binding.patientCardList.setLayoutManager(new LinearLayoutManager(this));
    }

    private void eventRegister() {
        binding.newCard.setOnClickListener(v -> {
            startActivity(new Intent(this, CreatePatientCardActivity.class));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateCards();
    }

    private void updateCards() {
        patients.clear();
        if (sp.getString(Constants.USER_INFO, null) != null) {
            UserInfoDTO.UserDTO user = new Gson().fromJson(sp.getString(Constants.USER_INFO, null), (Type) UserInfoDTO.UserDTO.class);
            patients.add(new PatientCardDTO.RowsDTO(
                    0,
                    user.getNickName(),
                    null,
                    user.getPhonenumber(),
                    user.getSex(),
                    null,
                    user.getAvatar(),
                    null,
                    user.getUserId()
            ));
            http.request.getPatientCardList(Constants.TOKEN).enqueue(new CallBackImpl<PatientCardDTO>() {
                @Override
                public void onResponse(Call<PatientCardDTO> call, Response<PatientCardDTO> response) {
                    super.onResponse(call, response);
                    if (response.body().getCode() == 200) {
                        patients.addAll(response.body().getRows());
                        adapter.notifyDataSetChanged();
                    }
                }
            });


        } else {
            Toast.makeText(this, "请登录", Toast.LENGTH_SHORT).show();
        }

    }


}
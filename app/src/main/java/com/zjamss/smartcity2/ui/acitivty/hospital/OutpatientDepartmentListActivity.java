package com.zjamss.smartcity2.ui.acitivty.hospital;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.zjamss.smartcity2.databinding.ActivityOutpatientDepartmentListBinding;
import com.zjamss.smartcity2.http.CallBackImpl;
import com.zjamss.smartcity2.http.dto.OutpatientDepartment;
import com.zjamss.smartcity2.http.http;

import retrofit2.Call;
import retrofit2.Response;

public class OutpatientDepartmentListActivity extends AppCompatActivity {

    ActivityOutpatientDepartmentListBinding binding;
    String patientName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOutpatientDepartmentListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        patientName = getIntent().getExtras().getString("patientName");


        updateOutpatientDepartmentList();
    }

    private void updateOutpatientDepartmentList() {
        http.request.getOutpatientDepartmentList().enqueue(new CallBackImpl<OutpatientDepartment>() {
            @Override
            public void onResponse(Call<OutpatientDepartment> call, Response<OutpatientDepartment> response) {
                super.onResponse(call, response);
                if(response.body().getCode() == 200){
                    binding.departmentList.setLayoutManager(new LinearLayoutManager(OutpatientDepartmentListActivity.this));
                    binding.departmentList.setAdapter(new DepartmentAdapter(response.body().getRows(),patientName));
                }
            }
        });
    }


}
package com.zjamss.smartcity2.ui.acitivty.hospital;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.zjamss.smartcity2.databinding.ActivityBookInfoBinding;
import com.zjamss.smartcity2.model.DepartmentBook;

public class BookInfoActivity extends AppCompatActivity {

    ActivityBookInfoBinding binding;
    DepartmentBook book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBookInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        book = (DepartmentBook) getIntent().getExtras().getSerializable("book");
        binding.date.setText("预约日期："+book.getDate());
        binding.department.setText("预约科室："+book.getCategoryName());
        binding.success.setOnClickListener(v->{
            startActivity(new Intent(this,HospitalActivity.class));
            finish();
        });


    }
}
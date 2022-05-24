package com.zjamss.smartcity2.ui.acitivty.hospital;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.zjamss.smartcity2.constant.Constants;
import com.zjamss.smartcity2.databinding.ActivityHospitalDetailBinding;
import com.zjamss.smartcity2.http.dto.HospitalListDTO;

public class HospitalDetailActivity extends AppCompatActivity {

    ActivityHospitalDetailBinding binding;
    HospitalListDTO.RowsDTO hospital;

    public static void actionStart(Context context, HospitalListDTO.RowsDTO hospital){
        Intent intent = new Intent(context,HospitalDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("hospital",hospital);
        intent.putExtras(bundle);
        context.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHospitalDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        hospital = (HospitalListDTO.RowsDTO) getIntent().getExtras().getSerializable("hospital");

        binding.titleBar.setTitle(hospital.getHospitalName());
        binding.brief.loadData(Html.fromHtml(hospital.getBrief(), Html.FROM_HTML_MODE_LEGACY).toString(), "text/html", "UTF-8");
        Glide.with(binding.image).load(Constants.BASE_URL+hospital.getImgUrl()).into(binding.image);

        binding.book.setOnClickListener(v->{
            startActivity(new Intent(this,PatientCardActivity.class));
        });
    }
}
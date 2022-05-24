package com.zjamss.smartcity2.ui.acitivty.hospital;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.zjamss.smartcity2.constant.Constants;
import com.zjamss.smartcity2.databinding.ActivityCreatePatientCardBinding;
import com.zjamss.smartcity2.http.CallBackImpl;
import com.zjamss.smartcity2.http.dto.BasicDTO;
import com.zjamss.smartcity2.http.http;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

public class CreatePatientCardActivity extends AppCompatActivity {

    ActivityCreatePatientCardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreatePatientCardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.commit.setOnClickListener(v->{
            JSONObject object = new JSONObject();
            try {
                object.put("name",binding.name.getText().toString());
                object.put("address",binding.address.getText().toString());
                object.put("birthday",binding.birthday.getText().toString());
                object.put("cardId",binding.cardId.getText().toString());
                object.put("sex",binding.sex.getText().toString());
                object.put("tel",binding.tel.getText().toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            http.request.createPatientCard(Constants.TOKEN, RequestBody.create(MediaType.parse("application/json"),object.toString()))
                    .enqueue(new CallBackImpl<BasicDTO>() {
                        @Override
                        public void onResponse(Call<BasicDTO> call, Response<BasicDTO> response) {
                            super.onResponse(call, response);
                            if(response.body().getCode() == 200){
                                Toast.makeText(CreatePatientCardActivity.this,"创建成功",Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                Toast.makeText(CreatePatientCardActivity.this,"创建失败",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });
    }
}
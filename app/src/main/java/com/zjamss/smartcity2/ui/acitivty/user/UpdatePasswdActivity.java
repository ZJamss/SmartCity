package com.zjamss.smartcity2.ui.acitivty.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.zjamss.smartcity2.constant.Constants;
import com.zjamss.smartcity2.databinding.ActivityUpdatePasswdBinding;
import com.zjamss.smartcity2.http.CallBackImpl;
import com.zjamss.smartcity2.http.dto.BasicDTO;
import com.zjamss.smartcity2.http.http;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

public class UpdatePasswdActivity extends AppCompatActivity {

    ActivityUpdatePasswdBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdatePasswdBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.titleBar.setTitle("修改密码");

        binding.commit.setOnClickListener(v->{
            String oldPasswd = binding.oldPasswd.getText().toString();
            String newPasswd = binding.newPasswd.getText().toString();

            JSONObject object = new JSONObject();
            try {
                object.put("oldPassword",oldPasswd);
                object.put("newPassword",newPasswd);
            } catch (JSONException e) {
                e.printStackTrace();
            }


            http.request.updateUserPasswd(Constants.TOKEN, RequestBody.create(MediaType.parse("application/json"),object.toString())).enqueue(new CallBackImpl<BasicDTO>() {
                @Override
                public void onResponse(Call<BasicDTO> call, Response<BasicDTO> response) {
                    super.onResponse(call, response);
                    if(response.body().getCode() == 200){
                        Toast.makeText(UpdatePasswdActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(UpdatePasswdActivity.this,"修改失败",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });
    }
}
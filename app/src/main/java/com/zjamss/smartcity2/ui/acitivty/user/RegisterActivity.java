package com.zjamss.smartcity2.ui.acitivty.user;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.zjamss.smartcity2.databinding.ActivityRegisterBinding;
import com.zjamss.smartcity2.http.CallBackImpl;
import com.zjamss.smartcity2.http.dto.BasicDTO;
import com.zjamss.smartcity2.http.http;
import com.zjamss.smartcity2.model.User;

import retrofit2.Call;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.register.setOnClickListener(v->{
            user.setAvatar("");
            user.setUserName(binding.username.getText().toString());
            user.setNickName(binding.nickname.getText().toString());
            user.setPassword(binding.password.getText().toString());
            user.setPhonenumber(binding.phonenumber.getText().toString());
            user.setEmail(binding.email.getText().toString());
            user.setIdCard(binding.idCard.getText().toString());
            user.setSex(binding.man.isChecked()?"男":"女");

            http.request.register(user).enqueue(new CallBackImpl<BasicDTO>() {
                @Override
                public void onResponse(Call<BasicDTO> call, Response<BasicDTO> response) {
                    super.onResponse(call, response);
                    if(response.body().getCode() == 200){
                        Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(RegisterActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });

    }

}
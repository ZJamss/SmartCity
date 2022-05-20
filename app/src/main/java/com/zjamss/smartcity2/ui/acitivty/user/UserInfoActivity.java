package com.zjamss.smartcity2.ui.acitivty.user;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.zjamss.smartcity2.R;
import com.zjamss.smartcity2.constant.Constants;
import com.zjamss.smartcity2.databinding.ActivityUserInfoBinding;
import com.zjamss.smartcity2.http.CallBackImpl;
import com.zjamss.smartcity2.http.dto.BasicDTO;
import com.zjamss.smartcity2.http.dto.UserInfoDTO;
import com.zjamss.smartcity2.http.http;
import com.zjamss.smartcity2.util.Utils;

import retrofit2.Call;
import retrofit2.Response;

public class UserInfoActivity extends AppCompatActivity {

    ActivityUserInfoBinding binding;
    SharedPreferences sp;
    UserInfoDTO.UserDTO user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sp = Utils.getSp(this);

        http.request.getUserInfo(Constants.TOKEN).enqueue(new CallBackImpl<UserInfoDTO>() {
            @Override
            public void onResponse(Call<UserInfoDTO> call, Response<UserInfoDTO> response) {
                super.onResponse(call, response);
                if (response.isSuccessful()) {
                    user = response.body().getUser();
                    updateUserInfo();
                }
            }
        });

        binding.titleBar.setTitle("个人信息");

        binding.save.setOnClickListener(v -> {
            user.setNickName(binding.nickname.getText().toString());
            user.setSex(binding.man.isChecked() ? "0" : "1");
            user.setPhonenumber(binding.phonenumber.getText().toString());
            user.setAvatar(binding.avatar.getText().toString());
            http.request.updateUserInfo(Constants.TOKEN,user).enqueue(new CallBackImpl<BasicDTO>() {
                @Override
                public void onResponse(Call<BasicDTO> call, Response<BasicDTO> response) {
                    super.onResponse(call, response);
                    if(response.isSuccessful()){
                        Toast.makeText(UserInfoActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(UserInfoActivity.this,"修改失败",Toast.LENGTH_SHORT).show();
                    }
                }
            });

        });
    }

    public void updateUserInfo() {
        binding.avatar.setText(user.getAvatar());
        binding.userId.setText(user.getUserId() + "");
        binding.username.setText(user.getUserName());
        binding.nickname.setText(user.getNickName());
        Glide.with(UserInfoActivity.this).load(R.drawable.ic_baseline_user).into(binding.image);
        if (user.getSex().equals("0")) {
            binding.man.setChecked(true);
        } else {
            binding.woman.setChecked(true);
        }
        binding.email.setText(user.getEmail());
        binding.phonenumber.setText(user.getPhonenumber());
        binding.idCard.setText(user.getIdCard());
        binding.balance.setText(user.getBalance() + "");
        binding.score.setText(user.getScore() + "");
    }
}
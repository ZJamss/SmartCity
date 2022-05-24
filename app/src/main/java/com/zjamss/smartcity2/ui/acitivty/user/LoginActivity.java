package com.zjamss.smartcity2.ui.acitivty.user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.zjamss.smartcity2.constant.Constants;
import com.zjamss.smartcity2.databinding.ActivityLoginBinding;
import com.zjamss.smartcity2.http.CallBackImpl;
import com.zjamss.smartcity2.http.dto.LoginDTO;
import com.zjamss.smartcity2.http.dto.UserInfoDTO;
import com.zjamss.smartcity2.http.http;
import com.zjamss.smartcity2.util.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        editor = Utils.getSp(this).edit();

        binding.login.setOnClickListener(v -> {

            String username = binding.username.getText().toString();
            String passwd = binding.passwd.getText().toString();

            JSONObject json = new JSONObject();
            try {
                json.put("username", username);
                json.put("password", passwd);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            RequestBody body = RequestBody.create(MediaType.parse("application/json"), json.toString());

            http.request.login(body).enqueue(new CallBackImpl<LoginDTO>() {
                @Override
                public void onResponse(Call<LoginDTO> call, Response<LoginDTO> response) {
                    super.onResponse(call, response);
                    String token = response.body().getToken();
                    if (response.body().getCode() == 200 && token != null) {
                        editor.putString(Constants.GET_TOKEN, token);
                        editor.putBoolean(Constants.IS_LOGIN, true);
                        http.request.getUserInfo(token).enqueue(new CallBackImpl<UserInfoDTO>() {
                            @Override
                            public void onResponse(Call<UserInfoDTO> call, Response<UserInfoDTO> response) {
                                super.onResponse(call, response);
                                editor.putString(Constants.USER_INFO, new Gson().toJson(response.body().getUser()));
                                editor.commit();
                                finish();
                            }
                        });
                    } else Toast.makeText(LoginActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();

                }
            });

        });
        binding.register.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));
    }
}
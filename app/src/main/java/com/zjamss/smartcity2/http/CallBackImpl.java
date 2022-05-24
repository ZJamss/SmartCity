package com.zjamss.smartcity2.http;

import android.util.Log;
import android.widget.Toast;

import com.zjamss.smartcity2.constant.Constants;
import com.zjamss.smartcity2.ui.MainActivity;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Program: SmartCity2
 * @Description: 报错显示
 * @Author: ZJamss
 * @Create: 2022-05-09 15:38
 **/
public abstract class CallBackImpl<T> implements Callback<T> {
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        Log.i(Constants.REQUEST_INFO, response.toString());
        Log.i(Constants.REQUEST_INFO, response.message());
        if (!response.isSuccessful()) {
            Toast.makeText(MainActivity.CONTEXT, response.code() + "", Toast.LENGTH_SHORT).show();
            try {
                Log.e(Constants.REQUEST_FAILED, response.errorBody().string());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(response.code() != 200){
            try {
                Log.e(Constants.REQUEST_FAILED, response.errorBody().string());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        Toast.makeText(MainActivity.CONTEXT, "发送网络请求失败", Toast.LENGTH_SHORT).show();
        Log.e(Constants.NETWORK_FAILED, t.getMessage());
    }
}

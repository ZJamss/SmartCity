package com.zjamss.smartcity2.http;

import com.google.gson.Gson;
import com.zjamss.smartcity2.SmartCityApplication;
import com.zjamss.smartcity2.constant.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Program: SmartCity2
 * @Description: 全局维护一个网络请求类
 * @Author: ZJamss
 * @Create: 2022-05-09 15:22
 **/
public class http {

    public static Request request;

    public static Retrofit retrofit;

    static {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        request = retrofit.create(Request.class);
    }

}

package com.zjamss.smartcity2;

import android.app.Application;
import android.content.Context;

/**
 * @Program: SmartCity2
 * @Description:
 * @Author: ZJamss
 * @Create: 2022-05-06 10:03
 **/
public class SmartCityApplication extends Application {

    public static Context CONTEXT;

    @Override
    public void onCreate() {
        super.onCreate();
        CONTEXT = this.getBaseContext();
    }
}

package com.zjamss.smartcity2.util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Message;

import com.zjamss.smartcity2.constant.Constants;
import com.zjamss.smartcity2.ui.acitivty.user.LoginActivity;

/**
 * @Program: SmartCity2
 * @Description:
 * @Author: ZJamss
 * @Create: 2022-05-06 10:01
 **/
public class Utils {
    public static SharedPreferences getSp(Context context){
        return context.getSharedPreferences("default_cache", Context.MODE_PRIVATE);
    }

    public static Message getMessageInstance(int what){
        Message message = new Message();
        message.what = what;
        return message;
    }

    public static boolean isLogin(Context context){
        return getSp(context).getBoolean(Constants.IS_LOGIN,false);
    }
    public static void login(Context context){
        context.startActivity(new Intent(context, LoginActivity.class));
    }
}

package com.zjamss.smartcity2.http.dto;

import com.google.gson.annotations.SerializedName;

/**
 * @Program: SmartCity2
 * @Description:
 * @Author: ZJamss
 * @Create: 2022-05-11 09:41
 **/
public class BasicDTO {

    @SerializedName("msg")
    private String msg;
    @SerializedName("code")
    private int code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}

package com.zjamss.smartcity2.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @Program: SmartCity2
 * @Description:
 * @Author: ZJamss
 * @Create: 2022-05-11 09:47
 **/
public class User implements Serializable {

    @SerializedName("avatar")
    private String avatar;
    @SerializedName("userName")
    private String userName;
    @SerializedName("nickName")
    private String nickName;
    @SerializedName("password")
    private String password;
    @SerializedName("phonenumber")
    private String phonenumber;
    @SerializedName("sex")
    private String sex;
    @SerializedName("email")
    private String email;
    @SerializedName("idCard")
    private String idCard;
    @SerializedName("balance")
    private int balance;
    @SerializedName("score")
    private int score;

    public User(){

    }
    public User(String username, String password){
        this.userName = username;
        this.password = password;
    }

    public User(String avatar, String userName, String nickName, String password, String phonenumber, String sex, String email, String idCard) {
        this.avatar = avatar;
        this.userName = userName;
        this.nickName = nickName;
        this.password = password;
        this.phonenumber = phonenumber;
        this.sex = sex;
        this.email = email;
        this.idCard = idCard;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

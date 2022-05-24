package com.zjamss.smartcity2.http.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * @Program: SmartCity2
 * @Description:
 * @Author: ZJamss
 * @Create: 2022-05-23 17:08
 **/
public class PatientCardDTO {

    @SerializedName("total")
    private int total;
    @SerializedName("code")
    private int code;
    @SerializedName("msg")
    private String msg;
    @SerializedName("rows")
    private List<RowsDTO> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<RowsDTO> getRows() {
        return rows;
    }

    public void setRows(List<RowsDTO> rows) {
        this.rows = rows;
    }

    public static class RowsDTO implements Serializable {
        @SerializedName("id")
        private int id;
        @SerializedName("name")
        private String name;
        @SerializedName("cardId")
        private String cardId;
        @SerializedName("tel")
        private String tel;
        @SerializedName("sex")
        private String sex;
        @SerializedName("birthday")
        private String birthday;
        @SerializedName("imgUrl")
        private String imgUrl;
        @SerializedName("address")
        private String address;
        @SerializedName("userId")
        private int userId;

        public RowsDTO(int id, String name, String cardId, String tel, String sex, String birthday, String imgUrl, String address, int userId) {
            this.id = id;
            this.name = name;
            this.cardId = cardId;
            this.tel = tel;
            this.sex = sex;
            this.birthday = birthday;
            this.imgUrl = imgUrl;
            this.address = address;
            this.userId = userId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCardId() {
            return cardId;
        }

        public void setCardId(String cardId) {
            this.cardId = cardId;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}

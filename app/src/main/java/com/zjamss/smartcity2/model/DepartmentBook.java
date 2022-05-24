package com.zjamss.smartcity2.model;

import java.io.Serializable;

/**
 * @Program: SmartCity2
 * @Description:
 * @Author: ZJamss
 * @Create: 2022-05-24 16:58
 **/
public class DepartmentBook implements Serializable {
    private int id;
    private String type;
    private String categoryName;
    private int money;
    private String date;
    private String patientName;

    public DepartmentBook() {
    }

    public DepartmentBook(int id, String type, String categoryName, int money, String date, String patientName) {
        this.id = id;
        this.type = type;
        this.categoryName = categoryName;
        this.money = money;
        this.date = date;
        this.patientName = patientName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
}

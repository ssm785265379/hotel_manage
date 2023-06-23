package com.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

public class User {
    String id;
    String username;
    String pwd;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    String create_date;
    int use_status;
    int id_admin;
    String position;
    Float salary;
    String idcard;
    String phone;

    public User() {
    }

    public User(String id, String username, String pwd, String create_date, int use_status, int id_admin, String position, Float salary, String idcard, String phone) {
        this.id = id;
        this.username = username;
        this.pwd = pwd;
        this.create_date = create_date;
        this.use_status = use_status;
        this.id_admin = id_admin;
        this.position = position;
        this.salary = salary;
        this.idcard = idcard;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public int getUse_status() {
        return use_status;
    }

    public void setUse_status(int use_status) {
        this.use_status = use_status;
    }

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

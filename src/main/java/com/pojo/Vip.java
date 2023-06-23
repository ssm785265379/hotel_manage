package com.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class Vip {

    private String id;
    private String vipNum;
    private float vipRate;
    private String customerName;
    private String gender;
    private String phone;
    private String idcard;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Vip() {
    }

    public String getVipNum() {
        return vipNum;
    }

    public void setVipNum(String vipNum) {
        this.vipNum = vipNum;
    }


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public float getVipRate() {
        return vipRate;
    }

    @Override
    public String toString() {
        return "Vip{" +
                "id='" + id + '\'' +
                ", vipNum='" + vipNum + '\'' +
                ", vipRate=" + vipRate +
                ", customerName='" + customerName + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", idcard='" + idcard + '\'' +
                ", createDate=" + createDate +
                '}';
    }

    public void setVipRate(float vipRate) {
        this.vipRate = vipRate;
    }
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}

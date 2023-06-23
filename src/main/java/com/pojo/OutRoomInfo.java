package com.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


public class OutRoomInfo {
    String id;
    String roomNum;
    String roomType;
    float price;
    String customerName;
    float money;
    float orderMoney;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    Date inDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    Date outDate;
    float cost;
    String phone;
    String idcard;

//    String qita;
//    float discount;
//    int days;
//    float vipRate;


    @Override
    public String toString() {
        return "OutRoomInfo{" +
                "id='" + id + '\'' +
                ", roomNum='" + roomNum + '\'' +
                ", roomType='" + roomType + '\'' +
                ", price=" + price +
                ", customerName='" + customerName + '\'' +
                ", money=" + money +
                ", orderMoney=" + orderMoney +
                ", inDate=" + inDate +
                ", outDate=" + outDate +
                ", cost=" + cost +
                ", phone='" + phone + '\'' +
                ", idcard='" + idcard + '\'' +
                '}';
    }

    public OutRoomInfo(String id, String roomNum, String roomType, float price, String customerName, float money, float orderMoney, Date inDate, Date outDate, float cost, String phone, String idcard) {
        this.id = id;
        this.roomNum = roomNum;
        this.roomType = roomType;
        this.price = price;
        this.customerName = customerName;
        this.money = money;
        this.orderMoney = orderMoney;
        this.inDate = inDate;
        this.outDate = outDate;
        this.cost = cost;
        this.phone = phone;
        this.idcard = idcard;
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

    public float getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(float orderMoney) {
        this.orderMoney = orderMoney;
    }
    public Date getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }
    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    public OutRoomInfo() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }


}

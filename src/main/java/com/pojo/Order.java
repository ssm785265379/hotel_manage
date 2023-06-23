package com.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;


public class Order {
    private String id;
    private String orderNum;
    private Float orderMoney;
    private String orderStatus;
    private String iriId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;
    private String roomNum;


    public Order() {
    }

    public Order(String id, String orderNum, Float orderMoney, String orderStatus, String iriId, Date createDate, String roomNum) {
        this.id = id;
        this.orderNum = orderNum;
        this.orderMoney = orderMoney;
        this.orderStatus = orderStatus;
        this.iriId = iriId;
        this.createDate = createDate;
        this.roomNum = roomNum;
    }

    @Override
    public String toString() {
        return "Cost{" +
                "id='" + id + '\'' +
                ", orderNum='" + orderNum + '\'' +
                ", orderMoney='" + orderMoney + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", iriId='" + iriId + '\'' +
                ", createDate=" + createDate +
                ", roomNum='" + roomNum + '\'' +
                '}';
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public Order(String id, String orderNum, Float orderMoney, String orderStatus, String iriId, Date createDate) {
        this.id = id;
        this.orderNum = orderNum;
        this.orderMoney = orderMoney;
        this.orderStatus = orderStatus;
        this.iriId = iriId;
        this.createDate = createDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Float getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(Float orderMoney) {
        this.orderMoney = orderMoney;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getIriId() {
        return iriId;
    }

    public void setIriId(String iriId) {
        this.iriId = iriId;
    }
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}

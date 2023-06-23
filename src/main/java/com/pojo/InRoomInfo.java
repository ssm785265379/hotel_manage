package com.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class InRoomInfo {

    private int id;//信息单号
//    private int status;//是否显示
//    private Long roomId;// 房间主键
//    private String vipRate;

    private String customerName;// 客人姓名
    private String gender;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;// 入住时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date esOutDate;// 入住时间

    private String idcard;
    private String phone;
    private Float money;// 押金
    private String roomNum;
    private String roomType;
    private Float roomPrice;

    public InRoomInfo(int id, String customerName, String gender, Date createDate, Date esOutDate, String idcard, String phone, Float money, String roomNum, String roomType, Float roomPrice) {
        this.id = id;
        this.customerName = customerName;
        this.gender = gender;
        this.createDate = createDate;
        this.esOutDate = esOutDate;
        this.idcard = idcard;
        this.phone = phone;
        this.money = money;
        this.roomNum = roomNum;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Float getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(Float roomPrice) {
        this.roomPrice = roomPrice;
    }




    public Date getCreateDate() {
        return createDate;
    }

    public Date getEsOutDate() {
        return esOutDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setEsOutDate(Date esOutDate) {
        this.esOutDate = esOutDate;
    }

    @Override
    public String toString() {
        return "InRoomInfo{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", gender='" + gender + '\'' +
                ", createDate=" + createDate +
                ", esOutDate=" + esOutDate +
                ", idcard='" + idcard + '\'' +
                ", phone='" + phone + '\'' +
                ", money=" + money +
                ", roomNum='" + roomNum + '\'' +
                ", roomType='" + roomType + '\'' +
                ", roomPrice='" + roomPrice + '\'' +
                '}';
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public InRoomInfo() {
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

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}

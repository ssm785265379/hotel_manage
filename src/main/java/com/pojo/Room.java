package com.pojo;

public class Room {
    private String roomId;
    private String roomNum;
    private String roomStatus;
    private String roomPrice;
    private String roomType;
    private String roomTypeId;


    public Room(String roomId, String roomNum, String roomStatus, String roomPrice, String roomType, String roomTypeId) {
        this.roomId = roomId;
        this.roomNum = roomNum;
        this.roomStatus = roomStatus;
        this.roomPrice = roomPrice;
        this.roomType = roomType;
        this.roomTypeId = roomTypeId;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId='" + roomId + '\'' +
                ", roomNum='" + roomNum + '\'' +
                ", roomStatus='" + roomStatus + '\'' +
                ", roomPrice='" + roomPrice + '\'' +
                ", roomType='" + roomType + '\'' +
                ", roomTypeId='" + roomTypeId + '\'' +
                '}';
    }

    public String getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(String roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public Room() {
    }

    public Room(String roomId, String roomNum, String roomStatus, String roomPrice, String roomType) {
        this.roomId = roomId;
        this.roomNum = roomNum;
        this.roomStatus = roomStatus;
        this.roomPrice = roomPrice;
        this.roomType = roomType;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }

    public String getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(String roomPrice) {
        this.roomPrice = roomPrice;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
}

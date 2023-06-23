
package com.service;

import com.pojo.Room;
import com.pojo.RoomType;

import java.util.List;
import java.util.Map;


public interface HouseService {
    List<Map<String, Object>> findAllRoomType(Map<String, Object> paramMap, Integer pageNum, Integer pageSize);

    List<Map<String, Object>> findRoomsInfo();

    public List<Map<String, Object>> findRoomTypeByRoomNum(String roomNum);

    boolean addRoomType(RoomType roomType);

    boolean updateRoomType(RoomType roomType);

    boolean deleteAllRoomType(List<String> roomList);

    boolean deleteRoomType(String roomType);

    List<Map<String, Object>>getAllRoom(Map<String, Object> paramMap, Integer pageNum, Integer pageSize);

    boolean addRoom(Room room);

    boolean updateRoom(String roomNum, String roomType);

    boolean deleteAllRoom(List<String> roomList);

    boolean deleteRoom(String roomType);


    boolean updateRoomStatusByRoomNum(String roomNum, String status);

    boolean updateAllRoomStatusByRoomNum(List<Map<String, Object>> roomNumList);

}

package com.service.impl;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageHelper;
import com.mapper.HouseMapper;
import com.pojo.Room;
import com.pojo.RoomType;
import com.service.HouseService;
import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class HouseServiceImpl implements HouseService {
    @Autowired
    private HouseMapper houseMapper;

    @Override
    public List<Map<String, Object>> findAllRoomType(Map<String, Object> paramMap, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return houseMapper.findAllRoomType(paramMap);
    }


    @Override
    public List<Map<String, Object>> findRoomsInfo() {
        return houseMapper.selectRoomsInfo();
    }

    @Override
    public List<Map<String, Object>> findRoomTypeByRoomNum(String roomNum) {
        return houseMapper.selectRoomTypeByRoomNum(roomNum);
    }

    @Override
    public boolean addRoomType(RoomType roomType) {
        return houseMapper.addRoomType(roomType) > 0;
    }

    @Override
    public boolean updateRoomType(RoomType roomType) {
        return houseMapper.updateRoomType(roomType) > 0;
    }

    @Transactional(readOnly = false)
    @Override
    public boolean deleteAllRoomType(List<String> roomList) {
        int flag = 0;
        for (String roomType : roomList) {
            flag += houseMapper.deleteRoomType(roomType);
        }
        return flag > 0;
    }

    @Override
    public boolean deleteRoomType(String roomType) {
        return houseMapper.deleteRoomType(roomType) > 0;
    }

    @Override
    public List<Map<String, Object>> getAllRoom(Map<String, Object> paramMap, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return houseMapper.selectAllRoom();
    }

    @Transactional(readOnly = false)
    @Override
    public boolean addRoom(Room room) {
        RoomType flag2 = houseMapper.selectRoomTypeByRoomType(room.getRoomType());
        room.setRoomTypeId(flag2.getId());
        room.setRoomPrice(flag2.getRoomPrice());
        int flag1 = houseMapper.addRoom(room);
        return flag1 > 0 && flag2 != null;
    }

    @Transactional(readOnly = false)
    @Override
    public boolean updateRoom(String roomNum, String roomType) {
        System.out.println("roomNum:" + roomNum);
        System.out.println("roomType:" + roomType);
        RoomType Type = houseMapper.selectRoomTypeByRoomType(roomType);
        int i = houseMapper.updateRoom(roomNum, Type.getId());
        return i > 0;
    }

    @Transactional(readOnly = false)
    @Override
    public boolean deleteAllRoom(List<String> roomList) {
        int flag = 0;
        for (String roomNum : roomList) {
            flag += houseMapper.deleteRoom(roomNum);
        }
        return flag > 0;
    }

    @Override
    public boolean deleteRoom(String roomType) {
        return houseMapper.deleteRoom(roomType) > 0;
    }

    @Override
    public boolean updateRoomStatusByRoomNum(String roomNum, String status) {
        return houseMapper.updateRoomStatusByRoomNum(roomNum, status) > 0;
    }

    @Transactional(readOnly = false)
    @Override
    public boolean updateAllRoomStatusByRoomNum(List<Map<String, Object>> roomNumList) {
        int flag = 0;
        for (Map<String, Object> stringObjectMap : roomNumList) {
            flag += houseMapper.updateRoomStatusByRoomNum((String.valueOf(stringObjectMap.get("roomNum"))),
                    (String.valueOf(stringObjectMap.get("status"))));
        }
        return flag == roomNumList.size();
    }
}


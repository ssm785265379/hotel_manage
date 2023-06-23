package com.service;

import com.pojo.OutRoomInfo;

import java.util.List;
import java.util.Map;

public interface OutRoomService {

    boolean addOutRoom(OutRoomInfo outRoomInfo) throws Exception;

    List<Map<String,Object>> getAllOutRoom(Map<String, Object> paramMap, Integer pageNum, Integer pageSize);

    Map<String, Object> findOutRoomInfoByRoomNum(Long roomNum);

    boolean deleteOutRoomById(String iriId);
}


package com.service;

import com.pojo.Order;
import com.pojo.InRoomInfo;

import java.util.List;
import java.util.Map;

public interface InRoomService {

    List<Map<String,Object>>  findInRoomInfo(Map<String, Object> paramMap, Integer pageNum, Integer pageSize);

    List<Map<String, Object>> findKXRoom();

    //
    boolean saveInRoomInfo(List<InRoomInfo> inRoomInfo) throws Exception;


    List<Map<String, Object>> findRoomsByStatus(String status);

//    Map<String, Object> findOutRoomInfoByRoomId(Long roomId);

    boolean outRoom(Long roomId, Long iriId, Float qita) throws Exception;

    List<Map<String, Object>> getVipByIdcard(String idcard);

    boolean deleteInRoomInfo(String roomNum);

    boolean deleteAllInRoomInfo(List<String> roomNumList);

    List<Map<String, Object>> getInRoomInfoByRoomNum(String roomNum) throws Exception;


}

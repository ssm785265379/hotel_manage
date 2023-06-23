package com.mapper;

import com.pojo.OutRoomInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface OutRoomMapper {

    int addOutRoom(OutRoomInfo outRoomInfo);

    List<Map<String,Object>> getAllOutRoom(Map<String, Object> paramMap);

    Map<String, Object> selectOutRoomInfoByRoomNum(Long roomNum);

    @Delete("delete from out_room_info where id=#{id}")
    int deleteOutRoomById(String id);

}

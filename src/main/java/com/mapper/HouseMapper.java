
package com.mapper;

import java.util.List;
import java.util.Map;

import com.pojo.Room;
import com.pojo.RoomType;
import org.apache.ibatis.annotations.*;

/**
 * Description: Date: 下午2:08:18 <br/>
 *
 * @author 丁鹏
 * @see
 */

public interface HouseMapper {

    /**
     * @param \[]
     * @author ssm
     * @description 查询所有客房信息
     */

    List<Map<String, Object>> findAllRoomType(Map<String,Object> param);

    /**
    *   @author ssm
    *   @description 根据房间类型名获取房间类型
    *   @param \[roomType]
    */

    @Select("SELECT * FROM `room_type` where room_type_name=#{0}")
    RoomType selectRoomTypeByRoomType(String roomType);

    /**
     * @param \[roomNum]
     * @author ssm
     * @description ajax
     * 通过房间号自动填充房间类型
     */
    List<Map<String, Object>> selectRoomTypeByRoomNum(@Param(value = "roomNum") String roomNum);

    /**
     * @param \[]
     * @author ssm
     * @description 获取房间,联合房间类型 的信息
     */

    @Select("SELECT rm.id AS roomId,rm.room_num,rm.room_status,rt.room_price,rt.room_type_name \r\n"
            + "FROM rooms rm INNER JOIN room_type rt ON rm.room_type_id=rt.id")
    List<Map<String, Object>> selectRoomsInfo();

    /**
    *   @author ssm
    *   @description   获取房间,联合房间类型 的信息
    *   @param \[]
    */
    List<Map<String, Object>> selectAllRoom();

    @Insert("insert into room_type values(NULL,#{roomType},#{roomPrice})")
    int addRoomType(RoomType roomType);

    @Update("update room_type set room_price=#{roomPrice} where room_type_name=#{roomType}")
    int updateRoomType(RoomType roomType);

    @Delete("delete from room_type where room_type_name=#{roomType}")
    int deleteRoomType(String roomType);

    @Insert("insert into rooms values (NULL,#{roomNum},0,#{roomTypeId})")
    int addRoom(Room room);

    int updateRoom(@Param("roomNum") String roomNum, @Param("roomType") String roomType);

    @Delete("delete from rooms where room_num=#{roomNum}")
    int deleteRoom(String roomNum);

    @Update("update rooms set room_status=#{1} where room_num=#{0}")
    int updateRoomStatusByRoomNum(String roomNum, String status);
}

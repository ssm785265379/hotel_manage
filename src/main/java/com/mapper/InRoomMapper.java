
package com.mapper;


import java.util.List;
import java.util.Map;

import com.pojo.Order;
import com.pojo.InRoomInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


public interface InRoomMapper {

    /**
     * @param \[paramMap]
     * @author ssm
     * @description 查询房间入住信息
     */
    List<Map<String,Object>> selectInRoomInfo(Map<String, Object> paramMap);

    /**
     * @param \[]
     * @author ssm
     * @description 获取空闲房间
     */

    @Select("SELECT * FROM rooms WHERE room_status='0'")
    List<Map<String, Object>> selectKXRoom();

    /**
     * @param \[info]
     * @author ssm
     * @description 添加入住信息
     */


    int insertInRoomInfo(InRoomInfo info);

    int insertInRoomInfoById(InRoomInfo info);

    /**
     * @param \[roomId, status] 0空闲1入住2打扫
     * @author ssm
     * @description 修改房间状
     */

    @Update("UPDATE `rooms` SET room_status=#{1} WHERE id=#{0}")
    int updateRoomStatusByRoomId(Long roomId, String status);

    @Update("UPDATE `rooms` SET room_status=#{1} WHERE room_num=#{0}")
    int updateRoomStatusByRoomRoomNum(Long roomNum, String status);

    /**
     * @param \[status]
     * @author ssm
     * @description 根据房间状态获取房间信息
     */

    @Select("SELECT * FROM rooms WHERE room_status=#{0}")
    List<Map<String, Object>> getRoomsByStatus(String status);

    /**
     * Description: 根据房间主键获取退房客人的信息<br/>
     *
     * @param roomId
     * @return
     * @author 丁鹏
     */
    Map<String, Object> selectOutRoomInfoByRoomId(Long roomId);

    /**
     * Description: 修改入住信息表中的状态:未退房--->已经退房<br/>
     *
     * @param iriId
     * @return
     * @author 丁鹏
     */
    @Update("UPDATE in_room_info SET out_room_status='1' WHERE id=#{0}")
    int updateIRIStatus(Long iriId);


    /**
     * @param \[iriId]
     * @author ssm
     * @description 修改订单结算状态, 可能不存在消费
     */

    @Update("UPDATE orders SET order_status='1' WHERE iri_id=#{0} and order_status=0")
    int updateOrderStatusByIriId(Long iriId);


    @Select("Select * from vip where idcard=#{idcard}")
    List<Map<String, Object>> getVipByIdcard(String idcard);

    @Delete("delete from in_room_info where room_num=#{0}")
    int deleteInRoomInfo(String roomNum);

    @Select("select * from in_room_info,rooms where in_room_info.room_num=#{roomNum} and out_room_status=0 order by create_date desc  limit 1")
    List<Map<String, Object>> getInRoomInfoByRoomNum(String roomNum);


}

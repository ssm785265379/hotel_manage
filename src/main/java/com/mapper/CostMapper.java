
package com.mapper;


import com.pojo.Order;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface CostMapper {
    List<Map<String, Object>> selectCost(Map<String, Object> paramMap);

    @Insert("insert into orders values(null,#{orderNum},#{orderMoney},null,0,#{iriId},#{createDate},#{roomNum}) ")
    int addCost(Order cost);

    @Select("select max(order_num) FROM orders ")
    String getMaxCostNum();


    @Delete("DELETE from orders where create_date=#{date} ")
    int  deleteConsume(String date);

}

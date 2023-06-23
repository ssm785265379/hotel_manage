package com.mapper;

import com.pojo.Vip;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface VipMapper {

    List<Map<String, Object>> getAllVip(Map<String,Object> param);

    @Select("select Max(vip_num) from vip")
    String selectMaxVipNum();

    @Insert("insert into vip values(null,#{vipNum},#{vipRate},#{customerName},#{gender},#{idcard},#{phone},#{createDate})")
    int addVip(Vip vip);

    @Update("update vip set vip_rate=#{vipRate} where idcard=#{idcard}")
    int updateVip(@Param("idcard") String idcard,@Param("vipRate") Float vipRate);

    @Delete("delete from vip where idcard=#{idcard}")
    int deleteVip(String idcard);
}

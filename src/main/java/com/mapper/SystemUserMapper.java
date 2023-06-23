

package com.mapper;

import java.util.List;
import java.util.Map;

import com.pojo.Position;
import com.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


public interface SystemUserMapper {

    /**
     * @param \[paramMap]
     * @author ssm
     * @description 创建系统用户, 返回
     */

    int insertSystemUser(Map<String, Object> paramMap);

    /**
     * @param \[userId, authority]
     * @author ssm
     * @description 添加用户权限
     */

    int insertAuthority(Long userId, Long authority);


    List<Map<String, Object>> selectAllSystemUser(Map<String, Object> params);

    /**
     * @param \[userId, flag]
     * @author ssm
     * @description 用户启用/关闭
     */

    @Update("UPDATE `system_user` SET use_status=#{1} WHERE id=#{0}")
    int updateSystemUserStatus(Long userId, String flag);

    @Update("update system_user set use_status=#{0} where id=#{1}")
    int updateUseStatusById(String use_status, String id);

    @Delete("delete from system_user where id=#{0}")
    int deleteUser(String id);

    List<Position> getAllPosition();


    Position getPositionById(int id);

    @Update("update system_user set pwd=#{0} where id=#{1}")
    int resetPassword(String password,int id);

    List<User> getAllSystemUser();

    @Delete("delete from user_authority where user_id=#{0}")
    int deleteAuthority(String id);

    @Update("update system_user set position_id=#{1} where username=#{0}")
    int updateUserPosition(String username, String position);

    @Select("select id from system_user where username=#{0} ")
    int selectSystemUserIdByusername(String username);


    List<Map<String, Object>> getAllPositionByParam(Map<String, Object> paramMap);

    @Insert("insert into position values(null,#{0},#{1})")
    int addPosition(String position, String salary);

    @Update("update position set salary=#{1} where position=#{0}")
    int updatePosition(String position, String salary);

    @Delete("delete from position where id=#{id}")
    int deletePositionById(String id);
//
//    int updatePosition(String position, String salary);
}

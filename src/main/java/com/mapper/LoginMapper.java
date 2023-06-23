package com.mapper;
import java.util.List;

import com.pojo.OneMenu;
import org.apache.ibatis.annotations.Select;

public interface LoginMapper {
    @Select("SELECT COUNT(*) FROM system_user where username=#{0} AND pwd=#{1} AND use_status=1")
    int login(String username, String password);

    /**
    *   @author ssm
    *   @description  查询某个用户权限
    *   @param \[username]
    */

    List<OneMenu> getAuthorityByUsername(String username);
    /**
    *   @author ssm
    *   @description   查询所有的权限
    *   @param \[]
    */
    List<OneMenu> getSQAuthority();

}

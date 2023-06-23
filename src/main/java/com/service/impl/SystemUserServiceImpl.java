
package com.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageHelper;
import com.mapper.SystemUserMapper;
import com.pojo.Position;
import com.pojo.User;
import com.service.SystemUserService;
import com.utils.MD5Tool;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class SystemUserServiceImpl implements SystemUserService {
    @Autowired
    private SystemUserMapper systemUserMapper;

    //注意设置事务
    @Transactional(readOnly = false)
    @Override
    public boolean saveSystemUser(String username, String pwd, String idcard, String phone, String position, String oneIds, String twoIds) throws Exception {
        // 1、往system_user表中添加数据，获取生成的主键值
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("username", username);
        paramMap.put("pwd", MD5Tool.md5(pwd));
        paramMap.put("idcard", idcard);
        paramMap.put("phone", phone);
        paramMap.put("position_id", position);
        Position positionById = systemUserMapper.getPositionById(Integer.parseInt(position));
        if (positionById.getPosition().contains("管理员")) {
            paramMap.put("is_admin", 1);
        } else paramMap.put("is_admin", 0);
        System.out.println("paramMap:\n" + paramMap);
        int flag1 = systemUserMapper.insertSystemUser(paramMap);


        // 2、将userId与权限id都添加到user_authority表中中
        if (flag1 <= 0)
            return false;
        Long userId = Long.parseLong(paramMap.get("userId") + "");
        String idStr = oneIds + twoIds;// "1,1,1,4,4,10,12,14"
        String[] idAttr = idStr.replaceAll("(.,)\\1+", "$1").split("\\,");
        System.out.println(idStr);
        //一级的可能存在重复
        for (String authorityId : idAttr) {
            int flag2 = systemUserMapper.insertAuthority(userId, Long.parseLong(authorityId));
            if (flag2 <= 0)
                return false;
        }
        return true;
    }

    @Transactional(readOnly = false)
    @Override
    public boolean updateSystemUser(String username, String position, String oneIds, String twoIds) {
        Integer id = systemUserMapper.selectSystemUserIdByusername(username);
        if (id == null) return false;
        boolean flag1 = systemUserMapper.updateUserPosition(username, position) > 0;
        systemUserMapper.deleteAuthority(String.valueOf(id));

        //
        String idStr = oneIds + twoIds;// "1,1,1,4,4,10,12,14"
        String[] idAttr = idStr.replaceAll("(.,)\\1+", "$1").split("\\,");
        System.out.println(idStr);
        //一级的可能存在重复
        for (String authorityId : idAttr) {
            int flag2 = systemUserMapper.insertAuthority(Long.valueOf(id), Long.parseLong(authorityId));
            if (flag2 <= 0)
                return false;
        }
        return true;
    }

    @Override
    public List<Map<String, Object>> getAllPosition(Map<String, Object> paramMap, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return systemUserMapper.getAllPositionByParam(paramMap);
    }

    @Override
    public boolean addPosition(String position, String salary) {
        return systemUserMapper.addPosition(position,salary)>0;
    }

    @Override
    public boolean updatePosition(String position, String salary) {
        return systemUserMapper.updatePosition(position,salary)>0;
    }

    @Override
    public boolean deletePositionById(String id) {
        return systemUserMapper.deletePositionById(id)>0;
    }

    @Override
    public boolean deleteAllPosition(String[] id) {
       int flag=0;
        for (String s : id) {
            flag+=systemUserMapper.deletePositionById(s);
        }
        return flag==id.length;
    }


    /**
     *   @author ssm
     *   @description 查找所有的系统用户bylimit
     *   @param \[pageNum, pageSize]
     */

//    @Override
//    public List<Map<String, Object>> findSystemUserByLimit(Integer pageNum, Integer pageSize) {
//        return systemUserMapper.selectAllSystemUser();
//    }


    /**
     * @param \[userId, flag]
     * @author ssm
     * @description 更新用户状态
     */

    @Override
    public boolean updateSystemUserStatus(Long userId, String flag) {
        return systemUserMapper.updateSystemUserStatus(userId, flag) >= 1;
    }

    @Override
    public List<Map<String, Object>> getAllSystemUser(Map<String, Object> params, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return systemUserMapper.selectAllSystemUser(params);
    }

    @Override
    public boolean updateUseStatusById(String use_status, String id) {
        return systemUserMapper.updateUseStatusById(use_status, id) > 0;
    }

    @Transactional(readOnly = true)
    @Override
    public boolean deleteUser(String id) {
        int flag1 = systemUserMapper.deleteUser(id);
        int flag2 = systemUserMapper.deleteAuthority(id);
        return flag1 > 0;
    }

    @Override
    public List<Position> getAllPosition() {
        return systemUserMapper.getAllPosition();
    }

    @Override
    public boolean resetPassword(List<String> id) throws Exception {
        int flag = 0;
        String pwd = MD5Tool.md5("123456");
        for (String s : id) {
            flag += systemUserMapper.resetPassword(pwd, Integer.parseInt(s));
        }
        return flag == id.size();
    }

    @Override
    public List<User> getAllSystemUser() {
        return systemUserMapper.getAllSystemUser();
    }


}

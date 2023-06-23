
package com.service;

import com.pojo.Position;
import com.pojo.User;

import java.util.List;
import java.util.Map;

public interface SystemUserService {

    boolean saveSystemUser(String username, String pwd, String idcard,String phone,String position,String oneIds, String twoIds) throws Exception;

//    List<Map<String, Object>> findSystemUserByLimit(Integer pageNum, Integer pageSize);

    boolean updateSystemUserStatus(Long userId, String flag);

    List<Map<String, Object>> getAllSystemUser(Map<String,Object>params,Integer pageNum, Integer pageSize);

    boolean updateUseStatusById(String use_status, String id);

    boolean deleteUser(String id);

    List<Position> getAllPosition();

    boolean resetPassword(List<String> id) throws Exception;

    List<User> getAllSystemUser();

    boolean updateSystemUser(String username, String position, String oneIds, String twoIds);

    List<Map<String, Object>> getAllPosition(Map<String, Object> paramMap, Integer pageNum, Integer pageSize);

    boolean addPosition(String position, String salary);

    boolean updatePosition(String position, String salary);

    boolean deletePositionById(String id);

    boolean deleteAllPosition(String[] id);
}

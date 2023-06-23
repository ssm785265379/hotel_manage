package com.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageHelper;
import com.mapper.HouseMapper;
import com.mapper.InRoomMapper;
import com.pojo.Order;
import com.pojo.InRoomInfo;
import com.service.InRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class InRoomServiceImpl implements InRoomService {
    @Autowired
    private InRoomMapper inRoomMapper;
    @Autowired
    private HouseMapper houseMapper;

    @Override
    public List<Map<String, Object>> findInRoomInfo(Map<String, Object> paramMap, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return inRoomMapper.selectInRoomInfo(paramMap);
    }

    @Override
    public List<Map<String, Object>> findKXRoom() {
        return inRoomMapper.selectKXRoom();
    }


    @Transactional(readOnly = false)
    @Override
    public boolean
    saveInRoomInfo(List<InRoomInfo> inRoomInfo) throws Exception {
        Date createDate = inRoomInfo.get(0).getCreateDate();
        Date esOutDate = inRoomInfo.get(0).getEsOutDate();
        int flag = esOutDate.compareTo(createDate);
        System.out.println("timeflag:" + flag);
        if (flag <= 0)
            throw new Exception("时间错误");

        int flag1 = 0;
        int id = 0;

        //根据房间号获取房间等信息.
        System.out.println("房间号:" + inRoomInfo.get(0).getRoomNum());
        List<Map<String, Object>> maps = houseMapper.selectRoomTypeByRoomNum(inRoomInfo.get(0).getRoomNum());
        System.out.println("Maps:" + maps);
        Map<String, Object> roomType = maps.get(0);
        System.out.println("roomType:" + roomType);

        //插入roomInfo,并且改变房间状态
        for (int i = 0; i < inRoomInfo.size(); i++) {
            InRoomInfo info = inRoomInfo.get(i);
            info.setRoomType((String) roomType.get("room_type_name"));
            info.setRoomPrice((Float) roomType.get("room_price"));
            System.out.println(info);
            if (i == 0) {
                flag1 += inRoomMapper.insertInRoomInfo(info);
                id = info.getId();
            } else {
                info.setId(id);
                flag1 += inRoomMapper.insertInRoomInfoById(inRoomInfo.get(i));
            }
        }
        int flag2 = inRoomMapper.updateRoomStatusByRoomRoomNum(Long.parseLong(inRoomInfo.get(0).getRoomNum()), "1");
        return flag1 >= 1 && flag2 >= 1;
    }

    @Override
    public List<Map<String, Object>> findRoomsByStatus(String status) {
        return inRoomMapper.getRoomsByStatus(status);
    }

//    @Override
//    public Map<String, Object> findOutRoomInfoByRoomId(Long roomId) {
//        return inRoomMapper.selectOutRoomInfoByRoomId(roomId);
//    }

    @Override
    @Transactional(readOnly = false)
    public boolean outRoom(Long roomId, Long iriId, Float qita) throws Exception {
        // 修改入住信息状态
        int flag1 = inRoomMapper.updateIRIStatus(iriId);
        // 修改订单状态
        int flag2 = 1;
        if (qita > 0) { //存在消费的情况,则去更新, 不存在也不会报错
            flag2 = inRoomMapper.updateOrderStatusByIriId(iriId);
        }
        // 修改房间状态
        int flag3 = inRoomMapper.updateRoomStatusByRoomId(roomId, "2");
        if (flag1 <= 0 || flag2 <= 0 || flag3 <= 0) {
            throw new Exception();
        }
        return true;
    }

    @Override
    public List<Map<String, Object>> getVipByIdcard(String idcard) {
        return inRoomMapper.getVipByIdcard(idcard);
    }

    @Transactional(readOnly = false)
    @Override
    public boolean deleteInRoomInfo(String roomNum) {

        //删除一条入住记录
        boolean flag1 = inRoomMapper.deleteInRoomInfo(roomNum) > 0;
        //更新房间为未入住
        boolean flag2 = inRoomMapper.updateRoomStatusByRoomRoomNum(Long.valueOf(roomNum), "0") > 0;
        return flag1 && flag2;
    }

    @Transactional(readOnly = false)
    @Override
    public boolean deleteAllInRoomInfo(List<String> roomNumList) {
        int flag = 0;
        int flag2 = 0;
        for (String str : roomNumList) {
            flag += inRoomMapper.deleteInRoomInfo(str);
            flag2 = inRoomMapper.updateRoomStatusByRoomRoomNum(Long.valueOf(str), "0");
        }
        return flag == roomNumList.size() && flag2 == flag;
    }

    @Override
    public List<Map<String, Object>> getInRoomInfoByRoomNum(String roomNum) throws Exception {
        List<Map<String, Object>> iriIdByRoomNum = inRoomMapper.getInRoomInfoByRoomNum(roomNum);
        if (iriIdByRoomNum == null || iriIdByRoomNum.size() == 0) {
            throw new Exception("不存在入住");
        }
        return iriIdByRoomNum;
    }


}

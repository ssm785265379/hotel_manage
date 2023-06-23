package com.service.impl;

import com.github.pagehelper.PageHelper;
import com.mapper.InRoomMapper;
import com.mapper.OutRoomMapper;
import com.pojo.OutRoomInfo;
import com.service.OutRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class OutRoomServiceImpl implements OutRoomService {
    @Autowired
    OutRoomMapper outRoomMapper;
    @Autowired
    InRoomMapper inRoomMapper;

    @Transactional(readOnly = false)
    @Override
    public boolean addOutRoom(OutRoomInfo outRoomInfo) throws Exception {
        long iriId = Long.parseLong(outRoomInfo.getId());
        long roomNum = Long.parseLong(outRoomInfo.getRoomNum());
        int flag1 = inRoomMapper.updateIRIStatus(iriId);
        // 修改订单状态
        int flag2 = 1;
        if (outRoomInfo.getOrderMoney() > 0) { //存在消费的情况,则去更新, 不存在也不会报错
            flag2 = inRoomMapper.updateOrderStatusByIriId(iriId);
        }
        // 修改房间状态
        int flag3 = inRoomMapper.updateRoomStatusByRoomRoomNum(roomNum, "2");
        //添加结账表
        int flag4 = outRoomMapper.addOutRoom(outRoomInfo);
        if (flag1 <= 0 || flag2 <= 0 || flag3 <= 0 || flag4 <= 0) {
            throw new Exception();
        }
        return true;
    }

    @Override
    public List<Map<String, Object>> getAllOutRoom(Map<String, Object> paramMap, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return outRoomMapper.getAllOutRoom(paramMap);
    }

    @Override
    public Map<String, Object> findOutRoomInfoByRoomNum(Long roomNum) {
        return outRoomMapper.selectOutRoomInfoByRoomNum(roomNum);
    }

    @Override
    public boolean deleteOutRoomById(String id) {
        return outRoomMapper.deleteOutRoomById(id)>0;
    }

}

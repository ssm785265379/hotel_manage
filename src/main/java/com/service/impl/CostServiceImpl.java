package com.service.impl;

import com.github.pagehelper.PageHelper;
import com.mapper.CostMapper;
import com.pojo.Order;
import com.service.CostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CostServiceImpl implements CostService {
    @Autowired
    CostMapper costMapper;
    @Override
    public boolean addCost(Order cost) {
        String maxNum = costMapper.getMaxCostNum();
        if (maxNum==null){
            maxNum="0";
        }
        int i = Integer.parseInt(maxNum);
        i++;
        maxNum = String.valueOf(i);
        StringBuilder sb = new StringBuilder();
        for (int j = 1; j <= 6 - maxNum.length(); j++) {
            sb.append("0");
        }
        sb.append(maxNum);
        cost.setOrderNum(maxNum);
        return costMapper.addCost(cost) > 0;
    }


    @Override
    public List<Map<String, Object>> findCost(Map<String, Object> paramMap, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return costMapper.selectCost(paramMap);
    }

    @Override
    public boolean deleteConsume(String date) {
        return costMapper.deleteConsume(date) > 0;
    }

}

package com.service.impl;

import com.github.pagehelper.PageHelper;
import com.mapper.VipMapper;
import com.pojo.Vip;
import com.service.VipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class VipServiceImpl implements VipService {

    @Autowired
    VipMapper vipMapper;

    @Override
    public List<Map<String, Object>> getAllVip(Map<String,Object>paramMap, int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return vipMapper.getAllVip(paramMap);
    }

    @Override
    public boolean addVip(Vip vip) {
        String s = vipMapper.selectMaxVipNum();
        if (s==null){
            s="0";
        }
        int i = Integer.parseInt(s);
        i++;
        String str = String.valueOf(i);
        StringBuilder sb = new StringBuilder();
        for (int j = 1; j <= 6 - str.length(); j++) {
            sb.append("0");
        }
        sb.append(str);
        vip.setVipNum(sb.toString());
        return vipMapper.addVip(vip) > 0;
    }

    @Override
    public boolean updateVip(String idcard, Float vipRate) {
        return vipMapper.updateVip(idcard,vipRate)>0;
    }

    @Override
    public boolean deleteVip(String idcard) {
        return vipMapper.deleteVip(idcard)>0;
    }

    @Transactional(readOnly = false)
    @Override
    public boolean deleteAllVip(List<String> vipList) {
        int flag=0;
        for (String s : vipList) {
            flag+=vipMapper.deleteVip(s);
        }
        return flag==vipList.size();
    }
}

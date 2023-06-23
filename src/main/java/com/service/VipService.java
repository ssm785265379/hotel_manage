package com.service;

import com.pojo.Vip;

import java.util.List;
import java.util.Map;

public interface VipService {

    List<Map<String, Object>> getAllVip(Map<String,Object>paramMap, int pageNum,int pageSize);

    boolean addVip(Vip vip);

    boolean updateVip(String idcard, Float vipRate);

    boolean deleteVip(String idcard);

    boolean deleteAllVip(List<String> vipList);
}

package com.service;

import com.pojo.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface CostService {
    List<Map<String, Object>> findCost(Map<String, Object> paramMap, Integer pageNum, Integer pageSize);

    boolean addCost(Order cost);

    boolean deleteConsume(String date);
}


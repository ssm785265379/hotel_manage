package com.controller;

import com.github.pagehelper.PageInfo;
import com.pojo.Order;
import com.service.CostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CostController {
    @Autowired
    CostService costService;


    @RequestMapping("/findCost.do")
    public @ResponseBody
    PageInfo<Map<String, Object>> findCost(
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(value = "customerName", required = false) String customerName,
            @RequestParam(value = "idcard", required = false) String idcard,
            @RequestParam(value = "phone", required = false) String phone) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("customerName", customerName);
        paramMap.put("idcard", idcard);
        paramMap.put("phone", phone);
        List<Map<String, Object>> cost = costService.findCost(paramMap, pageNum, pageSize);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(cost);
        return pageInfo;
    }
    @RequestMapping("/addCost.do")
    public @ResponseBody
    boolean addCost(@RequestBody Order cost) throws Exception {
        System.out.println("cost:" + cost);
        return costService.addCost(cost);
    }

    @RequestMapping("/deleteConsume.do")
    public @ResponseBody
    boolean addCost(String createDate) throws Exception {
        return costService.deleteConsume(createDate);
    }
}

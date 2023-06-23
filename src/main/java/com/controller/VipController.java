package com.controller;

import com.github.pagehelper.PageInfo;
import com.pojo.Room;
import com.pojo.Vip;
import com.service.HouseService;
import com.service.VipService;
import com.utils.DateTool;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class VipController {
    @Autowired
    private VipService vipService;

    @RequestMapping("/getAllVip.do")
    public @ResponseBody
    PageInfo<Map<String, Object>> getAllVip(
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(value = "customerName", required = false) String customerName,
            @RequestParam(value = "idcard", required = false) String idcard,
            @RequestParam(value = "vipNum", required = false) String vipNum) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("customerName", customerName);
        paramMap.put("idcard", idcard);
        paramMap.put("vipNum", vipNum);
        List<Map<String, Object>> vipList = vipService.getAllVip(paramMap, pageNum, pageSize);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(vipList);
        System.out.println("pageInfo:\n"+pageInfo);
        for (Map<String, Object> stringObjectMap : pageInfo.getList()) {
            Date createDate = (Date) stringObjectMap.get("createDate");
            System.out.println("createDate:\n"+createDate);
            String st = DateTool.standardTime(createDate);
            stringObjectMap.put("createDate", st);
        }
        return pageInfo;
    }

    @RequestMapping("/addVip.do")
    public @ResponseBody
    boolean addVip(@RequestBody Vip vip) {
        System.out.println("vip:"+vip);
        return vipService.addVip(vip);
    }

    @RequestMapping("/updateVip.do")
    public @ResponseBody
    boolean updateVip(@RequestParam Map<String, Object> data) {
        String idcard = (String) data.get("idcard");
        Float vipRate = Float.parseFloat((String) data.get("vipRate"));
        vipService.updateVip(idcard, vipRate);
        return false;
    }

    @RequestMapping("/deleteAllVip.do")
    public @ResponseBody
    boolean deleteAllVip(@RequestBody List<String> vipList) {
        if (vipList == null) return false;
        return vipService.deleteAllVip(vipList);
    }

    @RequestMapping("/deleteVip.do")
    public @ResponseBody
    boolean deleteVip(String idcard) {
        return vipService.deleteVip(idcard);
    }
}

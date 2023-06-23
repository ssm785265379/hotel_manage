package com.controller;

import com.github.pagehelper.PageInfo;
import com.pojo.OutRoomInfo;
import com.service.OutRoomService;
import com.utils.DateTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class OutRoomController {
    @Autowired
    private OutRoomService outRoomService;

    @RequestMapping("/addOutRoom.do")
    public @ResponseBody
    boolean outRoom(@RequestBody OutRoomInfo outRoomInfo) throws Exception {
        System.out.println("outRoomInfo:");
        System.out.println(outRoomInfo);
        return outRoomService.addOutRoom(outRoomInfo);
    }

    @RequestMapping("/getAllOutRoom.do")
    public @ResponseBody
    PageInfo<Map<String, Object>> getAllOutRoom(
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(value = "customerName", required = false) String customerName,
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "idcard", required = false) String idcard,
            @RequestParam(value = "roomNum", required = false) String roomNum) {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("customerName", customerName);
        paramMap.put("idcard", idcard);
        paramMap.put("roomNum", roomNum);
        paramMap.put("phone", phone);
        List<Map<String, Object>> cost = outRoomService.getAllOutRoom(paramMap, pageNum, pageSize);

        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(cost);
        for (Map<String, Object> stringObjectMap : pageInfo.getList()) {
            Date createDate = (Date) stringObjectMap.get("in_date");
            Date esOutDate = (Date) stringObjectMap.get("out_date");
            String st = DateTool.standardTime(createDate);
            String ed = DateTool.standardTime(esOutDate);
            stringObjectMap.put("in_date", st);
            stringObjectMap.put("out_date", ed);
        }
        return pageInfo;
    }

    @RequestMapping("/findOutRoomInfoByRoomNum.do")
    public @ResponseBody
    Map<String, Object> findOutRoomInfoByRoomId(Long roomNum) throws Exception {
        //根据房间id获取所有的入住信息。 联合vip表获取vipRate，联合房间，房间信息表，
        //想要获取vip，首先这个用户得在vip表中存在，如果不存在的话vipRate应该是null

        Map<String, Object> resultMap = outRoomService.findOutRoomInfoByRoomNum(roomNum);
        //根据退房具体时间计算入住天数
        System.out.println("resultMap:");
        System.out.println(resultMap);
        String inTime = (String) resultMap.get("create_date");
        System.out.println("入住时间:" + inTime);
        long days = DateTool.diffDays(inTime, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        resultMap.put("days", days);

        // 计算消费金额
        Float cost = 0f;
        Float roomPrice = Float.parseFloat(resultMap.get("room_price") + "");
        Float orderMoney = 0F;
        if (resultMap.get("orderMoney") != null) {//额外消费
            orderMoney = Float.parseFloat(resultMap.get("orderMoney") + "");
        }

        //1这里不应该用直接用vip， 去vip表中搜索获取vip卡号，与之对应
        //2或者直接获取viprate,让几个表的idcard相同,但是多人入住会出现多条情况,还是传入一个结账人vip姓名就行了
        //2.1如果直接采取limit1形式就行,无法知晓谁是vip
        //2.2直接采取搜索最低vip折扣

        Float rate = (Float) resultMap.get("vip_rate");
        if (rate == null) {// 非vip用户
            resultMap.put("vip_rate", 1);
            cost = roomPrice * days + orderMoney;
        } else {
            cost = roomPrice * days * rate + orderMoney;
        }
        System.out.println("roomPrice:" + roomPrice);
        System.out.println("rate:" + rate);
        System.out.println("cost:" + cost);

        resultMap.put("cost", cost);
        return resultMap;
    }

    @RequestMapping("/deleteOutRoomById.do")
    public @ResponseBody
    boolean outRoom(String id) throws Exception {
        return outRoomService.deleteOutRoomById(id);
    }
}

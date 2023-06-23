package com.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.pojo.InRoomInfo;
import com.service.HouseService;
import com.service.InRoomService;
import com.utils.DateTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class InRoomController {
    @Autowired
    private InRoomService inRoomService;
    @Autowired
    private HouseService houseService;

    /**
     * @param \[model, pageNum, pageSize, customerName, roomNum, phone]
     * @author ssm
     * @description 获取所有入住客户.房间信息查询
     */

    @RequestMapping("/getInRoomInfo.do")
    @ResponseBody
    public PageInfo<Map<String,Object>> getInRoomInfo(
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(value = "customerName", required = false) String customerName,
            @RequestParam(value = "roomNum", required = false) String roomNum,
            @RequestParam(value = "phone", required = false) String phone) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("customerName", customerName);
        paramMap.put("roomNum", roomNum);
        paramMap.put("phone", phone);
        List<Map<String,Object>> inRoomList = inRoomService.findInRoomInfo(paramMap, pageNum, pageSize);
        PageInfo<Map<String,Object>> pageInfo = new PageInfo<>(inRoomList);
        for (Map<String, Object> stringObjectMap : pageInfo.getList()) {
            Date createDate = (Date) stringObjectMap.get("create_date");
            Date esOutDate = (Date) stringObjectMap.get("esout_date");
            String st = DateTool.standardTime(createDate);
            String ed = DateTool.standardTime(esOutDate);
            stringObjectMap.put("create_date",st);
            stringObjectMap.put("esout_date",ed);
        }
        return pageInfo;
    }


//    /**
//     * @param \[pageNum, pageSize, customerName, roomNum, phone]
//     * @author ssm
//     * @description 获取入住信息vue
//     */
//
//    @RequestMapping("/getInRoomInfoByVue.do")
//    public @ResponseBody
//    List<Map<String, Object>> getInRoomInfoByVue(
//            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
//            @RequestParam(value = "pageSize", required = false, defaultValue = "2") Integer pageSize,
//            @RequestParam(value = "customerName", required = false) String customerName,
//            @RequestParam(value = "roomNum", required = false) String roomNum,
//            @RequestParam(value = "phone", required = false) String phone) throws Exception {
//        Map<String, Object> paramMap = new HashMap<String, Object>();
//        paramMap.put("customerName", customerName);
//        paramMap.put("roomNum", roomNum);
//        paramMap.put("phone", phone);
//        List<Map<String, Object>> inRoomList = inRoomService.findInRoomInfo(paramMap, pageNum, pageSize);
//        return inRoomList;
//    }


    /**
     * @param \[]
     * @author ssm
     * @description ajax获取空闲房间
     */

    @RequestMapping("/getKXRoom.do")
    public @ResponseBody
    List<Map<String, Object>> getKXRoom() {
        List<Map<String, Object>> roomList = inRoomService.findKXRoom();
        return roomList;
    }

    /**
     * @param \[]
     * @author ssm
     * @description 点击页面获取空闲房间
     */
    @RequestMapping("/checkIn.do")
    public String checkIn(Model model) {
        List<Map<String, Object>> roomList = inRoomService.findKXRoom();
        System.out.println("roomList" + roomList);
        model.addAttribute("roomList", roomList);
        return "bill/checkin.jsp";
    }

    /**
     * @param \[]
     * @author ssm
     * @description ajax通过房间号自动填充房间类型
     */
    @RequestMapping("/getRoomTypeByRoomNum.do")
    @ResponseBody
    public List<Map<String, Object>> getRoomTypeByRoomNum(
            @RequestParam(value = "roomNum", required = false) String roomNum) {
        List<Map<String, Object>> roomTypeList = houseService.findRoomTypeByRoomNum(roomNum);
        System.out.println("roomTypeList");
        System.out.println(roomTypeList);
//        model.addAttribute("roomTypeList", roomTypeList);
//        return model;
        return roomTypeList;
    }


    /**
     * @param \[inRoomInfo]
     * @author ssm
     * @description 添加入住信息
     */
    @RequestMapping("/addInRoomInfo.do")
    public @ResponseBody
    boolean addInRoomINfo(@RequestBody List<InRoomInfo> inRoomInfo) throws Exception {
        System.out.println("inRoomInfo:");
        System.out.println(inRoomInfo);
        boolean flag = inRoomService.saveInRoomInfo(inRoomInfo);
        System.out.println("结果是" + flag);
        return flag;
    }


    /**
     * @param \[status]
     * @author ssm
     * @description 根据房间状态获取房间信息
     */

    @RequestMapping("/getRoomsByStatus.do")
    public @ResponseBody
    List<Map<String, Object>> getRoomsByStatus(
            @RequestParam(value = "status", defaultValue = "1") String status) {
        return inRoomService.findRoomsByStatus(status);
    }

//    /**
//    *   @author ssm
//    *   @description 根据房间id查看用户的入住信息
//    *   @param \[roomId]
//    */
//    @RequestMapping("/findOutRoomInfoByRoomId.do")
//    public @ResponseBody
//    Map<String, Object> findOutRoomInfoByRoomId(Long roomId) throws Exception {
//        //根据房间id获取所有的入住信息。 联合vip表获取vipRate，联合房间，房间信息表，
//        //想要获取vip，首先这个用户得在vip表中存在，如果不存在的话vipRate应该是null
//
//        Map<String, Object> resultMap = inRoomService.findOutRoomInfoByRoomId(roomId);
//        //根据退房具体时间计算入住天数
//        System.out.println("resultMap:");
//        System.out.println(resultMap);
//        String inTime = (String) resultMap.get("create_date");
//        System.out.println("入住时间:" + inTime);
//        long days = DateTool.diffDays(inTime, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//        resultMap.put("days", days);
//
//        // 计算消费金额
//        Float cost = 0f;
//        Float roomPrice = (Float) resultMap.get("room_price");
//        Float orderMoney = 0F;
//        if (resultMap.get("orderMoney") != null) {//额外消费
//            orderMoney = Float.parseFloat(resultMap.get("orderMoney") + "");
//        }
//
//        //1这里不应该用直接用vip， 去vip表中搜索获取vip卡号，与之对应
//        //2或者直接获取viprate,让几个表的idcard相同,但是多人入住会出现多条情况,还是传入一个结账人vip姓名就行了
//        //2.1如果直接采取limit1形式就行,无法知晓谁是vip
//        //2.2直接采取搜索最低vip折扣
//
//        Float rate = (Float) resultMap.get("vip_rate");
//        if (rate == null) {// 非vip用户
//            cost = roomPrice * days + orderMoney;
//        } else {
//            cost = roomPrice * days * rate + orderMoney;
//        }
//        resultMap.put("cost", cost);
//        return resultMap;
//    }



    /**
     * @param \[roomId, iriId, qita]
     * @author ssm
     * @description 最终结账退房  订单设置1,房间状态设置2打扫
     */

    @RequestMapping("/outRoom.do")
    public @ResponseBody
    boolean outRoom(Long roomId, Long iriId, Float qita) throws Exception {
        return inRoomService.outRoom(roomId, iriId, qita);
    }


    @RequestMapping("/getVipByIdcard.do")
    public @ResponseBody
    List<Map<String, Object>> getVipByIdcard(String idcard) {
        return inRoomService.getVipByIdcard(idcard);
    }


    @RequestMapping("/deleteInRoomInfo.do")
    public @ResponseBody
    boolean deleteInRoomInfo(String roomNum) {
        System.out.println("roomNum:" + roomNum);
        return inRoomService.deleteInRoomInfo(roomNum);
    }

    @RequestMapping("/deleteAllInRoomInfo.do")
    public @ResponseBody
    boolean deleteAllInRoomInfo(@RequestBody  List<String> roomNumList) {
        System.out.println("roomNumList:" + roomNumList);
        return inRoomService.deleteAllInRoomInfo(roomNumList);
    }

    @RequestMapping("/getInRoomInfoByRoomNum.do")
    public @ResponseBody
    List<Map<String, Object>> getInRoomInfoByRoomNum(String roomNum) throws Exception {
        System.out.println("roomNum:" + roomNum);
        return inRoomService.getInRoomInfoByRoomNum(roomNum);
    }




}

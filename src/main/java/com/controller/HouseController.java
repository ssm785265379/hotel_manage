

package com.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.pojo.Room;
import com.pojo.RoomType;
import com.service.HouseService;
import com.utils.DateTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HouseController {

    @Autowired
    private HouseService houseService;

//    @RequestMapping("/getHouseManage.do")
//    public String getHouseManage(Model model) {
//        List<Map<String, Object>> roomTypeList = houseService.findRoomType();
//        System.out.println("log:" + roomTypeList);
//        model.addAttribute("roomTypeList", roomTypeList);
//        return "room/showRoomType.jsp";
//    }

    /**
     * @param \[]
     * @author ssm
     * @description ajax触发获取所有的房间类型
     * @description 重新改版, 不在使用ajaxx
     */
    @RequestMapping("/getAllRoomType.do")
    public @ResponseBody
    PageInfo<Map<String, Object>> getAllRoomType(
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(value = "roomType", required = false) String roomType,
            @RequestParam(value = "roomPrice", required = false) String roomPrice) {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("roomType", roomType);
        paramMap.put("roomPrice", roomPrice);
        System.out.println("param:\n"+paramMap);
        List<Map<String, Object>> roomTypeList = houseService.findAllRoomType(paramMap, pageNum, pageSize);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(roomTypeList);
        return pageInfo;
    }

    @RequestMapping("/getRoomInfo.do")
    public @ResponseBody
    List<Map<String, Object>> getRoomInfo() {
        return houseService.findRoomsInfo();
    }

    @RequestMapping("/addRoomType.do")
    public @ResponseBody
    boolean addRoomType(RoomType roomType) {
        return houseService.addRoomType(roomType);
    }

    @RequestMapping("/updateRoomType.do")
    public @ResponseBody
    boolean updateRoomType(RoomType roomType) {
        return houseService.updateRoomType(roomType);
    }

    @RequestMapping("/deleteAllRoomType.do")
    public @ResponseBody
    boolean deleteAllRoomType(@RequestBody List<String> roomList) {
        if (roomList == null) return false;
        return houseService.deleteAllRoomType(roomList);
    }

    @RequestMapping("/deleteRoomType.do")
    public @ResponseBody
    boolean deleteRoomType(String roomType) {
        System.out.println("roomType:" + roomType);
        return houseService.deleteRoomType(roomType);
    }

    @RequestMapping("/getAllRoom.do")
    public @ResponseBody
    PageInfo<Map<String, Object>> getAllRoom(
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(value = "roomNum", required = false) String roomNum,
            @RequestParam(value = "roomType", required = false) String roomType,
            @RequestParam(value = "roomPrice", required = false) String roomPrice) {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("roomNum", roomNum);
        paramMap.put("roomType", roomType);
        paramMap.put("roomPrice", roomPrice);
        List<Map<String, Object>> inRoomList = houseService.getAllRoom(paramMap, pageNum, pageSize);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(inRoomList);
        return pageInfo;
    }

    @RequestMapping("/addRoom.do")
    public @ResponseBody
    boolean addRoom(@RequestBody Room room) {
        return houseService.addRoom(room);
    }

    @RequestMapping("/updateRoom.do")
    public @ResponseBody
    boolean updateRoom(String roomNum, String roomType) {
        houseService.updateRoom(roomNum, roomType);
        return false;
    }

    @RequestMapping("/deleteAllRoom.do")
    public @ResponseBody
    boolean deleteAllRoom(@RequestBody List<String> roomList) {
        if (roomList == null) return false;
        return houseService.deleteAllRoom(roomList);
    }

    @RequestMapping("/deleteRoom.do")
    public @ResponseBody
    boolean deleteRoom(String roomNum) {
        System.out.println("roomNum:" + roomNum);
        return houseService.deleteRoom(roomNum);
    }

    @RequestMapping("/updateRoomStatusByRoomNum.do")
    public @ResponseBody
    boolean updateRoomStatusByRoomNum(String roomNum, String status) {
        return houseService.updateRoomStatusByRoomNum(roomNum, status);
    }
    @RequestMapping("/updateAllRoomStatus.do")
    public @ResponseBody
    boolean updateRoomStatusByRoomNum(@RequestBody List<Map<String,Object>> roomNumList) {
        System.out.println("接受到了");
        System.out.println(roomNumList);
        return houseService.updateAllRoomStatusByRoomNum(roomNumList);
    }


}

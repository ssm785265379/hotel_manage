
package com.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.pojo.OneMenu;
import com.pojo.Position;
import com.pojo.User;
import com.service.LoginService;
import com.service.SystemUserService;
import com.utils.DateTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class SystemUserController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private SystemUserService systemUserService;

    @RequestMapping("/toAddUser.do")
    public String toAddUser(Model model) {
        List<OneMenu> sqAuthorityList = loginService.getSQAuthority();
        List<Position> positionList = systemUserService.getAllPosition();
        model.addAttribute("sqAuthorityList", sqAuthorityList);
        model.addAttribute("positionList", positionList);
        System.out.println("positionList:" + positionList);
        return "user/addSystemUser.jsp";
    }

    @RequestMapping("/toChangeUser.do")
    public String toChangeUser(Model model) {
        List<OneMenu> sqAuthorityList = loginService.getSQAuthority();
        List<Position> positionList = systemUserService.getAllPosition();
        List<User> userList = systemUserService.getAllSystemUser();
        model.addAttribute("sqAuthorityList", sqAuthorityList);
        model.addAttribute("positionList", positionList);
        model.addAttribute("userList", positionList);
        System.out.println("positionList:" + positionList);
        return "user/changeSystemUser.jsp";
    }


    /**
     * @param \[username, pwd, oneIds, twoIds]
     * @author ssm
     * @description 添加用户, 并保存权限
     */
    @RequestMapping("/addSystemUser.do")
    public @ResponseBody
    boolean addSystemUser(String username, String pwd, String idcard, String phone, String position, String oneIds, String twoIds)
            throws Exception {
        System.out.println("info:");
        System.out.println(" " + username + " " + pwd + " " + idcard + " " + phone + " " + position + " " + oneIds + " " + twoIds);
        return systemUserService.saveSystemUser(username, pwd, idcard, phone, position, oneIds, twoIds);
    }


    @RequestMapping("/changeSystemUser.do")
    public @ResponseBody
    boolean changeSystemUser(String username, String position, String oneIds, String twoIds) {
        return systemUserService.updateSystemUser(username, position, oneIds, twoIds);
    }
    /**
     * @param \[model, pageNum, pageSize]
     * @author ssm
     * @description 分页获取所有系统用户
     */

//    @RequestMapping("/getSystemUserByLimit.do")
//    public String getSystemUserByLimit(Model model,
//                                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
//                                       @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
//        List<Map<String, Object>> systemUserList = systemUserService.findSystemUserByLimit(pageNum, pageSize);
//        model.addAttribute("systemUserList", systemUserList);
//        return "user/showSystemUser.jsp";
//    }

    /**
     * Description: 修改用户状态<br/>
     *
     * @param flag
     * @return
     * @author 丁鹏
     */
    @RequestMapping("/updateSystemUserStatus.do")
    public String updateSystemUserStatus(Long userId, String flag) {
        // 校验flag=0/1
        // 调用业务层
        boolean result = systemUserService.updateSystemUserStatus(userId, flag);
        return "redirect:/getSystemUserByLimit.do";
    }


    @RequestMapping("/getAllSystemUser.do")
    public @ResponseBody
    PageInfo<Map<String, Object>> getAllSystemUser(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "position", required = false) String position,
            @RequestParam(value = "salary", required = false) Float salary) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("username", username);
        paramMap.put("position", position);
        paramMap.put("salary", salary);
        List<Map<String, Object>> systemUserList = systemUserService.getAllSystemUser(paramMap, pageNum, pageSize);
        System.out.println("params:" + systemUserList);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(systemUserList);
        for (Map<String, Object> stringObjectMap : pageInfo.getList()) {
            Date createDate = (Date) stringObjectMap.get("create_date");
            String st = DateTool.standardTime(createDate);
            stringObjectMap.put("create_date", st);
        }
        return pageInfo;
    }

    @RequestMapping("/updateUseStatusById.do")
    public @ResponseBody
    boolean updateUseStatusById(String use_status, String id) {
        System.out.println("use_status:" + use_status);
        System.out.println("id:" + id);
        boolean flag = systemUserService.updateUseStatusById(use_status, id);
        return flag;
    }

    @RequestMapping("/deleteUser.do")
    public @ResponseBody
    boolean deleteUser(String id) throws Exception {
        System.out.println("id:" + id);
        return systemUserService.deleteUser(id);

    }

    @RequestMapping("/resetPassword.do")
    public @ResponseBody
    boolean resetPassword(@RequestBody List<String> id) throws Exception {
        System.out.println("id:" + id);
        return systemUserService.resetPassword(id);
    }

    @RequestMapping("/getAllPosition.do")
    public @ResponseBody
    PageInfo<Map<String, Object>> getAllPosition(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "position", required = false) String position,
            @RequestParam(value = "salary", required = false) Float salary
    ) throws Exception {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("position", position);
        paramMap.put("salary", salary);
        List<Map<String, Object>> systemUserList = systemUserService.getAllPosition(paramMap, pageNum, pageSize);
        System.out.println("params:" + systemUserList);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(systemUserList);
        return pageInfo;
    }

    @RequestMapping("/addPosition.do")
    public @ResponseBody
    boolean addPosition(String position, String salary) throws Exception {
        System.out.println("position:" + position);
        System.out.println("salary:" + salary);
        return systemUserService.addPosition(position, salary);
    }

    @RequestMapping("/updatePosition.do")
    public @ResponseBody
    boolean updatePosition(String position, String salary) throws Exception {
        System.out.println("position:" + position);
        System.out.println("salary:" + salary);
        return systemUserService.updatePosition(position, salary);
    }

    @RequestMapping("/deletePosition.do")
    public @ResponseBody
    boolean deletePosition(String id) throws Exception {
        System.out.println("id:" + id);
        return systemUserService.deletePositionById(id);
    }
    @RequestMapping("/deleteAllPosition.do")
    public @ResponseBody
    boolean deleteAllPosition(@RequestBody String[] id) throws Exception {
        System.out.println("ids:" + id);
        return systemUserService.deleteAllPosition(id);
    }

}

package com.controller;

import javax.servlet.http.HttpSession;

import com.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;


    @RequestMapping("/login.do")
    public String login(String username, String pwd, HttpSession session) throws Exception {
        boolean flag = loginService.login(username, pwd, session);
        System.out.println(flag);
        return flag ? "index.jsp" : "login.jsp";
    }

}

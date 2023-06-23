


package com.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.mapper.LoginMapper;
import com.pojo.OneMenu;
import com.service.LoginService;
import com.utils.MD5Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;

    @Transactional(readOnly = false)
    public boolean login(String username, String pwd, HttpSession session) throws Exception {
        // 对明文密码进行加密后在调用mapper层
        pwd = MD5Tool.md5(pwd);
        System.out.println(pwd);
        int flag = loginMapper.login(username, pwd);
        if (flag >= 1) {
            List<OneMenu> oneMenuList = loginMapper.getAuthorityByUsername(username);
            session.setAttribute("oneMenuList", oneMenuList);
            session.setAttribute("username", username);
            return true;
        }
        return false;
    }

    public List<OneMenu> getSQAuthority() {
        return loginMapper.getSQAuthority();
    }
}

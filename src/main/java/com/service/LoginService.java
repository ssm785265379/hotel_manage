package com.service;

import com.pojo.OneMenu;

import java.util.List;
import javax.servlet.http.HttpSession;


public interface LoginService {

    boolean login(String username, String pwd, HttpSession session) throws Exception;

    List<OneMenu> getSQAuthority();

}

package cn.edu.lingnan.projectmanagment.controller;

import cn.edu.lingnan.projectmanagment.bean.MyUserDetails;
import cn.edu.lingnan.projectmanagment.utils.UserUtil;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 20:20 2020/5/14
 */
@Controller
public class indexController {
    @GetMapping(value = {"/index", "/"})
    public String index(HttpServletRequest request) {
        //获取myUserDetails对象
        MyUserDetails myUserDetails = UserUtil.getMyUserDetailsBySecurity(request);
        Integer userId = null;
        String username = null;
        String email = null;
        String photo = null;
        if (myUserDetails != null) {
            userId = myUserDetails.getId();
            username = myUserDetails.getUsername();
            email = myUserDetails.getEmail();
            photo = myUserDetails.getPhoto();
        }
        HttpSession session = request.getSession();
        session.setAttribute("userId", userId);
        session.setAttribute("username", username);
        session.setAttribute("email", email);
        session.setAttribute("photo", photo);
        return "index";
    }
}

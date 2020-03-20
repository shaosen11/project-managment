package cn.edu.lingnan.projectmanagment.controller;

import cn.edu.lingnan.projectmanagment.bean.MyUserDetails;
import cn.edu.lingnan.projectmanagment.bean.User;
import cn.edu.lingnan.projectmanagment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @ResponseBody
    @GetMapping(value = "/user")
    public boolean User(String email){
        MyUserDetails myUserDetails =  userService.checkEmail(email);
        if (myUserDetails == null){
            return true;//表示可以注册
        } else {
            return false;//表示不可以注册
        }
    }

    @PostMapping(value = "/user")
    public String addUser(MyUserDetails myUserDetails){
        System.out.println(myUserDetails);
        String password = passwordEncoder.encode(myUserDetails.getPassword());
        System.out.println(password);
        myUserDetails.setPassword(password);
        System.out.println(myUserDetails);
        userService.addUser(myUserDetails);
        return "redirect:/login.html";
    }

}

package cn.edu.lingnan.projectmanagment.controller;

import cn.edu.lingnan.projectmanagment.bean.User;
import cn.edu.lingnan.projectmanagment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @ResponseBody
    @GetMapping(value = "/user")
    public boolean addUser(@PathVariable("username") String username){
        System.out.println(username);
        return userService.ckeckUsername(username);
    }

}

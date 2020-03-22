package cn.edu.lingnan.projectmanagment.controller;

import cn.edu.lingnan.projectmanagment.bean.MyUserDetails;
import cn.edu.lingnan.projectmanagment.service.UserService;
import cn.edu.lingnan.projectmanagment.service.impl.MyUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

/**
 * @author shaosen
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MyUserDetailsServiceImpl myUserDetailsService;


    @ResponseBody
    @GetMapping("/user")
    public boolean User(String email){
        MyUserDetails myUserDetails =  userService.checkEmail(email);
        if (myUserDetails == null){
            //表示可以注册
            return true;
        } else {
            //表示不可以注册
            return false;
        }
    }

    @PostMapping("/user")
    public String addUser(MyUserDetails myUserDetails){
        String password = passwordEncoder.encode(myUserDetails.getPassword());
        myUserDetails.setPassword(password);
        userService.addUser(myUserDetails);
        return "redirect:/login.html";
    }

    @PutMapping("/user")
    public String updateUser(MyUserDetails myUserDetails, HttpServletRequest request) {
        MyUserDetails myUserDetails1 = userService.updateUser(myUserDetails);
        //1.从HttpServletRequest中获取SecurityContextImpl对象
        SecurityContextImpl securityContextImpl = (SecurityContextImpl) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        //2.从SecurityContextImpl中获取Authentication对象
        Authentication authentication = securityContextImpl.getAuthentication();
        //获取权限
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        //3.初始化UsernamePasswordAuthenticationToken实例 ，这里的参数user就是我们要更新的用户信息
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(myUserDetails1, authentication.getCredentials(), authorities);
        auth.setDetails(authentication.getDetails());
        //4.重新设置SecurityContextImpl对象的Authentication
        securityContextImpl.setAuthentication(auth);
        return "redirect:/userprofile.html";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }

}

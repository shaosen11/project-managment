package cn.edu.lingnan.projectmanagment.controller;

import cn.edu.lingnan.projectmanagment.bean.MyUserDetails;
import cn.edu.lingnan.projectmanagment.bean.UserRecord;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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

    //添加用户1
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

    //查询所有用户信息
    @GetMapping("/user_list")
    public String userList(Model model){
        List<MyUserDetails> list = userService.getUserList();
        model.addAttribute("userlist",list);
        System.out.println("查询所有用户"+list);
        return "tables/userlist";
    }

    //添加用户2
    @PostMapping("/add_user")
    public String addUser2(MyUserDetails myUserDetails,Model model){
        System.out.println("添加用户："+myUserDetails);
        MyUserDetails flag = userService.checkEmail(myUserDetails.getEmail());
        System.out.println("addflag="+flag+" email="+myUserDetails.getEmail());
        if(flag == null){
            String password = passwordEncoder.encode(myUserDetails.getPassword());
            myUserDetails.setPassword(password);
            userService.addUser2(myUserDetails);
            System.out.println("添加用户成功！");
        }else{
            System.out.println("添加失败，该邮箱已被绑定！");
            model.addAttribute("addresult","添加失败，该邮箱已被绑定！");
        }
        return "tables/userlist";
    }

    //删除用户
    @PostMapping("/delete_user/{id}")
    public String deleteUser(@PathVariable("id")Integer id){
        Boolean flag =  userService.deleteUser(id);
        System.out.println("删除用户:"+id+flag);
        return "redirect:user_list";
    }

    //修改用户信息
    @PostMapping("/edit_user")
    public String editUser(MyUserDetails myUserDetails,Model model){
        System.out.println("editUser:用户："+myUserDetails);
        Boolean flag = userService.editUser(myUserDetails);
        if(flag){
            System.out.println("修改成功");
        }else{
            System.out.println("修改失败");
        }
        return "redirect:user_list";
    }

    //查询所有注销用户信息
    @GetMapping("/deleted_user_list")
    public String deletedUserList(Model model){
        List<MyUserDetails> list = userService.getDeletedUserList();
        model.addAttribute("deluserlist",list);
        System.out.println("查询所有已注销用户"+list);
        return "deleted/deleteduser";
    }

    //还原用户
    @PostMapping("/reduction/{id}")
    public String reductionUser(@PathVariable("id")Integer id){
        Boolean flag =  userService.reductionUser(id);
        System.out.println("删除用户:"+id+flag);
        return "redirect:../deleteduserList";
    }
}

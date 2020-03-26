package cn.edu.lingnan.projectmanagment.controller;

import cn.edu.lingnan.projectmanagment.bean.MyUserDetails;
import cn.edu.lingnan.projectmanagment.bean.UserRole;
import cn.edu.lingnan.projectmanagment.exception.AJaxResponse;
import cn.edu.lingnan.projectmanagment.exception.CustomException;
import cn.edu.lingnan.projectmanagment.exception.CustomExceptionType;
import cn.edu.lingnan.projectmanagment.service.UserService;
import cn.edu.lingnan.projectmanagment.service.impl.MyUserDetailsServiceImpl;
import cn.edu.lingnan.projectmanagment.service.impl.UserRoleServiceImpl;
import cn.edu.lingnan.projectmanagment.utils.MyContants;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import javax.websocket.server.PathParam;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import java.sql.Timestamp;
import java.util.UUID;


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

    @Autowired
    UserRoleServiceImpl userRoleService;

    @Value("${spring.security.loginType}")
    private String loginType;

    @Autowired
    JavaMailSenderImpl mailSender;

    @ResponseBody
    @GetMapping("/user")
    public boolean User(String email){
        MyUserDetails myUserDetails =  userService.findByEmail(email);
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
        myUserDetails = userService.findByEmail(myUserDetails.getEmail());
        //给用户权限
        UserRole userRole = new UserRole();
        userRole.setUserId(myUserDetails.getId());
        //暂时默认设置为管理员
        userRole.setRoleId(MyContants.USER_ROLE_ADMIN);
        userRoleService.insertUserRole(userRole);
        return "redirect:/login.html";
    }

    @PutMapping("/user")
    public String updateUser(MyUserDetails myUserDetails, HttpServletRequest request) {
        System.out.println(myUserDetails);
        if (myUserDetails.getPassword() != null){
            String password = passwordEncoder.encode(myUserDetails.getPassword());
            myUserDetails.setPassword(password);
        }
        MyUserDetails myUserDetails1 = userService.updateUser(myUserDetails);
        System.out.println(myUserDetails + "123456789");
        //1.从HttpServletRequest中获取SecurityContextImpl对象
        SecurityContextImpl securityContextImpl = (SecurityContextImpl) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        if (securityContextImpl != null){
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
        }else {
            return "login";
        }
    }

    @ResponseBody
    @GetMapping("/forget_password")
    public AJaxResponse forgetpasswordrequest(@Param("id") String email, HttpServletRequest request) {
        MyUserDetails myUserDetails = userService.findByEmail(email);
        String msg;
        if(myUserDetails == null){
            msg = "用户邮箱不存在";
            return AJaxResponse.error(new CustomException(CustomExceptionType.USER_INPUT_ERROR,msg));
        }
        try{
            //密钥
            String secretKey= UUID.randomUUID().toString();
            //30分钟后过期
            Timestamp outDate = new Timestamp(System.currentTimeMillis()+30*60*1000);
            //忽略毫秒数
            long date = outDate.getTime()/1000*1000;
            myUserDetails.setValidataCode(secretKey);
            myUserDetails.setOutDate(outDate);
            System.out.println("设置属性之后" + myUserDetails);
            //保存到数据库
            userService.updateUser(myUserDetails);
            String key = myUserDetails.getEmail()+"$"+date+"$"+secretKey;
            //数字签名
            String digitalSignature = DigestUtils.md5Hex(key);
            //发送邮件
            //1.创建一个复杂的消息邮件
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            //邮件设置
            String emailTitle = "密码找回";
            String path = request.getContextPath();
            String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
            String resetPassHref =  basePath+"reset_password?sid="+digitalSignature+"&email="+myUserDetails.getEmail();
            String emailContent = "请勿回复本邮件.点击下面的链接,重设密码<br/><a href="+resetPassHref +" target='_BLANK'>点击我重新设置密码</a>" +
                    "<br/>tips:本邮件超过30分钟,链接将会失效，需要重新申请'找回密码'"+key+"\t"+digitalSignature;
            System.out.println(resetPassHref);
            msg = "操作成功,已经发送找回密码链接到您邮箱。请在30分钟内重置密码";
            helper.setSubject(emailTitle);
            helper.setText(emailContent,true);
            helper.setTo(myUserDetails.getEmail());
            helper.setFrom(MyContants.SYSTEM_EMAIL);
            mailSender.send(mimeMessage);
        }catch (Exception e){
            e.printStackTrace();
            msg="未知错误,联系管理员吧。";
        }
        return AJaxResponse.success("/login",msg);
    }

    @GetMapping("/reset_password")
    public ModelAndView checkResetLink(String sid, String email){
        ModelAndView model = new ModelAndView("/resetpassword");
        String msg = "";
        if(sid.equals("") || email.equals("")){
            msg="链接不完整,请重新生成";
            model.addObject("msg",msg) ;
            System.out.println(email + "找回密码连接失效");
            return model;
        }
        MyUserDetails myUserDetails = userService.findByEmail(email);
        if(myUserDetails == null){
            msg = "链接错误,无法找到匹配用户,请重新申请找回密码.";
            model.addObject("msg",msg) ;
            System.out.println(email + "找回密码链接失效");
            return model;
        }
        Timestamp outDate = myUserDetails.getOutDate();
        //表示已经过期
        if(outDate.getTime() <= System.currentTimeMillis()){
            msg = "链接已经过期,请重新申请找回密码.";
            model.addObject("msg",msg) ;
            System.out.println(email + "找回密码链接失效");
            return model;
        }
        //数字签名
        String key = myUserDetails.getEmail()+"$"+outDate.getTime()/1000*1000+"$"+myUserDetails.getValidataCode();
        String digitalSignature = DigestUtils.md5Hex(key);
        System.out.println(key+"\t"+digitalSignature);
        if(!digitalSignature.equals(sid)) {
            msg = "链接不正确,是否已经过期了?重新申请";
            model.addObject("msg",msg) ;
            System.out.println(email + "找回密码连接失效");
            return model;
        }
        //返回到修改密码的界面
        model.setViewName("/resetpassword");
        model.addObject("email",email);
        return model;
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
        MyUserDetails flag = userService.findByEmail(myUserDetails.getEmail());
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

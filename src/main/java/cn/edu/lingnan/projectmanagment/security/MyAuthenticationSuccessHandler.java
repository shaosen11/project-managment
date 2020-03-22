package cn.edu.lingnan.projectmanagment.security;

import cn.edu.lingnan.projectmanagment.bean.MyUserDetails;
import cn.edu.lingnan.projectmanagment.bean.UserRecord;
import cn.edu.lingnan.projectmanagment.exception.AJaxResponse;
import cn.edu.lingnan.projectmanagment.service.UserRecordService;
import cn.edu.lingnan.projectmanagment.service.UserService;
import cn.edu.lingnan.projectmanagment.utils.IPUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author shaosen
 */
@Component
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Value("${spring.security.loginType}")
    private String loginType;

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    UserService userService;

    @Autowired
    UserRecordService userRecordService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        if(loginType.equalsIgnoreCase("JSON")){
            afterLoginHandler(request);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(AJaxResponse.success("/index.html")));
        }else {
            //跳转登录之前请求的页面
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }

    public void afterLoginHandler(HttpServletRequest request){
        System.out.println("登录处理");
        //获取myUserDetails对象
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        //设置登录时间
        myUserDetails.setLastLoginTime(new Date());
        System.out.println(myUserDetails);
        userService.updateUser(myUserDetails);
        //存储登录日志
        UserRecord userRecord = new UserRecord();
        userRecord.setUserId(myUserDetails.getId());
        userRecord.setIp(IPUtil.getIP(request));
        userRecord.setOperateTime(new Date());
        userRecord.setOperateMassage("登录");
        userRecordService.insert(userRecord);
        return ;
    }

}

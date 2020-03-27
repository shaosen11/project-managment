package cn.edu.lingnan.projectmanagment.security;

import cn.edu.lingnan.projectmanagment.exception.AJaxResponse;
import cn.edu.lingnan.projectmanagment.service.UserRecordService;
import cn.edu.lingnan.projectmanagment.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    @Autowired
    cn.edu.lingnan.projectmanagment.utils.afterLoginOrLoginOutHandler afterLoginOrLoginOutHandler;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        if(loginType.equalsIgnoreCase("JSON")){
            afterLoginOrLoginOutHandler.afterLoginOrLoginOutHandler(request, "登录系统");
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(AJaxResponse.success("/index.html")));
        }else {
            //跳转登录之前请求的页面
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }

}

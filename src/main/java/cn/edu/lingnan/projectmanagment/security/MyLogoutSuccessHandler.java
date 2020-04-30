package cn.edu.lingnan.projectmanagment.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * @author shaosen
 */
@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
    @Autowired
    cn.edu.lingnan.projectmanagment.utils.AfterLoginOrLoginOutHandler afterLoginOrLoginOutHandler;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Authentication authentication)
            throws IOException {
        //写一下业务逻辑
        httpServletResponse.sendRedirect("/login.html");
    }
}

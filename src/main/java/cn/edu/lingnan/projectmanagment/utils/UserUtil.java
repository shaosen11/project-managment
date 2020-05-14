package cn.edu.lingnan.projectmanagment.utils;

import cn.edu.lingnan.projectmanagment.bean.MyUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 21:58 2020/5/14
 */
public class UserUtil {
    public static final MyUserDetails getMyUserDetailsBySecurity(HttpServletRequest request) {
        SecurityContextImpl securityContextImpl = (SecurityContextImpl) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        if (securityContextImpl != null) {
            return (MyUserDetails) SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getPrincipal();
        }
        return null;
    }
}

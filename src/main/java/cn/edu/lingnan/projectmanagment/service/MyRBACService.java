package cn.edu.lingnan.projectmanagment.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
/**
 * @author shaosen
 */
public interface MyRBACService {
    /**
     * 获取用户权限
     * @param request
     * @param authentication
     * @return
     */
    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}

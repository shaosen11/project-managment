package cn.edu.lingnan.projectmanagment.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface MyRBACService {
    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}

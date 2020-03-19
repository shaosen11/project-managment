package cn.edu.lingnan.projectmanagment.component;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录检查
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {
    //在目标方法执行之前
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        Object user = request.getSession().getAttribute("loginUser");
//        if (user == null) {
//            //未登录，放回登录页面
//            request.setAttribute("msg", "没有权限请先登录");
//            request.getRequestDispatcher("/login.html").forward(request, response);
//            return false;
//        } else {
//            return true;
//        }
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
//    }
}

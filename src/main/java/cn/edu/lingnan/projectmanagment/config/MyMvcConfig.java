package cn.edu.lingnan.projectmanagment.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author shaosen
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/login.html").setViewName("login");
        //用戶信息頁面
        registry.addViewController("/userprofile").setViewName("userprofile");
        //跳转到忘记密码页面
        registry.addViewController("/forgetpassword").setViewName("forgetpassword");
        //跳转到重置密码页面
        registry.addViewController("/resetpassword").setViewName("resetpassword");
        //跳转到项目页面
        registry.addViewController("/projectview").setViewName("projectview");
    }



}


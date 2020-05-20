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
        //跳转到系统介绍页面
        registry.addViewController("/projectIntroduce").setViewName("introduce/projectIntroduce");
        //跳转到主页引导页面
        registry.addViewController("/projectIndexIntroduce").setViewName("introduce/projectIndexIntroduce");
        //跳转到项目主页引导页面
        registry.addViewController("/projectViewIntroduce").setViewName("introduce/projectViewIntroduce");
        //跳转到项目文件引导页面
        registry.addViewController("/projectDocumentIntroduce").setViewName("introduce/projectDocumentIntroduce");
    }



}


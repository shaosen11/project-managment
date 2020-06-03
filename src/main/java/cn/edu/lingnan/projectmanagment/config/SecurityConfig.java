package cn.edu.lingnan.projectmanagment.config;

import cn.edu.lingnan.projectmanagment.imageCode.CaptchaCodeFilter;
import cn.edu.lingnan.projectmanagment.security.MyAuthenticationFailureHandler;
import cn.edu.lingnan.projectmanagment.security.MyAuthenticationSuccessHandler;
import cn.edu.lingnan.projectmanagment.security.MyExpiredSessionStrategy;
import cn.edu.lingnan.projectmanagment.security.MyLogoutSuccessHandler;
import cn.edu.lingnan.projectmanagment.service.impl.MyUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @author shaosen
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Autowired
    private MyUserDetailsServiceImpl myUserDetailsService;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private MyLogoutSuccessHandler myLogoutSuccessHandler;

    @Autowired
    private CaptchaCodeFilter captchaCodeFilter;


    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.addFilterBefore(captchaCodeFilter, UsernamePasswordAuthenticationFilter.class)
            .logout()
                //设置退出url
                .logoutUrl("/security_login_out")
//                .logoutSuccessUrl("/login.html")//设置退出成功页面
                //删除JSESSIONID
                .deleteCookies("JSESSIONID")
                //自定义退出处理器
                .logoutSuccessHandler(myLogoutSuccessHandler)
            .and()
                .rememberMe()
                //设置前端属性名称
                .rememberMeParameter("remember-me")
                //设置cookie名称
                .rememberMeCookieName("remember-me-cookie")
                //设置过期时间
                .tokenValiditySeconds(7 * 24 * 60 * 60)
                //存储token到数据库，应用重新启动不会刷掉token
                .tokenRepository(persistentTokenRepository())
            .and()
                //跨域请求
                .csrf().disable()
            //formlogin验证
            .formLogin()
                //指定登录页面
                .loginPage("/login")
                //可以指定前端页面的用户名参数
                .usernameParameter("email")
                //可以指定前端页面的密码参数
                .passwordParameter("password")
                //指定登录URL
                .loginProcessingUrl("/login_user")
//                    .defaultSuccessUrl("/index")//指定登录成功的跳转页面
//                    .failureUrl("/login.html")
                //自定义的成功处理器
                .successHandler(myAuthenticationSuccessHandler)
                //自定义的错误处理器
                .failureHandler(myAuthenticationFailureHandler)
            .and()
                //需要权限的请求
                .authorizeRequests()
                //允许所有访问
                .antMatchers("/login.html","/login", "/kaptcha", "/loginUser", "/user").permitAll()
                .antMatchers("/forgetpassword","/forget_password","/resetpassword","/reset_password").permitAll()

                //nav_message.js
                .antMatchers("/myMessage").permitAll()
                //nav_sider.js
                .antMatchers("/user_projects").permitAll()
                //nav_sider_project.js
                .antMatchers("/projectsPackages","/projectMessageCount").permitAll()

                //inedx
                .antMatchers("/","/index.html","/index","/todayProjectsAndWeekProjects","/getProjectsByTypePage","/projects_index_click","/projectManagementAdmin").permitAll()
                //project_view
                .antMatchers("/projects_view/*","/getCodeInsertData","/getCodeDevoteData","/getLineChartDate","/documentRecordPage","/project").permitAll()
                //project_plan_view
                .antMatchers("/projects_plan_view/*","/projects_plan","/projects_plan_time","/projectFunctionMessageAlert").permitAll()
                //project_function_view
                .antMatchers("/project_function_view/*","/allFunctionPage","/projectFunctionDataCicleChart","/projectFunctionTotal","/projectFunction").permitAll()
                //project_user_view
                .antMatchers("/project_user_view/*","/projectUserPage","/projectsUserDevotion","/user_finish_function","/user_developing_function","/projectUserTotal").permitAll()
                //project_user_cooperation_view
                .antMatchers("/project_user_cooperation_view","/projectUserPage","/inviteUser","/user_information").permitAll()
                //document
                .antMatchers("/document/*/*","/files/projects/*/*").permitAll()
                //projectIntroduce
                .antMatchers("/projectIntroduce","/projectIndexIntroduce","/projectViewIntroduce","/projectDocumentIntroduce").permitAll()
                //projectfunctiondetailview
                .antMatchers("/project_function_detail_view/*").permitAll()

                .anyRequest().access("@rbacService.hasPermission(request,authentication)")
            .and()
                .sessionManagement()
                //如果有需要就创建session
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                //当session超时，重新跳转到指定页面
                .invalidSessionUrl("/")
                //每次登录都替换sesionID
                .sessionFixation().migrateSession()
                //只允许最多一个登录
                .maximumSessions(1)
                //允许登录之后再登录
                .maxSessionsPreventsLogin(false)
                //返回自定义的配置
                .expiredSessionStrategy(new MyExpiredSessionStrategy())
            ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
        //将项目中的静态资源路径开放
        web.ignoring()
            .antMatchers("/assets/**");
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }
}


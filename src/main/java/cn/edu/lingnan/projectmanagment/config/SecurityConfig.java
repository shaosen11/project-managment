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
                .logoutUrl("/loginOut")//设置退出url
//                .logoutSuccessUrl("/login.html")//设置退出成功页面
                .deleteCookies("JSESSIONID")//删除JSESSIONID
                .logoutSuccessHandler(myLogoutSuccessHandler)//自定义退出处理器
            .and()
                .rememberMe()
                .rememberMeParameter("remember-me")//设置前端属性名称
                .rememberMeCookieName("remember-me-cookie")//设置cookie名称
                .tokenValiditySeconds(7 * 24 * 60 * 60)//设置过期时间
                .tokenRepository(persistentTokenRepository())//存储token到数据库，应用重新启动不会刷掉token
            .and()
                .csrf().disable()//跨域请求
            .formLogin()//formlogin验证
                .loginPage("/login")//指定登录页面
                .usernameParameter("email")//可以指定前端页面的用户名参数
                .passwordParameter("password")//可以指定前端页面的密码参数
                .loginProcessingUrl("/loginUser")//指定登录URL
//                    .defaultSuccessUrl("/index")//指定登录成功的跳转页面
//                    .failureUrl("/login.html")
                .successHandler(myAuthenticationSuccessHandler)//自定义的成功处理器
                .failureHandler(myAuthenticationFailureHandler)//自定义的错误处理器
            .and()
                .authorizeRequests()//需要权限的请求
                .antMatchers("/","/login.html","/login", "/kaptcha", "/loginUser").permitAll()//允许所有访问
                .antMatchers("/index.html").authenticated()
                .anyRequest().access("@rbacService.hasPermission(request,authentication)")
            .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)//如果有需要就创建session
                .invalidSessionUrl("/login.html")//当session超时，重新跳转到指定页面
                .sessionFixation().migrateSession()//每次登录都替换sesionID
                .maximumSessions(1)//只允许最多一个登录
                .maxSessionsPreventsLogin(false)//允许登录之后再登录
                .expiredSessionStrategy(new MyExpiredSessionStrategy())//返回自定义的配置
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

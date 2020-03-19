package cn.edu.lingnan.projectmanagment.service.impl;

import cn.edu.lingnan.projectmanagment.bean.MyUserDetails;
import cn.edu.lingnan.projectmanagment.mapper.MyRBACServiceMapper;
import cn.edu.lingnan.projectmanagment.service.MyRBACService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component("rbacService")
public class MyRBACServiceImpl implements MyRBACService {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    private MyRBACServiceMapper myRBACServiceMapper;

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();

        if(principal instanceof UserDetails){
            String email = ((MyUserDetails)principal).getEmail();
            List<String> urls = myRBACServiceMapper.findUrlsByEmail(email);
            //通过查询到的url来匹配我们可以访问的url
            return urls.stream().anyMatch(
                    url ->antPathMatcher.match(url,request.getRequestURI())
            );
        }
        return false;
    }
}

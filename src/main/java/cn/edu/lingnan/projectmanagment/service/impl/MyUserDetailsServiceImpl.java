package cn.edu.lingnan.projectmanagment.service.impl;

import cn.edu.lingnan.projectmanagment.bean.MyUserDetails;
import cn.edu.lingnan.projectmanagment.mapper.MyUserDetailsMapper;
import cn.edu.lingnan.projectmanagment.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@CacheConfig(cacheNames = "userDetails")
public class MyUserDetailsServiceImpl implements MyUserDetailsService {
    @Autowired
    private MyUserDetailsMapper myUserDetailsMapper;

    @Override
    public MyUserDetails findByEmail(String email) {
        return myUserDetailsMapper.findByEmail(email);
    }

    @Override
    public List<String> findRoleByEmail(String email) {
        return myUserDetailsMapper.findRoleByEmail(email);
    }

    @Override
    public List<String> findAuthorityByRoleCodes(List<String> roleCodes) {
        return myUserDetailsMapper.findAuthorityByRoleCodes(roleCodes);
    }

    @Cacheable(key = "'loginUser_' + #email")
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //加载基础用户信息
        MyUserDetails myUserDetails = myUserDetailsMapper.findByEmail(email);

        //加载用户角色列表
        List<String> roleCodes = myUserDetailsMapper.findRoleByEmail(email);


        //通过用户角色列表加载用户的资源权限
        List<String> authoritys = myUserDetailsMapper.findAuthorityByRoleCodes(roleCodes);

        //角色是一个特殊的权限，ROLE_前缀
        roleCodes = roleCodes.stream()
                .map(rc -> "ROLE_" + rc)
                .collect(Collectors.toList());

        authoritys.addAll(roleCodes);

        myUserDetails.setAuthorities(
                AuthorityUtils.commaSeparatedStringToAuthorityList(
                        String.join(",",authoritys)
                )
        );
        return myUserDetails;
    }
}

package cn.edu.lingnan.projectmanagment.service;

import cn.edu.lingnan.projectmanagment.bean.MyUserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface MyUserDetailsService extends UserDetailsService {
    //根据userID查询用户信息
    MyUserDetails findByEmail(String email);

    //根据userID查询你用户角色
    List<String> findRoleByEmail(String email);

    //根据用户角色查询用户权限
    List<String> findAuthorityByRoleCodes(List<String> roleCodes);
}

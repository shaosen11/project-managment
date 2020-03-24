package cn.edu.lingnan.projectmanagment.service.impl;

import cn.edu.lingnan.projectmanagment.bean.UserRole;
import cn.edu.lingnan.projectmanagment.mapper.UserRoleMapper;
import cn.edu.lingnan.projectmanagment.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 17:52 2020/3/24
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    UserRoleMapper userRoleMapper;

    @Override
    public boolean insertUserRole(UserRole userRole) {
        return userRoleMapper.insertUserRole(userRole);
    }
}

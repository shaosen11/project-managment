package cn.edu.lingnan.projectmanagment.service.impl;

import cn.edu.lingnan.projectmanagment.bean.MyUserDetails;
import cn.edu.lingnan.projectmanagment.bean.User;
import cn.edu.lingnan.projectmanagment.mapper.UserMapper;
import cn.edu.lingnan.projectmanagment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "user")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Cacheable(key = "'user_' + #email")
    @Override
    public MyUserDetails checkEmail(String email) {
        return userMapper.checkEmail(email);
    }

    @Override
    public boolean addUser(MyUserDetails myUserDetails) {
        System.out.println(myUserDetails);
        return userMapper.addUser(myUserDetails);
    }
}


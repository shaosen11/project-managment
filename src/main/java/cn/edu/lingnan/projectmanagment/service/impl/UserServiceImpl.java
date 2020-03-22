package cn.edu.lingnan.projectmanagment.service.impl;

import cn.edu.lingnan.projectmanagment.bean.MyUserDetails;
import cn.edu.lingnan.projectmanagment.mapper.UserMapper;
import cn.edu.lingnan.projectmanagment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author shaosen
 */
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
        return userMapper.addUser(myUserDetails);
    }

    @CachePut(key = "'user_' + #myUserDetails.email")
    @Override
    public MyUserDetails updateUser(MyUserDetails myUserDetails) {
        userMapper.updateUser(myUserDetails);
        return myUserDetails;
    }
}


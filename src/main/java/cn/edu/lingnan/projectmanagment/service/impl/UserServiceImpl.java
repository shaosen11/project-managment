package cn.edu.lingnan.projectmanagment.service.impl;

import cn.edu.lingnan.projectmanagment.bean.MyUserDetails;
import cn.edu.lingnan.projectmanagment.bean.UserRecord;
import cn.edu.lingnan.projectmanagment.mapper.UserMapper;
import cn.edu.lingnan.projectmanagment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public MyUserDetails findByEmial(String email) {
        return userMapper.findByEmial(email);
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

    @Override
    public List<MyUserDetails> getUserList() {
        return userMapper.getUserList();
    }

    @Override
    public boolean addUser2(MyUserDetails myUserDetails) {
        return userMapper.addUser2(myUserDetails);
    }

    @Override
    public boolean deleteUser(Integer id) {
        return userMapper.deleteUser(id);
    }

    @Override
    public boolean editUser(MyUserDetails myUserDetails) {
        return userMapper.editUser(myUserDetails);
    }

    @Override
    public boolean reductionUser(Integer id) {
        return userMapper.reductionUser(id);
    }

    @Override
    public List<MyUserDetails> getDeletedUserList() {
        return userMapper.getDeletedUserList();
    }
}


package cn.edu.lingnan.projectmanagment.service.impl;

import cn.edu.lingnan.projectmanagment.bean.MyUserDetails;
import cn.edu.lingnan.projectmanagment.bean.Myprojects;
import cn.edu.lingnan.projectmanagment.bean.UserRecord;
import cn.edu.lingnan.projectmanagment.mapper.UserMapper;
import cn.edu.lingnan.projectmanagment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    public MyUserDetails findByEmail(String email) {
        return userMapper.findByEmail(email);
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
    public MyUserDetails findById(Integer id) {
        return userMapper.findById(id);
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

    @Override
    public List<Myprojects> getMyProjects(Integer id) {
        return userMapper.getMyProjects(id);
    }

    @Override
    public List<Myprojects> getMyChargeProjects(Integer id) {
        return userMapper.getMyChargeProjects(id);
    }

    @Override
    public List<Myprojects> getMyJoinProjects(Integer id) {
        return userMapper.getMyJoinProjects(id);
    }

    @Override
    public Integer myProjectScheduleNum(Integer id, String schedule) {
        return userMapper.myProjectScheduleNum(id,schedule);
    }

    @Override
    public Integer myProjectScheduleNum2(Integer id, String schedule) {
        return userMapper.myProjectScheduleNum2(id,schedule);
    }

    @Override
    public Integer myProjectScheduleNum3(Integer id, String schedule) {
        return userMapper.myProjectScheduleNum3(id,schedule);
    }

    @Override
    public List<Myprojects> getMyProjectsStore(Integer id) {
        return userMapper.getMyProjectsStore(id);
    }

    @Override
    public MyUserDetails getMyUserDetailsByUserId(Integer userId) {
        return userMapper.getMyUserDetailsByUserId(userId);
    }

    @Override
    public MyUserDetails getSimpleMyUserDetailsByUserId(Integer userId) {
        return userMapper.getSimpleMyUserDetailsByUserId(userId);
    }
}


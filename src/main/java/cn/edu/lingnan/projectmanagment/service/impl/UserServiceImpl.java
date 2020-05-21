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
    public List<Myprojects> getMyProjectsPage(Integer userId, Integer offset, Integer pageSize) {
        return userMapper.getMyProjectsPage(userId,offset,pageSize);
    }

    @Override
    public List<Myprojects> getMyChargeProjects(Integer id) {
        return userMapper.getMyChargeProjects(id);
    }

    @Override
    public List<Myprojects> getMyChargeProjectsPage(Integer userId, Integer offset, Integer pageSize) {
        return userMapper.getMyChargeProjectsPage(userId,offset,pageSize);
    }

    @Override
    public List<Myprojects> getMyJoinProjects(Integer id) {
        return userMapper.getMyJoinProjects(id);
    }

    @Override
    public List<Myprojects> getMyJoinProjectsPage(Integer userId, Integer offset, Integer pageSize) {
        return userMapper.getMyJoinProjectsPage(userId,offset,pageSize);
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
    public List<Myprojects> getMyProjectsStorePage(Integer userId, Integer offset, Integer pageSize) {
        return userMapper.getMyProjectsStorePage(userId,offset,pageSize);
    }

    @Override
    public MyUserDetails getMyUserDetailsByUserId(Integer userId) {
        return userMapper.getMyUserDetailsByUserId(userId);
    }

    @Override
    public MyUserDetails getSimpleMyUserDetailsByUserId(Integer userId) {
        return userMapper.getSimpleMyUserDetailsByUserId(userId);
    }

    @Override
    public Integer userCount() {
        return userMapper.userCount();
    }

    @Override
    public Integer getMyProjectsByType(Integer id,String type) {
        return userMapper.getMyProjectsByType(id,type);
    }

    @Override
    public List<Myprojects> getMyProjectsPageByType(Integer userId, String type, Integer offset, Integer pageSize) {
        return userMapper.getMyProjectsPageByType(userId,type,offset,pageSize);
    }

    @Override
    public Integer getMyProjectsByNameOrUser(Integer id, String nameOrUser) {
        return userMapper.getMyProjectsByNameOrUser(id,nameOrUser);
    }

    @Override
    public List<Myprojects> getMyProjectsPageByNameOrUser(Integer userId, String nameOrUser, Integer offset, Integer pageSize) {
        return userMapper.getMyProjectsPageByNameOrUser(userId,nameOrUser,offset,pageSize);
    }

    @Override
    public Integer getMyProjectsBySchedule(Integer id, String schedule) {
        return userMapper.getMyProjectsBySchedule(id,schedule);
    }

    @Override
    public List<Myprojects> getMyProjectsPageBySchedule(Integer userId, String schedule, Integer offset, Integer pageSize) {
        return userMapper.getMyProjectsPageBySchedule(userId,schedule,offset,pageSize);
    }

    @Override
    public Integer getMyProjectsByTypeSchedule(Integer id, String type, String schedule) {
        return userMapper.getMyProjectsByTypeSchedule(id,type,schedule);
    }

    @Override
    public List<Myprojects> getMyProjectsPageByTypeSchedule(Integer userId, String type, String schedule, Integer offset, Integer pageSize) {
        return userMapper.getMyProjectsPageByTypeSchedule(userId,type,schedule,offset,pageSize);
    }

    @Override
    public Integer getMyProjectsChargeByType(Integer id, String type) {
        return userMapper.getMyProjectsChargeByType(id,type);
    }

    @Override
    public List<Myprojects> getMyProjectsChargePageByType(Integer userId, String type, Integer offset, Integer pageSize) {
        return userMapper.getMyProjectsChargePageByType(userId,type,offset,pageSize);
    }

    @Override
    public Integer getMyProjectsChargeByNameOrUser(Integer id, String nameOrUser) {
        return userMapper.getMyProjectsChargeByNameOrUser(id,nameOrUser);
    }

    @Override
    public List<Myprojects> getMyProjectsChargePageByNameOrUser(Integer userId, String nameOrUser, Integer offset, Integer pageSize) {
        return userMapper.getMyProjectsChargePageByNameOrUser(userId,nameOrUser,offset,pageSize);
    }

    @Override
    public Integer getMyProjectsChargeBySchedule(Integer id, String schedule) {
        return userMapper.getMyProjectsChargeBySchedule(id,schedule);
    }

    @Override
    public List<Myprojects> getMyProjectsChargePageBySchedule(Integer userId, String schedule, Integer offset, Integer pageSize) {
        return userMapper.getMyProjectsChargePageBySchedule(userId,schedule,offset,pageSize);
    }

    @Override
    public Integer getMyProjectsChargeByTypeSchedule(Integer id, String type, String schedule) {
        return userMapper.getMyProjectsChargeByTypeSchedule(id,type,schedule);
    }

    @Override
    public List<Myprojects> getMyProjectsChargePageByTypeSchedule(Integer userId, String type, String schedule, Integer offset, Integer pageSize) {
        return userMapper.getMyProjectsChargePageByTypeSchedule(userId,type,schedule,offset,pageSize);
    }

    @Override
    public Integer getMyProjectsJoinByType(Integer id, String type) {
        return userMapper.getMyProjectsJoinByType(id,type);
    }

    @Override
    public List<Myprojects> getMyProjectsJoinPageByType(Integer userId, String type, Integer offset, Integer pageSize) {
        return userMapper.getMyProjectsJoinPageByType(userId,type,offset,pageSize);
    }

    @Override
    public Integer getMyProjectsJoinByNameOrUser(Integer id, String nameOrUser) {
        return userMapper.getMyProjectsJoinByNameOrUser(id,nameOrUser);
    }

    @Override
    public List<Myprojects> getMyProjectsJoinPageByNameOrUser(Integer userId, String nameOrUser, Integer offset, Integer pageSize) {
        return userMapper.getMyProjectsJoinPageByNameOrUser(userId,nameOrUser,offset,pageSize);
    }

    @Override
    public Integer getMyProjectsJoinBySchedule(Integer id, String schedule) {
        return userMapper.getMyProjectsJoinBySchedule(id,schedule);
    }

    @Override
    public List<Myprojects> getMyProjectsJoinPageBySchedule(Integer userId, String schedule, Integer offset, Integer pageSize) {
        return userMapper.getMyProjectsJoinPageBySchedule(userId,schedule,offset,pageSize);
    }

    @Override
    public Integer getMyProjectsJoinByTypeSchedule(Integer id, String type, String schedule) {
        return userMapper.getMyProjectsJoinByTypeSchedule(id,type,schedule);
    }

    @Override
    public List<Myprojects> getMyProjectsJoinPageByTypeSchedule(Integer userId, String type, String schedule, Integer offset, Integer pageSize) {
        return userMapper.getMyProjectsJoinPageByTypeSchedule(userId,type,schedule,offset,pageSize);
    }
}


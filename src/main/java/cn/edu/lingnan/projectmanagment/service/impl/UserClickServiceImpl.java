package cn.edu.lingnan.projectmanagment.service.impl;

import cn.edu.lingnan.projectmanagment.bean.UserClick;
import cn.edu.lingnan.projectmanagment.mapper.UserClickMapper;
import cn.edu.lingnan.projectmanagment.service.UserClickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserClickServiceImpl implements UserClickService {
    @Autowired
    private UserClickMapper userClickMapper;


    @Override
    public UserClick getOneUserClick(Integer userId, Integer projectId) {
        return userClickMapper.getOneUserClick(userId,projectId);
    }

    @Override
    public boolean addUserClick(UserClick userClick) {
        return userClickMapper.addUserClick(userClick);
    }

    @Override
    public boolean deleteUserClick(Integer userId, Integer projectId) {
        return userClickMapper.deleteUserClick(userId,projectId);
    }

    @Override
    public boolean reductionUserClick(Integer userId, Integer projectId) {
        return userClickMapper.reductionUserClick(userId,projectId);
    }

    @Override
    public Integer countProjectByClick(Integer id) {
        return userClickMapper.countProjectByClick(id);
    }
}

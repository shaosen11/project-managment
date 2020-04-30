package cn.edu.lingnan.projectmanagment.service.impl;

import cn.edu.lingnan.projectmanagment.bean.UserStore;
import cn.edu.lingnan.projectmanagment.mapper.UserStoreMapper;
import cn.edu.lingnan.projectmanagment.service.UserStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserStoreServiceImpl implements UserStoreService {
    @Autowired
    private UserStoreMapper userStoreMapper;

    @Override
    public UserStore getOneUserStore(Integer userId, Integer projectId) {
        return userStoreMapper.getOneUserStore(userId,projectId);
    }

    @Override
    public boolean addUserStore(UserStore userStore) {
        return userStoreMapper.addUserStore(userStore);
    }

    @Override
    public boolean deleteUserStore(Integer userId, Integer projectId) {
        return userStoreMapper.deleteUserStore(userId,projectId);
    }

    @Override
    public boolean reductionUserStore(Integer userId, Integer projectId) {
        return userStoreMapper.reductionUserStore(userId,projectId);
    }

    @Override
    public Integer countProjectBeStored(Integer id) {
        return userStoreMapper.countProjectBeStored(id);
    }

    @Override
    public Integer countUserByStored() {
        return userStoreMapper.countUserByStored();
    }

    @Override
    public List findTheFirstRecored() {
        return userStoreMapper.findTheFirstRecored();
    }

    @Override
    public UserStore findTheSurplusRecored(int i) {
        return userStoreMapper.findTheSurplusRecored(i);
    }
}

package cn.edu.lingnan.projectmanagment.service.impl;

import cn.edu.lingnan.projectmanagment.bean.UserLike;
import cn.edu.lingnan.projectmanagment.mapper.UserLikeMapper;
import cn.edu.lingnan.projectmanagment.service.UserLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLikeServiceImpl implements UserLikeService {
    @Autowired
    private UserLikeMapper userLikeMapper;

    @Override
    public UserLike getOneUserLike(Integer userId, Integer projectId) {
        return userLikeMapper.getOneUserLike(userId,projectId);
    }

    @Override
    public boolean addUserLike(UserLike userLike) {
        return userLikeMapper.addUserLike(userLike);
    }

    @Override
    public boolean deleteUserLike(Integer userId, Integer projectId) {
        return userLikeMapper.deleteUserLike(userId,projectId);
    }

    @Override
    public boolean reductionUserLike(Integer userId, Integer projectId) {
        return userLikeMapper.reductionUserLike(userId,projectId);
    }

    @Override
    public Integer countProjectBeLiked(Integer id) {
        return userLikeMapper.countProjectBeLiked(id);
    }
}

package cn.edu.lingnan.projectmanagment.service.impl;

import cn.edu.lingnan.projectmanagment.bean.UserCodeUpdateRecord;
import cn.edu.lingnan.projectmanagment.mapper.UserCodeUpdateRecordMapper;
import cn.edu.lingnan.projectmanagment.service.UserCodeUpdateRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 1:45 2020/4/16
 */
@Service
public class UserCodeUpdateRecordServiceImpl implements UserCodeUpdateRecordService {
    @Autowired
    UserCodeUpdateRecordMapper userCodeUpdateRecordMapper;
    @Override
    public List<UserCodeUpdateRecord> getAllByUserId(Integer userId) {
        return userCodeUpdateRecordMapper.getAllByUserId(userId);
    }
}

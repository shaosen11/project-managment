package cn.edu.lingnan.projectmanagment.service.impl;

import cn.edu.lingnan.projectmanagment.bean.UserRecord;
import cn.edu.lingnan.projectmanagment.mapper.UserRecordMapper;
import cn.edu.lingnan.projectmanagment.service.UserRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 12:31 2020/3/22
 */
@Service
public class UserRecordServiceImpl implements UserRecordService {
    @Autowired
    UserRecordMapper userRecordMapper;

    @Override
    public boolean insert(UserRecord userRecord) {
        return userRecordMapper.insert(userRecord);
    }
}

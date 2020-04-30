package cn.edu.lingnan.projectmanagment.service;

import cn.edu.lingnan.projectmanagment.bean.UserCodeUpdateRecord;

import java.util.List;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 1:45 2020/4/16
 */
public interface UserCodeUpdateRecordService {
    /**
     * 查找用户所有贡献图
     * @param userId
     * @return
     */
    List<UserCodeUpdateRecord> getAllByUserId(Integer userId);
}


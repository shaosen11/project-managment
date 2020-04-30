package cn.edu.lingnan.projectmanagment.service;

import cn.edu.lingnan.projectmanagment.bean.UserRecord;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 12:28 2020/3/22
 */
public interface UserRecordService {
    /**
     * 插入登录日志
     * @param userRecord
     * @return
     */
    boolean insert(UserRecord userRecord);
}


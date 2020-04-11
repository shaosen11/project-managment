package cn.edu.lingnan.projectmanagment.service;

import cn.edu.lingnan.projectmanagment.bean.Message;

import java.util.List;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 20:33 2020/4/11
 */
public interface MessageService {
    /**
     * 通过userId查询所有消息
     * @param userId
     * @return
     */
    List<Message> getByUserId(Integer userId);

    /**
     * 插入一条消息
     * @param message
     * @return
     */
    boolean insert(Message message);
}

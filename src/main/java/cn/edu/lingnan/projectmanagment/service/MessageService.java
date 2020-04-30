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
     * @param needToDo
     * @return
     */
    List<Message> getByUserId(Integer userId, Integer needToDo);

    /**
     * 插入一条消息
     * @param message
     * @return
     */
    boolean insert(Message message);

    /**
     * 标记所有消息为已读
     * @param userId
     * @param isRead
     * @return
     */
    boolean updateAllMessageIsReadByUserId(Integer userId, Integer isRead);
}

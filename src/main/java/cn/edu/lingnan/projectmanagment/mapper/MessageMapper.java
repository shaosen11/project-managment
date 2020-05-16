package cn.edu.lingnan.projectmanagment.mapper;

import cn.edu.lingnan.projectmanagment.bean.Message;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 20:36 2020/4/11
 */
@Mapper
@Repository
public interface MessageMapper {
    /**
     * 通过userId查询所有消息
     * @param userId
     * @param needToDo
     * @return
     */
    List<Message> getByUserId(Integer userId, Integer needToDo);

    /**
     * 通过userId查询所有消息
     * @param userId
     * @param needToDo
     * @param offset
     * @param pageSize
     * @return
     */
    List<Message> getByUserIdAndOffsetAndPageSize(Integer userId, Integer needToDo, Integer offset, Integer pageSize);


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

    /**
     * 通过id查找消息
     *
     * @param id
     * @return
     */
    Message getById(Integer id);

    /**
     * 更新消息
     * @param message
     * @return
     */
    boolean update(Message message);

    /**
     * 标记消息为已读
     *
     * @param messageId
     * @return
     */
    boolean updateMessageIsReadByMessageId(Integer messageId, Integer isRead);

    /**
     * 获取消息数量
     *
     * @param userId
     * @return
     */
    Integer messageCount(Integer userId);

    /**
     * 获取待办消息数量
     *
     * @param userId
     * @return
     */
    Integer messageNeedToDoCount(Integer userId);
}


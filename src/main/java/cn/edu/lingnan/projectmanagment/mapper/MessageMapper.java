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
}


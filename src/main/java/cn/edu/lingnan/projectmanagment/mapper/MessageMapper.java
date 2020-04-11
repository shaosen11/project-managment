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

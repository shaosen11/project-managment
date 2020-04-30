package cn.edu.lingnan.projectmanagment.mapper;

import cn.edu.lingnan.projectmanagment.bean.MessageType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 20:37 2020/4/11
 */
@Mapper
@Repository
public interface MessageTypeMapper {
    /**
     * 通过消息码得到消息类型
     * @param id
     * @return
     */
    MessageType getById(Integer id);
}


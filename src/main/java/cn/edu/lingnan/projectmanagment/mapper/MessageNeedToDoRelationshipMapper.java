package cn.edu.lingnan.projectmanagment.mapper;

import cn.edu.lingnan.projectmanagment.bean.MessageNeedToDoRelationship;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 23:13 2020/4/30
 */
@Mapper
@Repository
public interface MessageNeedToDoRelationshipMapper {
    /**
     * 通过消息id查询需要处理的事情
     *
     * @param messageId
     * @return
     */
    MessageNeedToDoRelationship getByMessageId(Integer messageId);

    /**
     * 通过projectsUserCooperationId查询需要处理的事情
     *
     * @param projectsUserCooperationId
     * @return
     */
    MessageNeedToDoRelationship getByProjectsUserCooperationId(Integer projectsUserCooperationId);

    /**
     * 插入一条关系
     *
     * @param messageNeedToDoRelationship
     */
    void insert(MessageNeedToDoRelationship messageNeedToDoRelationship);
}

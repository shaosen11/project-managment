package cn.edu.lingnan.projectmanagment.service;

import cn.edu.lingnan.projectmanagment.bean.MessageNeedToDoRelationship;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 23:12 2020/4/30
 */
public interface MessageNeedToDoRelationshipService {


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

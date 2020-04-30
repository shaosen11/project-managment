package cn.edu.lingnan.projectmanagment.service.impl;

import cn.edu.lingnan.projectmanagment.bean.MessageNeedToDoRelationship;
import cn.edu.lingnan.projectmanagment.mapper.MessageNeedToDoRelationshipMapper;
import cn.edu.lingnan.projectmanagment.service.MessageNeedToDoRelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 23:13 2020/4/30
 */
@Service
public class MessageNeedToDoRelationshipServiceImpl implements MessageNeedToDoRelationshipService {
    @Autowired
    MessageNeedToDoRelationshipMapper messageNeedToDoRelationshipMapper;
    @Override
    public MessageNeedToDoRelationship getByMessageId(Integer messageId) {
        return messageNeedToDoRelationshipMapper.getByMessageId(messageId);
    }

    @Override
    public MessageNeedToDoRelationship getByProjectsUserCooperationId(Integer projectsUserCooperationId) {
        return messageNeedToDoRelationshipMapper.getByProjectsUserCooperationId(projectsUserCooperationId);
    }

    @Override
    public void insert(MessageNeedToDoRelationship messageNeedToDoRelationship) {
        messageNeedToDoRelationshipMapper.insert(messageNeedToDoRelationship);
    }
}

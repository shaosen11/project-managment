package cn.edu.lingnan.projectmanagment.service.impl;

import cn.edu.lingnan.projectmanagment.bean.MessageType;
import cn.edu.lingnan.projectmanagment.mapper.MessageTypeMapper;
import cn.edu.lingnan.projectmanagment.service.MessageTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 20:34 2020/4/11
 */
@Service
public class MessageTypeServiceImpl implements MessageTypeService {
    @Autowired
    MessageTypeMapper messageTypeMapper;
    @Override
    public MessageType getById(Integer id) {
        return messageTypeMapper.getById(id);
    }
}

package cn.edu.lingnan.projectmanagment.service.impl;

import cn.edu.lingnan.projectmanagment.bean.Message;
import cn.edu.lingnan.projectmanagment.mapper.MessageMapper;
import cn.edu.lingnan.projectmanagment.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 20:34 2020/4/11
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageMapper messageMapper;
    @Override
    public List<Message> getByUserId(Integer userId) {
        return messageMapper.getByUserId(userId);
    }

    @Override
    public boolean insert(Message message) {
        return messageMapper.insert(message);
    }
}

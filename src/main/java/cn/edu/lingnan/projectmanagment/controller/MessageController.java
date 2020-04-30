package cn.edu.lingnan.projectmanagment.controller;

import cn.edu.lingnan.projectmanagment.bean.Message;
import cn.edu.lingnan.projectmanagment.service.impl.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 21:32 2020/4/11
 */
@Controller
public class MessageController {
    @Autowired
    MessageServiceImpl messageService;

    @PostMapping("/myMessage")
    @ResponseBody
    public List<Message> myMessgae(Integer userId){
        List<Message> messageList = messageService.getByUserId(userId);
        return messageList;
    }
}


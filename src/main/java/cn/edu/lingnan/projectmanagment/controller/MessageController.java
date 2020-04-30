package cn.edu.lingnan.projectmanagment.controller;

import cn.edu.lingnan.projectmanagment.bean.Message;
import cn.edu.lingnan.projectmanagment.exception.AJaxResponse;
import cn.edu.lingnan.projectmanagment.service.impl.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    public List<Message> myMessgae(Integer userId, Integer needToDo){
        List<Message> messageList = messageService.getByUserId(userId, needToDo);
        return messageList;
    }

    @GetMapping("/updateAllMessageIsReadByUserId")
    @ResponseBody
    public AJaxResponse updateAllMessageIsReadByUserId(Integer userId){
        messageService.updateAllMessageIsReadByUserId(userId, 0);
        return AJaxResponse.success("修改成功");
    }
}

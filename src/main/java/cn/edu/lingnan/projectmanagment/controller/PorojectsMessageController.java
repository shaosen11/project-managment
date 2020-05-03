package cn.edu.lingnan.projectmanagment.controller;

import cn.edu.lingnan.projectmanagment.bean.ProjectsMessage;
import cn.edu.lingnan.projectmanagment.exception.AJaxResponse;
import cn.edu.lingnan.projectmanagment.exception.CustomException;
import cn.edu.lingnan.projectmanagment.exception.CustomExceptionType;
import cn.edu.lingnan.projectmanagment.service.impl.ProjectsMessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 19:38 2020/4/17
 */
@Controller
public class PorojectsMessageController {
    @Autowired
    ProjectsMessageServiceImpl projectsMessageService;

    @GetMapping("/projectmessage")
    public String projectMessage() {
        return "project/projectmessage";
    }

    @GetMapping("/project_user_message_person")
    @ResponseBody
    public List<ProjectsMessage> projectUserMessage(Integer projectId, Integer userId, Integer offset, Integer pageSize) {
        return projectsMessageService.getByProjectIdAndUserId(projectId, userId, offset, pageSize);
    }

    @GetMapping("/project_user_message_project")
    @ResponseBody
    public List<ProjectsMessage> projectsMessages(Integer projectId, Integer offset, Integer pageSize){
        return projectsMessageService.getByProjectId(projectId, offset, pageSize);
    }

    @GetMapping("/updateProjectMessageIsReadByProjectMessageId")
    @ResponseBody
    public AJaxResponse updateProjectMessageIsReadByProjectMessageId(Integer projectMessageId) {
        boolean result = projectsMessageService.updateProjectMessageIsReadByProjectMessageId(projectMessageId);
        if (result){
            return AJaxResponse.success("标志所有消息已读");
        }else {
            return AJaxResponse.error(new CustomException(CustomExceptionType.SYSTEM_ERROR, "标志所有消息已读操作失败"));
        }
    }
}


package cn.edu.lingnan.projectmanagment.controller;

import cn.edu.lingnan.projectmanagment.bean.ProjectsMessage;
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

    @GetMapping("/project_user_message")
    @ResponseBody
    public List<ProjectsMessage> projectUserMessage(Integer projectId, Integer userId, Integer offset, Integer pageSize) {
        return projectsMessageService.getByProjectIdAndUserId(projectId, userId, offset, pageSize);
    }
}

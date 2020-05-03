package cn.edu.lingnan.projectmanagment.controller;

import cn.edu.lingnan.projectmanagment.bean.*;
import cn.edu.lingnan.projectmanagment.exception.AJaxResponse;
import cn.edu.lingnan.projectmanagment.service.impl.*;
import cn.edu.lingnan.projectmanagment.utils.DateFromatUtil;
import cn.edu.lingnan.projectmanagment.utils.MessageTypeContants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 12:10 2020/5/2
 */
@Controller
public class ProjectsUserCooperationController {
    @Autowired
    ProjectsUserCooperationServiceImpl projectsUserCooperationService;

    @Autowired
    ProjectServiceImpl projectService;

    @Autowired
    ProjectUserServiceImpl projectUserService;

    @Autowired
    MessageServiceImpl messageService;

    @Autowired
    MessageNeedToDoRelationshipServiceImpl messageNeedToDoRelationshipService;

    @PostMapping("/inviteUser")
    @ResponseBody
    public AJaxResponse inviteUser(Integer projectId, Integer fromUserId, Integer toUserId) {
        //在项目不同的人都可以有不同的邀请
        ProjectsUserCooperation projectsUserCooperation = projectsUserCooperationService.getByProjectIdAndInProjectUserIdAndNotInProjectUserIdAndInviteAndFinish(projectId, fromUserId, toUserId, 1, 0);
        if (projectsUserCooperation == null) {
            //没有邀请过，新建消息，新建邀请，新建消息邀请关系
            //新建邀请
            projectsUserCooperation = new ProjectsUserCooperation();
            projectsUserCooperation.setProjectsId(projectId);
            projectsUserCooperation.setInProjectUserId(fromUserId);
            projectsUserCooperation.setNotInProjectUserId(toUserId);
            projectsUserCooperation.setTime(new Date());
            projectsUserCooperation.setInviteFlag(1);
            projectsUserCooperationService.insert(projectsUserCooperation);
            //新建信息
            //封装信息
            Message message = new Message();
            //查找项目
            message.setFromUserId(fromUserId);
            message.setToUserId(toUserId);
            message.setTypeId(MessageTypeContants.PROJECT_COOPERATION);
            Projects projects = projectService.getById(projectId);
            ProjectsUser projectsUser = projectUserService.getByUserIdAndProjectId(fromUserId, projectId);
            String msg = projects.getName() + "的" + projectsUser.getProjectsUserDuty().getDutyName() + projectsUser.getMyUserDetails().getUsername() + "邀请你一起开发";
            message.setMessage(msg);
            message.setTime(DateFromatUtil.getNowDate(new Date()));
            message.setNeedToDo(1);
            messageService.insert(message);
            //新建邀请关系
            MessageNeedToDoRelationship messageNeedToDoRelationship = new MessageNeedToDoRelationship();
            messageNeedToDoRelationship.setMessageId(message.getId());
            messageNeedToDoRelationship.setProjectsUserCooperrationId(projectsUserCooperation.getId());
            messageNeedToDoRelationshipService.insert(messageNeedToDoRelationship);
        } else {
            //有邀请过，更新消息时间，邀请时间
            projectsUserCooperation.setTime(new Date());
            projectsUserCooperationService.update(projectsUserCooperation);
            //通过projectsUserCooperationId查找message
            MessageNeedToDoRelationship messageNeedToDoRelationship = messageNeedToDoRelationshipService.getByProjectsUserCooperationId(projectsUserCooperation.getId());
            Message message = messageService.getById(messageNeedToDoRelationship.getMessageId());
            //更新消息时间
            message.setTime(DateFromatUtil.getNowDate(new Date()));
            messageService.update(message);
        }
        return AJaxResponse.success("/project/projectuserview", "邀请成功");
    }
}

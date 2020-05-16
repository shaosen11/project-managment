package cn.edu.lingnan.projectmanagment.controller;

import cn.edu.lingnan.projectmanagment.bean.*;
import cn.edu.lingnan.projectmanagment.exception.AJaxResponse;
import cn.edu.lingnan.projectmanagment.exception.CustomException;
import cn.edu.lingnan.projectmanagment.exception.CustomExceptionType;
import cn.edu.lingnan.projectmanagment.service.impl.*;
import cn.edu.lingnan.projectmanagment.utils.DateFromatUtil;
import cn.edu.lingnan.projectmanagment.utils.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 21:32 2020/4/11
 */
@Controller
public class MessageController {
    @Autowired
    MessageServiceImpl messageService;

    @Autowired
    MessageNeedToDoRelationshipServiceImpl messageNeedToDoRelationshipService;

    @Autowired
    ProjectsUserCooperationServiceImpl projectsUserCooperationService;

    @Autowired
    ProjectUserServiceImpl projectUserService;

    @Autowired
    ProjectsMessageServiceImpl projectsMessageService;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    ProjectServiceImpl projectService;

    @PostMapping("/myMessage")
    @ResponseBody
    public List<Message> myMessgae(Integer userId, Integer needToDo) {
        List<Message> messageList = messageService.getByUserId(userId, needToDo);
        return messageList;
    }

    @GetMapping("/updateAllMessageIsReadByUserId")
    @ResponseBody
    public AJaxResponse updateAllMessageIsReadByUserId(Integer userId) {
        boolean result = messageService.updateAllMessageIsReadByUserId(userId, 0);
        if (result) {
            return AJaxResponse.success("标志所有消息已读");
        } else {
            return AJaxResponse.error(new CustomException(CustomExceptionType.SYSTEM_ERROR, "标志所有消息已读操作失败"));
        }
    }

    @GetMapping("/updateMessageIsReadByMessageId")
    @ResponseBody
    public AJaxResponse updateMessageIsReadByMessageId(Integer messageId) {
        messageService.updateMessageIsReadByMessageId(messageId, 0);
        return AJaxResponse.success("标志消息已读");
    }

    @PutMapping("/agreeNeedToDo")
    @ResponseBody
    public AJaxResponse agreeNeedToDo(Integer messageId) {
        //通过messageId查找待办的事件
        MessageNeedToDoRelationship messageNeedToDoRelationship = messageNeedToDoRelationshipService.getByMessageId(messageId);
        //如果是项目邀请
        if (messageNeedToDoRelationship.getProjectsUserCooperrationId() != 0) {
            ProjectsUserCooperation projectsUserCooperation = projectsUserCooperationService.getById(messageNeedToDoRelationship.getProjectsUserCooperrationId());
            //获取邀请信息对应的项目，和被邀请人
            Integer inProjectUserId = projectsUserCooperation.getInProjectUserId();
            Integer notInProjectUserId = projectsUserCooperation.getNotInProjectUserId();
            Integer projectId = projectsUserCooperation.getProjectsId();
            Integer dutyCode = projectsUserCooperation.getDutyCode();
            //加入projectuser表
            ProjectsUser projectsUser = new ProjectsUser();
            projectsUser.setProjectsId(projectId);
            projectsUser.setUserId(notInProjectUserId);
            projectsUser.setDutyCode(dutyCode);
            projectsUser.setJoinTime(new Date());
            projectUserService.addProjectUser(projectsUser);
            //修改项目人数
            Projects project = projectService.getById(projectId);
            project.setUserCount(project.getUserCount() + 1);
            projectService.editProject(project);
            //封装项目消息
            ProjectsMessage projectsMessage = new ProjectsMessage();
            projectsMessage.setProjectId(projectId);
            projectsMessage.setTypeId(15);
            projectsMessage.setTime(DateFromatUtil.getNowDate(new Date()));
            projectsMessage.setAllFlag(1);
            //获取被邀请人信息
            MyUserDetails myUserDetailsByUserId = userService.getMyUserDetailsByUserId(notInProjectUserId);
            MyUserDetails inviteUser = userService.getMyUserDetailsByUserId(inProjectUserId);
            String msg = inviteUser.getUsername() + "成功邀请" + myUserDetailsByUserId.getUsername() + "加入团队";
            projectsMessage.setMessage(msg);
            projectsMessageService.insert(projectsMessage);
            //重新封装项目个人消息给邀请人
            projectsMessage.setFromUserId(notInProjectUserId);
            projectsMessage.setToUserId(inProjectUserId);
            projectsMessage.setTypeId(9);
            projectsMessage.setAllFlag(0);
            msg = myUserDetailsByUserId.getUsername() + "同意了你的邀请合作";
            projectsMessage.setMessage(msg);
            projectsMessageService.insert(projectsMessage);
            //处理关于这个项目邀请的消息和邀请
            List<ProjectsUserCooperation> projectIdAndNotInProjectUserIdAndInvite = projectsUserCooperationService.getByProjectIdAndNotInProjectUserIdAndInviteAndFinish(projectId, notInProjectUserId, 1, 0);
            if (projectIdAndNotInProjectUserIdAndInvite != null) {
                Message message;
                MessageNeedToDoRelationship messageNeedToDoRelationship1;
                for (ProjectsUserCooperation userCooperation : projectIdAndNotInProjectUserIdAndInvite) {
                    //标记项目对个人的所有邀请的消息已处理
                    messageNeedToDoRelationship1 = messageNeedToDoRelationshipService.getByProjectsUserCooperationId(userCooperation.getId());
                    message = messageService.getById(messageNeedToDoRelationship1.getMessageId());
                    message.setIsRead(0);
                    messageService.update(message);
                    //标记邀请为完成
                    userCooperation.setFinishFlag(1);
                    projectsUserCooperationService.update(userCooperation);
                }
            }
            //处理项目的申请（待办）

            //设置跳转页面
            Map<String, Object> pathMap = new HashMap<>();
            pathMap.put("projectId", projectId);
            pathMap.put("userId", notInProjectUserId);
            String pathString = "/projects_view" + PathUtil.pathUtil(pathMap);
            return AJaxResponse.success(pathString, "处理完成");
        }
        return null;
    }

    @PutMapping("/doNotAgreeNeedToDo")
    @ResponseBody
    public AJaxResponse doNotAgreeNeedToDo(Integer messageId) {
        //通过messageId查找待办的事件
        MessageNeedToDoRelationship messageNeedToDoRelationship = messageNeedToDoRelationshipService.getByMessageId(messageId);
        //如果是项目邀请
        if (messageNeedToDoRelationship.getProjectsUserCooperrationId() != 0) {
            ProjectsUserCooperation userCooperation = projectsUserCooperationService.getById(messageNeedToDoRelationship.getProjectsUserCooperrationId());
            //标志邀请已完成
            userCooperation.setSuccessFlag(-1);
            //标志邀请已结束
            userCooperation.setFinishFlag(1);
            projectsUserCooperationService.update(userCooperation);
            //标志消息已处理
            Message message = messageService.getById(messageNeedToDoRelationship.getMessageId());
            message.setIsRead(0);
            messageService.update(message);
            //发送消息给邀请人，邀请失败
            //封装项目消息
            ProjectsMessage projectsMessage = new ProjectsMessage();
            projectsMessage.setProjectId(userCooperation.getProjectsId());
            projectsMessage.setTypeId(10);
            projectsMessage.setToUserId(userCooperation.getInProjectUserId());
            projectsMessage.setTime(DateFromatUtil.getNowDate(new Date()));
            //获取邀请人
            MyUserDetails inviteUser = userService.getSimpleMyUserDetailsByUserId(userCooperation.getNotInProjectUserId());
            String msg = inviteUser.getUsername() + "拒绝了你的邀请邀请";
            projectsMessage.setMessage(msg);
            projectsMessageService.insert(projectsMessage);
            return AJaxResponse.success("处理完成");
        }
        return null;
    }

    @GetMapping("/message_view")
    public String messages() {
        return "/message";
    }

    @GetMapping("/message_person")
    @ResponseBody
    public List<Message> messagePerson(Integer userId, Integer offset, Integer pageSize) {
        return messageService.getByUserIdAndOffsetAndPageSize(userId, 0, offset, pageSize);
    }

    @GetMapping("/messageCount")
    @ResponseBody
    public Integer messageCount(Integer userId) {
        return messageService.messageCount(userId) + messageService.messageNeedToDoCount(userId);
    }

    @GetMapping("/message_need_to_do")
    @ResponseBody
    public List<Message> message_need_to_do(Integer userId, Integer offset, Integer pageSize) {
        return messageService.getByUserIdAndOffsetAndPageSize(userId, 1, offset, pageSize);
    }


}


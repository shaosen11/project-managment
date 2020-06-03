package cn.edu.lingnan.projectmanagment.controller;

import cn.edu.lingnan.projectmanagment.bean.*;
import cn.edu.lingnan.projectmanagment.exception.AJaxResponse;
import cn.edu.lingnan.projectmanagment.exception.CustomException;
import cn.edu.lingnan.projectmanagment.exception.CustomExceptionType;
import cn.edu.lingnan.projectmanagment.service.ProjectUserService;
import cn.edu.lingnan.projectmanagment.service.UserService;
import cn.edu.lingnan.projectmanagment.service.impl.*;
import cn.edu.lingnan.projectmanagment.utils.DateFromatUtil;
import cn.edu.lingnan.projectmanagment.utils.IPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
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
    @Autowired
    UserServiceImpl userService;
    @Autowired
    ProjectsMessageNeedToDoRelationshipServiceImpl projectsMessageNeedToDoRelationshipService;
    @Autowired
    DocumentsServiceImpl documentsService;
    @Autowired
    ProjectsUserController projectsUserController;
    @Autowired
    ProjectsCodeLineController projectsCodeLineController;
    @Autowired
    DocumentsRecordController documentsRecordController;
    @Autowired
    ProjectUserServiceImpl projectUserService;
    @Autowired
    ProjectsUserDutyServiceImpl projectsUserDutyService;
    @Autowired
    ProjectServiceImpl projectService;

    @GetMapping("/projectmessage/{projectId}/{userId}")
    public String projectMessage(@PathVariable("projectId") Integer projectId, @PathVariable("userId") Integer userId, Model model) {
        model.addAttribute("projectId", projectId);
        model.addAttribute("userId", userId);
        return "project/projectmessage";
    }

    @GetMapping("/project_message_person")
    @ResponseBody
    public List<ProjectsMessage> projectUserMessage(Integer projectId, Integer userId, Integer offset, Integer pageSize) {
        return projectsMessageService.getByProjectIdAndUserId(projectId, userId, offset, pageSize);
    }

    @GetMapping("/project_message_project")
    @ResponseBody
    public List<ProjectsMessage> projectsMessages(Integer projectId, Integer offset, Integer pageSize) {
        return projectsMessageService.getByProjectId(projectId, offset, pageSize);
    }

    @GetMapping("/project_message_need_to_do")
    @ResponseBody
    public List<ProjectsMessage> projectMessageNeed_to_do(Integer projectId, Integer offset, Integer pageSize) {
        return projectsMessageService.getAllNeedToByProjectId(projectId, offset, pageSize);
    }

    @GetMapping("/updateProjectMessageIsReadByProjectMessageId")
    @ResponseBody
    public AJaxResponse updateProjectMessageIsReadByProjectMessageId(Integer projectMessageId) {
        boolean result = projectsMessageService.updateProjectMessageIsReadByProjectMessageId(projectMessageId);
        if (result) {
            return AJaxResponse.success("标志所有消息已读");
        } else {
            return AJaxResponse.error(new CustomException(CustomExceptionType.SYSTEM_ERROR, "标志所有消息已读操作失败"));
        }
    }

    public void insertDocumentUpdateProjectMessage(Documents documents) {
        //封装项目消息
        ProjectsMessage projectsMessage = new ProjectsMessage();
        projectsMessage.setProjectId(documents.getProjectId());
        projectsMessage.setTypeId(1);
        projectsMessage.setTime(DateFromatUtil.getNowDate(new Date()));
        projectsMessage.setFromUserId(documents.getUserId());
        MyUserDetails simpleMyUserDetails = userService.getSimpleMyUserDetailsByUserId(documents.getUserId());
        String msg = null;
        if (documents.getVersionFlag() == 1) {
            //不需要审核
            projectsMessage.setAllFlag(1);
            msg = simpleMyUserDetails.getUsername() + "上传了第" + documents.getVersion() + "版本的" + documents.getName() + "。";
            //获取上传者信息
            projectsMessage.setMessage(msg);
            projectsMessageService.insert(projectsMessage);
        } else {
            //需要审核
            projectsMessage.setAdminFlag(1);
            projectsMessage.setNeedToDo(1);
            msg = simpleMyUserDetails.getUsername() + "上传了第" + documents.getVersion() + "版本的" + documents.getName() + ",请尽快审核。";
            //获取上传者信息
            projectsMessage.setMessage(msg);
            projectsMessageService.insert(projectsMessage);
            //建立消息待办联系
            System.out.println(projectsMessage.getId());
            System.out.println(documents.getId());
            ProjectsMessageNeedToDoRelationship projectsMessageNeedToDoRelationship = new ProjectsMessageNeedToDoRelationship();
            projectsMessageNeedToDoRelationship.setProjectMessageId(projectsMessage.getId());
            projectsMessageNeedToDoRelationship.setDocumentId(documents.getId());
            projectsMessageNeedToDoRelationshipService.insert(projectsMessageNeedToDoRelationship);
        }
    }

    @PutMapping("/agreeProjectMessageNeedToDo")
    @ResponseBody
    public AJaxResponse agreeNeedToDo(Integer projectMessageId, Integer userId, HttpServletRequest request) {
        //通过消息id找到关系表
        ProjectsMessageNeedToDoRelationship projectsMessageNeedToDoRelationship = projectsMessageNeedToDoRelationshipService.getByProjectMessageId(projectMessageId);
        //如果是文件审核消息
        if (projectsMessageNeedToDoRelationship.getDocumentId() != 0) {
            //版本号为2的文件,修改文件versionFlag值为2的变为1
            Documents documents2 = documentsService.getById(projectsMessageNeedToDoRelationship.getDocumentId());
            System.out.println(documents2);
            //版本号为1的文件,修改文件versionFlag值为1的变为0
            Documents documents1 = documentsService.getByProjectsIdAndVersionFlagAndName(documents2.getProjectId(), 1, documents2.getName());
            documents1.setVersionFlag(0);
            documentsService.update(documents1);
            documents2.setVersionFlag(1);
            documentsService.update(documents2);
            //获取ip
            String ip = IPUtil.getIpAddress(request);
            //修改代码贡献量
            Integer codeLine = documents2.getCodeLineNumber() - documents1.getCodeLineNumber();
            projectsUserController.update(codeLine, documents2.getUserId(), documents2.getProjectId());
            //插入projectcodeline表
            projectsCodeLineController.insert(documents1.getProjectId());
            //插入日志表
            documentsRecordController.insert(documents2, ip, 2);
            //项目消息处理
            ProjectsMessage projectsMessage = new ProjectsMessage();
            projectsMessage.setProjectId(documents1.getProjectId());
            projectsMessage.setFromUserId(documents2.getUserId());
            projectsMessage.setTypeId(3);
            projectsMessage.setTime(DateFromatUtil.getNowDate(new Date()));
            projectsMessage.setAllFlag(1);
            //获取上传人信息
            MyUserDetails commitUser = userService.getSimpleMyUserDetailsByUserId(documents2.getUserId());
            String msg = commitUser.getUsername() + "上传了" + documents2.getVersion() + "版本的" + documents2.getName();
            projectsMessage.setMessage(msg);
            projectsMessageService.insert(projectsMessage);
            //处理个人消息
            projectsMessage.setFromUserId(userId);
            projectsMessage.setToUserId(documents2.getUserId());
            projectsMessage.setAllFlag(0);
            //获取同意人信息
            ProjectsUser projectsUser = projectUserService.getByUserIdAndProjectId(userId, documents2.getProjectId());
            msg = projectsUser.getProjectsUserDuty().getDutyName() + projectsUser.getMyUserDetails().getUsername() +
                    "同意了你第" + documents2.getVersion() + "版本" + documents2.getName() + "的上传";
            projectsMessage.setMessage(msg);
            projectsMessageService.insert(projectsMessage);
            //设置消息已处理
            ProjectsMessage projectsMessage1 = projectsMessageService.getById(projectMessageId);
            projectsMessage1.setIsRead(0);
            projectsMessageService.update(projectsMessage1);
            Projects projects = projectService.getById(documents2.getUserId());
            projects.setLastUpdateTime(new Date());
            projectService.editProject(projects);
            return AJaxResponse.success("处理完成");
        }
        return null;
    }

    @GetMapping("/projectMessageCount")
    @ResponseBody
    public Integer projectMessageCount(Integer userId, Integer projectId) {
        return projectsMessageService.projectMessageCount(projectId, userId) + projectsMessageService.projectMessageNeedToDoCount(projectId);
    }

    @PutMapping("/doNotAgreeProjectMessageToDo")
    @ResponseBody
    public AJaxResponse doNotagreeProjectMessageToDo(Integer projectMessageId, Integer userId, HttpServletRequest request){
        //通过消息id找到关系表
        ProjectsMessageNeedToDoRelationship projectsMessageNeedToDoRelationship = projectsMessageNeedToDoRelationshipService.getByProjectMessageId(projectMessageId);
        //如果是文件审核消息
        if (projectsMessageNeedToDoRelationship.getDocumentId() != 0) {
            Documents documents2 = documentsService.getById(projectsMessageNeedToDoRelationship.getDocumentId());
            //不同意修改文件，只修改文件versionFlag值2的变为-1
            documents2.setVersionFlag(-1);
            documentsService.update(documents2);
            //获取ip
            String ip = IPUtil.getIpAddress(request);
            documentsRecordController.insert(documents2, ip, 2);
            //项目消息处理
            ProjectsMessage projectsMessage = new ProjectsMessage();
            projectsMessage.setProjectId(documents2.getProjectId());
            projectsMessage.setFromUserId(userId);
            projectsMessage.setToUserId(documents2.getUserId());
            projectsMessage.setTypeId(4);
            projectsMessage.setTime(DateFromatUtil.getNowDate(new Date()));
            //获取拒绝人信息
            ProjectsUser projectsUser = projectUserService.getByUserIdAndProjectId(userId, documents2.getProjectId());
            String msg = projectsUser.getProjectsUserDuty().getDutyName() + projectsUser.getMyUserDetails().getUsername() +
                    "拒绝了你第" + documents2.getVersion() + "版本" + documents2.getName() + "的上传";
            projectsMessage.setMessage(msg);
            projectsMessageService.insert(projectsMessage);
            //设置消息已处理
            projectsMessage = projectsMessageService.getById(projectMessageId);
            projectsMessage.setIsRead(0);
            projectsMessageService.update(projectsMessage);
            Projects projects = projectService.getById(documents2.getProjectId());
            projects.setLastUpdateTime(new Date());
            projectService.editProject(projects);
            return AJaxResponse.success("处理完成");
        }
        return null;
    }
}


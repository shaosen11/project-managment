package cn.edu.lingnan.projectmanagment;

import cn.edu.lingnan.projectmanagment.bean.*;
import cn.edu.lingnan.projectmanagment.controller.DocumentsController;
import cn.edu.lingnan.projectmanagment.controller.ProjectUserController;
import cn.edu.lingnan.projectmanagment.mapper.ProjectsCodeLineMapper;
import cn.edu.lingnan.projectmanagment.service.ProjectsPackageService;
import cn.edu.lingnan.projectmanagment.service.impl.*;
import cn.edu.lingnan.projectmanagment.utils.PathUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class ProjectManagmentApplicationTests {

    @Autowired
    UserRecordServiceImpl userRecordService;

    @Autowired
    ProjectServiceImpl projectService;

    @Autowired
    ProjectsPackageService projectsPackageService;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    ProjectUserServiceImpl projectUserService;

    @Autowired
    ProjectUserController projectUserController;

    @Autowired
    ProjectsCodeLineServiceImpl projectsCodeLineService;

    @Autowired
    DocumentsServiceImpl documentsService;

//    @Test
//    void contextLoads() {
//        UserRecord userRecord = new UserRecord();
//        userRecord.setUserId(1);
//        userRecord.setIp("192.168.1.1");
//        userRecord.setOperateTime(new Date());
//        userRecord.setOperateMassage("登录");
//        userRecordService.insert(userRecord);
//    }
//    @Test
//    void test01(){
//        MyUserDetails byEmail = userService.findByEmail("1244785@qq.com");
//        System.out.println(byEmail);
//    }
//
//    @Test
//    void testGetProjectsIdByUserId(){
//        List<Projects> projectListByUserId = projectService.getProjectListByUserId(1);
//        System.out.println(projectListByUserId);
//    }
//
//    @Test
//    void testGetProjectsPackageListByProjectId(){
//        List<ProjectsPackageList> allPackagesListByProjects = projectsPackageService.getAllPackagesListByProjectId(1);
//        System.out.println(allPackagesListByProjects);
//    }

//    @Test
//    void t2(){
//        ProjectsUser projectsUser = projectUserService.getByUserIdAndProjectId(1,1);
//        System.out.println(projectsUser);
//        projectsUser.setCodeDevoteLine(1);
//        projectsUser.setCodeUpdate(1);
//        projectUserController.update(1,1,1);
//    }

//    @Test
//    void t3(){
//        ProjectsCodeLine projectsCodeLine = new ProjectsCodeLine();
//        projectsCodeLine.setId(1);
//        projectsCodeLine.setProjectsId(1);
//        projectsCodeLine.setCodeLineNumber(12);
//        projectsCodeLineService.update(projectsCodeLine);
//    }
}

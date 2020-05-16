package cn.edu.lingnan.projectmanagment;

import cn.edu.lingnan.projectmanagment.bean.*;
import cn.edu.lingnan.projectmanagment.controller.ProjectsUserController;
import cn.edu.lingnan.projectmanagment.service.ProjectsPackageService;
import cn.edu.lingnan.projectmanagment.service.impl.*;
import cn.edu.lingnan.projectmanagment.utils.DateFromatUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.Date;
import java.util.List;

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
    ProjectsUserController projectsUserController;

    @Autowired
    ProjectsCodeLineServiceImpl projectsCodeLineService;

    @Autowired
    DocumentsServiceImpl documentsService;

    @Autowired
    DocumentsRecordServiceImpl documentsRecordService;

    @Autowired
    MessageServiceImpl messageService;

    @Autowired
    ProjectsFunctionServiceImpl projectsFunctionService;

    @Autowired
    UserCodeUpdateRecordServiceImpl userCodeUpdateRecordService;

    @Autowired
    ProjectsMessageTypeServiceImpl projectsMessageTypeService;

    @Autowired
    ProjectsMessageServiceImpl projectsMessageService;

    @Autowired
    ProjectsUserCooperationServiceImpl projectsUserCooperationService;

    @Autowired
    MessageNeedToDoRelationshipServiceImpl messageNeedToDoRelationshipService;
    @Test
    void t1(){
        String downloadPath = "http://www.projectsmanagment.top/files/projects/1/66ce0a9f59094c869ecfaf262907bc7a-DocumentsController.java";
        System.out.println(downloadPath);
        File file = new File(downloadPath);
        if (file.exists()) {
            System.out.println("文件存在");
        }
    }
}

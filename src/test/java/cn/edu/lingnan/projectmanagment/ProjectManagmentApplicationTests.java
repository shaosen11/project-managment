package cn.edu.lingnan.projectmanagment;

import cn.edu.lingnan.projectmanagment.bean.*;
import cn.edu.lingnan.projectmanagment.controller.ProjectsUserController;
import cn.edu.lingnan.projectmanagment.service.DocumentsRecordService;
import cn.edu.lingnan.projectmanagment.service.ProjectsPackageService;
import cn.edu.lingnan.projectmanagment.service.impl.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

    @Test
    void t1(){
//        List<ProjectsFunction> functionByProjectIdAndRealizeUserId = projectsFunctionService.getFunctionByProjectIdAndRealizeUserId(1, 1, 2);
//        System.out.println(functionByProjectIdAndRealizeUserId);
    }
}

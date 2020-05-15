package cn.edu.lingnan.projectmanagment;

import cn.edu.lingnan.projectmanagment.bean.*;
import cn.edu.lingnan.projectmanagment.controller.ProjectsUserController;
import cn.edu.lingnan.projectmanagment.service.ProjectsPackageService;
import cn.edu.lingnan.projectmanagment.service.impl.*;
import cn.edu.lingnan.projectmanagment.utils.DateFromatUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        List<DocumentsRecord> documentsRecordPageByProjectIdAndUserId = documentsRecordService.getDocumentsRecordPageByProjectIdAndUserId(1, 1, 1, 5);
        System.out.println(documentsRecordPageByProjectIdAndUserId);
    }
}

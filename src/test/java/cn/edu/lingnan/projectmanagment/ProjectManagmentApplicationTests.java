package cn.edu.lingnan.projectmanagment;

import cn.edu.lingnan.projectmanagment.bean.*;
import cn.edu.lingnan.projectmanagment.service.ProjectsPackageService;
import cn.edu.lingnan.projectmanagment.service.impl.*;
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

    @Test
    void contextLoads() {
        UserRecord userRecord = new UserRecord();
        userRecord.setUserId(1);
        userRecord.setIp("192.168.1.1");
        userRecord.setOperateTime(new Date());
        userRecord.setOperateMassage("登录");
        userRecordService.insert(userRecord);
    }
    @Test
    void test01(){
        MyUserDetails byEmail = userService.findByEmail("1244785@qq.com");
        System.out.println(byEmail);
    }

    @Test
    void testGetProjectsIdByUserId(){
        List<Projects> projectListByUserId = projectService.getProjectListByUserId(1);
        System.out.println(projectListByUserId);
    }

    @Test
    void testGetProjectsPackageListByProjectId(){
        List<ProjectsPackageList> allPackagesListByProjects = projectsPackageService.getAllPackagesListByProjectId(1);
        System.out.println(allPackagesListByProjects);
    }
}

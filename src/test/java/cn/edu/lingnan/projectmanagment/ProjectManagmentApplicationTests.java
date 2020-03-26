package cn.edu.lingnan.projectmanagment;

import cn.edu.lingnan.projectmanagment.bean.UserRecord;
import cn.edu.lingnan.projectmanagment.bean.UserRole;
import cn.edu.lingnan.projectmanagment.service.UserRecordService;
import cn.edu.lingnan.projectmanagment.service.impl.UserRecordServiceImpl;
import cn.edu.lingnan.projectmanagment.service.impl.UserRoleServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class ProjectManagmentApplicationTests {

    @Autowired
    UserRecordServiceImpl userRecordService;

    @Autowired
    UserRoleServiceImpl userRoleService;

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
    void testuserRole(){
        UserRole userRole = new UserRole();
        userRole.setUserId(16);
        userRole.setRoleId(1);
        userRoleService.insertUserRole(userRole);
    }


}

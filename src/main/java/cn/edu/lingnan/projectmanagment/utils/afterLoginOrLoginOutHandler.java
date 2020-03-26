package cn.edu.lingnan.projectmanagment.utils;

import cn.edu.lingnan.projectmanagment.bean.MyUserDetails;
import cn.edu.lingnan.projectmanagment.bean.UserRecord;
import cn.edu.lingnan.projectmanagment.service.UserRecordService;
import cn.edu.lingnan.projectmanagment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 18:55 2020/3/26
 */
@Component
public class afterLoginOrLoginOutHandler {
    @Autowired
    UserService userService;

    @Autowired
    UserRecordService userRecordService;

    public void afterLoginOrLoginOutHandler(HttpServletRequest request, String msg){
        //获取myUserDetails对象
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        //设置登录时间
        myUserDetails.setLastLoginTime(new Date());
        userService.updateUser(myUserDetails);
        //存储登录日志
        UserRecord userRecord = new UserRecord();
        userRecord.setUserId(myUserDetails.getId());
        userRecord.setIp(IPUtil.getIP(request));
        userRecord.setOperateTime(new Date());
        userRecord.setOperateMassage(msg);
        userRecordService.insert(userRecord);
        return ;
    }
}

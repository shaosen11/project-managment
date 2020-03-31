package cn.edu.lingnan.projectmanagment.controller;

import cn.edu.lingnan.projectmanagment.bean.Projects;
import cn.edu.lingnan.projectmanagment.service.ProjectService;
import cn.edu.lingnan.projectmanagment.service.impl.ProjectServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 15:43 2020/3/28
 */
@Controller
public class ProjectsUserController {
    @Autowired
    ProjectServiceImpl projectService;

    @GetMapping("user_prjects")
    @ResponseBody
    public List getProjectsByUserId(@Param("userId") Integer userId){
        List<Projects> projectListByUserId = projectService.getProjectListByUserId(userId);
        return projectListByUserId;
    }
}

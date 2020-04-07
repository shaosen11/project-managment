package cn.edu.lingnan.projectmanagment.controller;

import cn.edu.lingnan.projectmanagment.bean.DocumentsRecord;
import cn.edu.lingnan.projectmanagment.bean.MyUserDetails;
import cn.edu.lingnan.projectmanagment.bean.Projects;
import cn.edu.lingnan.projectmanagment.bean.ProjectsUser;
import cn.edu.lingnan.projectmanagment.service.impl.*;
import cn.edu.lingnan.projectmanagment.utils.PathUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProjectController {
    @Autowired
    private ProjectServiceImpl projectService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    ProjectUserServiceImpl projectUserService;

    @Autowired
    ProjectsFunctionServiceImpl projectsFunctionService;

    @Autowired
    DocumentsRecordServiceImpl documentsRecordService;

    //查询一条项目信息
    @GetMapping("/get_by_id")
    public String getById(Integer id,Model model){
        System.out.println("查询项目id:"+id);
        Projects projects=projectService.getById(id);
        if (projects != null){
            model.addAttribute("projectmsg",projects);
            System.out.println(projects);
            return "echarts_test";
        }else {
            model.addAttribute("projectmsg","此项目不存在");
            return "failure";
        }
    }

    //查询所有项目信息
    @GetMapping("/projects_list")
    public String projectList(Model model){
        List<Projects> list = projectService.getProjectList();
        model.addAttribute("projectlist",list);
        System.out.println("查询所有项目"+list);
        return "tables/projectlist";
    }

    @GetMapping("/projects_view")
    public String projectView(@Param("projectId") Integer projectId, Model model){
        System.out.println("项目id " + projectId);
        List<DocumentsRecord> documentsRecordList = documentsRecordService.getAllByProjectId(projectId);
        model.addAttribute("documentsRecordlist", documentsRecordList);
        return "project/projectview";
    }

    //添加项目
    @ResponseBody
    @PostMapping("/add_project")
    public Integer addProject(Projects project){
        System.out.println("待添加项目："+project+" ChargeUserId="+project.getChargeUserId());
        if (userService.findById(project.getChargeUserId()) == null){
            System.out.println("该项目负责人不存在");
            return 1;
        }else{
            project.setSchedule("未开始");
            System.out.println("添加项目："+project);
            Boolean flag = projectService.addProject(project);
            if (flag){
                System.out.println("添加项目成功！");
                return 2;
            }else{
                System.out.println("添加项目失败！");
                return 3;
            }
        }
    }

    //删除项目
    @Transactional
    @ResponseBody
    @PostMapping("/del_project")
    public Boolean deleteProject(Integer id){
        try{
            projectUserService.deleteProjectUserByProjectsId(id);
            projectsFunctionService.deleteProjectFunctionByProjectsId(id);
            projectService.deleteProject(id);
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println("事务回滚，删除失败！");
            return false;
        }
        return true;
    }

    //修改项目信息
    @ResponseBody
    @PostMapping("/edit_project")
    public Integer editProject(Projects project){
        MyUserDetails myUserDetails = userService.findById(project.getChargeUserId());
        if (myUserDetails == null){
            System.out.println("该项目负责人不存在");
            return 1;
        }else{
            System.out.println("editproject项目："+project);
            Boolean flag = projectService.editProject(project);
            if(flag){
                System.out.println("修改项目信息成功");
                return 2;
            }else{
                System.out.println("修改项目信息失败");
                return 3;
            }
        }
    }

    //查询所有注销项目信息
    @GetMapping("/del_project_list")
    public String deletedProjectList(Model model){
        List<Projects> list = projectService.getDelProjectList();
        model.addAttribute("delprojectlist",list);
        System.out.println("查询所有已注销用户"+list);
        return "deleted/deleteproject";
    }

    //还原用户项目
    @ResponseBody
    @PostMapping("/reduction_project")
    public Boolean reductionProject(Integer id){
        Boolean flag =  projectService.reductionProject(id);
        System.out.println("还原项目:"+id+flag);
        if(flag){
            return true;
        }else {
            return false;
        }
    }

    @GetMapping("/newprojectview")
    public String newProjectView(){
        return "project/newprojectview";
    }

    @PostMapping("/projects")
    public String newprojects(Projects projects){
        System.out.println("新建项目:::" + projects);
        //设置项目初始状态
        projects.setSchedule("未开始");
        //插入数据库
        projectService.addProject(projects);
        //查找项目ID
        projects = projectService.getNewProjectByProject(projects.getName(), projects.getChargeUserId(), projects.getCharacterization());
        System.out.println(projects);
        //插入projectuser表
        ProjectsUser projectsUser = new ProjectsUser();
        projectsUser.setProjectsId(projects.getId());
        projectsUser.setUserId(projects.getChargeUserId());
        projectUserService.addProjectUser(projectsUser);
        Map<String,Object> pathMap = new HashMap<>();
        pathMap.put("projectId", projects.getId());
        pathMap.put("userId", projects.getChargeUserId());
        String pathString = PathUtil.pathUtil(pathMap);
        return "redirect:projects_view" + pathString;
    }
}

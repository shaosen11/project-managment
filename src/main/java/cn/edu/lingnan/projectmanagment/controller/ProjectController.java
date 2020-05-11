package cn.edu.lingnan.projectmanagment.controller;

import cn.edu.lingnan.projectmanagment.bean.*;
import cn.edu.lingnan.projectmanagment.service.impl.*;
import cn.edu.lingnan.projectmanagment.utils.PathUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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

    @Autowired
    ProjectsCodeLineServiceImpl projectsCodeLineService;

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
    public String projectView(Integer projectId, Model model){
        System.out.println("项目id " + projectId);
        model.addAttribute("projectId",projectId);
        //获取进度条
        Integer data1 = projectsFunctionService.countProjectFunctionByProjectId(projectId);
        if(data1 == 0){
            model.addAttribute("projectFunctionData",0);
            System.out.println("data1="+data1);
        }else{
            Integer data2 = projectsFunctionService.countByProjectIdAndStatus(projectId,2);
            Integer data3 = projectsFunctionService.countByProjectIdAndStatus(projectId,3);
            Integer projectFunctionData = ((data2+data3)*100)/data1;
            model.addAttribute("projectFunctionData",projectFunctionData);
            System.out.println("data1="+data1+" data2="+data2+" data3="+data3+" projectFunctionData="+projectFunctionData);
        }
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
    public String newProjectView(HttpServletRequest request, Model model){
        //1.从HttpServletRequest中获取SecurityContextImpl对象
        SecurityContextImpl securityContextImpl = (SecurityContextImpl) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        //2.从SecurityContextImpl中获取Authentication对象
        Authentication authentication = securityContextImpl.getAuthentication();
        MyUserDetails userDetails = (MyUserDetails)authentication.getPrincipal();
        System.out.println(userDetails);
        //我所有项目
        List<Myprojects> myprojectsList = userService.getMyProjects(userDetails.getId());
        System.out.println("userid:"+userDetails.getId()+" 我的项目："+myprojectsList);
        model.addAttribute("myprojects",myprojectsList);
        return "project/newprojectview";
    }

    @PostMapping("/projects")
    public String newprojects(Projects projects){
        //设置项目初始状态
        projects.setSchedule("未开始");
        //插入数据库
        projectService.addProject(projects);
        //插入projectuser表
        ProjectsUser projectsUser = new ProjectsUser();
        projectsUser.setProjectsId(projects.getId());
        projectsUser.setUserId(projects.getChargeUserId());
        projectUserService.addProjectUser(projectsUser);
        //插入project_code_line表
        ProjectsCodeLine projectsCodeLine = new ProjectsCodeLine();
        projectsCodeLine.setProjectsId(projects.getId());
        projectsCodeLineService.insert(projectsCodeLine);
        Map<String,Object> pathMap = new HashMap<>();
        pathMap.put("projectId", projects.getId());
        pathMap.put("userId", projects.getChargeUserId());
        String pathString = PathUtil.pathUtil(pathMap);
        return "redirect:projects_view" + pathString;
    }

    @GetMapping("/project")
    @ResponseBody
    public  Projects getProjectByProjectId(Integer projectId){
        return projectService.getById(projectId);
    }
}


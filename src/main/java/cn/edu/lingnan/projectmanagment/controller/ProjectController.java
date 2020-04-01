package cn.edu.lingnan.projectmanagment.controller;

import cn.edu.lingnan.projectmanagment.bean.DocumentsRecord;
import cn.edu.lingnan.projectmanagment.bean.MyUserDetails;
import cn.edu.lingnan.projectmanagment.bean.Projects;
import cn.edu.lingnan.projectmanagment.service.ProjectService;
import cn.edu.lingnan.projectmanagment.service.impl.*;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.util.List;

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
        System.out.println("查询所有用户"+list);
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
    @PostMapping("/add_project")
    public String addProject(Projects project, Model model){
        System.out.println("待添加项目："+project+" ChargeUserId="+project.getChargeUserId());
        if (userService.findById(project.getChargeUserId()) == null){
            model.addAttribute("msg","该项目负责人不存在！");
            System.out.println("该项目负责人不存在");
        }else{
            project.setSchedule("未开始");
            System.out.println("添加项目："+project);
            Boolean flag = projectService.addProject(project);
            if (flag){
                System.out.println("添加项目成功！");
            }else{
                System.out.println("添加项目失败！");
            }
        }
        List<Projects> list = projectService.getProjectList();
        model.addAttribute("projectlist",list);
        System.out.println("查询所有用户"+list);
        return "tables/projectlist";
    }

    //删除项目
    @Transactional
    @PostMapping("/del_project/{id}")
    public String deleteProject(@PathVariable("id")Integer id,Model model){
        try{
            projectUserService.deleteProjectUserByProjectsId(id);
            projectsFunctionService.deleteProjectFunctionByProjectsId(id);
            projectService.deleteProject(id);
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            model.addAttribute("msg","删除失败！");
            System.out.println("事务回滚，删除失败！");
        }
        List<Projects> list = projectService.getProjectList();
        model.addAttribute("projectlist",list);
        System.out.println("查询所有用户"+list);
        return "tables/projectlist";
    }

    //修改项目信息
    @PostMapping("/edit_project")
    public String editProject(Projects project, Model model){
        MyUserDetails myUserDetails = userService.findById(project.getChargeUserId());
        if (myUserDetails == null){
            model.addAttribute("msg","该项目负责人不存在！");
            System.out.println("该项目负责人不存在");
        }else{
            System.out.println("editproject项目："+project);
            Boolean flag = projectService.editProject(project);
            if(flag){
                System.out.println("修改项目信息成功");
            }else{
                System.out.println("修改项目信息失败");
            }
        }
        List<Projects> list = projectService.getProjectList();
        model.addAttribute("projectlist",list);
        System.out.println("查询所有用户"+list);
        return "tables/projectlist";
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
    @PostMapping("/reduction_project/{id}")
    public String reductionProject(@PathVariable("id")Integer id){
        Boolean flag =  projectService.reductionProject(id);
        System.out.println("还原项目:"+id+flag);
        return "redirect:../del_project_list";
    }
}

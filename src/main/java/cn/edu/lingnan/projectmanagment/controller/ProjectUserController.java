package cn.edu.lingnan.projectmanagment.controller;

import cn.edu.lingnan.projectmanagment.bean.MyUserDetails;
import cn.edu.lingnan.projectmanagment.bean.Projects;
import cn.edu.lingnan.projectmanagment.bean.ProjectsUser;
import cn.edu.lingnan.projectmanagment.service.ProjectUserService;
import cn.edu.lingnan.projectmanagment.service.impl.ProjectServiceImpl;
import cn.edu.lingnan.projectmanagment.service.impl.ProjectUserServiceImpl;
import cn.edu.lingnan.projectmanagment.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProjectUserController {
    @Autowired
    private ProjectUserServiceImpl projectUserService;

    @Autowired
    private ProjectServiceImpl projectService;

    @Autowired
    private UserServiceImpl userService;

    //查询所有项目用户信息
    @GetMapping("/project_user_list")
    public String projectUserList(Model model){
        List<ProjectsUser> list = projectUserService.getProjectUserList();
        model.addAttribute("projectuserlist",list);
        System.out.println("查询所有项目用户信息:"+list);
        return "tables/projectuserlist";
    }


    //添加项目
    @PostMapping("/add_project_user")
    public String addProjectUser(ProjectsUser projectsUser, Model model){
        System.out.println("待添加项目用户信息："+projectsUser);
        Projects projects = projectService.getById(projectsUser.getProjectsId());
        MyUserDetails myUserDetails = userService.findById(projectsUser.getUserId());
        if(projects == null){
            model.addAttribute("addmsg","该项目不存在！");
            System.out.println("该项目不存在");
        }
        if (myUserDetails == null){
            model.addAttribute("msg","该用户不存在！");
            System.out.println("该用户不存在！");
        }
        if(projects != null && myUserDetails != null){
            Boolean flag = projectUserService.addProjectUser(projectsUser);
            if (flag){
                System.out.println("添加项目用户信息成功！");
            }else{
                System.out.println("添加项目用户信息失败！");
            }
        }
        List<ProjectsUser> list = projectUserService.getProjectUserList();
        model.addAttribute("projectuserlist",list);
        System.out.println("查询所有项目用户信息:"+list);
        return "tables/projectuserlist";
    }

    //删除项目
    @PostMapping("/del_project_user/{id}")
    public String delProjectUser(@PathVariable("id")Integer id){
        Boolean flag =  projectUserService.deleteProjectUser(id);
        System.out.println("删除项目用户信息:"+ id + flag);
        return "redirect:../project_user_list";
    }

    //修改项目信息
    @PostMapping("/edit_project_user")
    public String editProjectUser(ProjectsUser projectsUser, Model model){
        System.out.println("待修改项目用户信息："+projectsUser);
        Projects projects = projectService.getById(projectsUser.getProjectsId());
        MyUserDetails myUserDetails = userService.findById(projectsUser.getUserId());
        if(projects == null){
            model.addAttribute("addmsg","该项目不存在！");
            System.out.println("该项目不存在");
        }
        if (myUserDetails == null){
            model.addAttribute("msg","该用户不存在！");
            System.out.println("该用户不存在！");
        }
        if(projects != null && myUserDetails != null){
            Boolean flag = projectUserService.editProjectUser(projectsUser);
            if(flag){
                System.out.println("修改项目用户信息成功");
            }else{
                System.out.println("修改项目用户信息失败");
            }
        }
        List<ProjectsUser> list = projectUserService.getProjectUserList();
        model.addAttribute("projectuserlist",list);
        System.out.println("查询所有项目用户信息:"+list);
        return "tables/projectuserlist";
    }

    //查询所有注销项目信息
    @GetMapping("/del_project_user_list")
    public String deletedProjectUserList(Model model){
        List<ProjectsUser> list = projectUserService.getDelProjectUserList();
        model.addAttribute("delprojectuserlist",list);
        System.out.println("查询所有已注销项目用户"+list);
        return "deleted/delprojectuser";
    }

    //还原用户项目
    @PostMapping("/reduction_project_user/{id}")
    public String reductionProjectUser(@PathVariable("id")Integer id){
        Boolean flag =  projectUserService.reductionProjectUser(id);
        System.out.println("还原项目用户:"+id+flag);
        return "redirect:../del_project_user_list";
    }

}
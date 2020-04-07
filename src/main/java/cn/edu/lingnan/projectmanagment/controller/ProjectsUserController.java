package cn.edu.lingnan.projectmanagment.controller;

import cn.edu.lingnan.projectmanagment.bean.Echarts;
import cn.edu.lingnan.projectmanagment.bean.MyUserDetails;
import cn.edu.lingnan.projectmanagment.bean.Projects;
import cn.edu.lingnan.projectmanagment.bean.ProjectsUser;
import cn.edu.lingnan.projectmanagment.service.impl.ProjectServiceImpl;
import cn.edu.lingnan.projectmanagment.service.impl.ProjectUserServiceImpl;
import cn.edu.lingnan.projectmanagment.service.impl.UserServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    private ProjectUserServiceImpl projectUserService;

    @Autowired
    private ProjectServiceImpl projectService;

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("user_prjects")
    @ResponseBody
    public List getProjectsByUserId(@Param("userId") Integer userId){
        List<Projects> projectListByUserId = projectService.getProjectListByUserId(userId);
        return projectListByUserId;
    }

    //查询所有项目用户信息
    @GetMapping("/project_user_list")
    public String projectUserList(Model model){
        List<ProjectsUser> list = projectUserService.getProjectUserList();
        model.addAttribute("projectuserlist",list);
        System.out.println("查询所有项目用户信息:"+list);
        return "tables/projectuserlist";
    }


    //添加项目用户
    @ResponseBody
    @PostMapping("/add_project_user")
    public Integer addProjectUser(ProjectsUser projectsUser){
        System.out.println("待添加项目用户信息："+projectsUser);
        Projects projects = projectService.getById(projectsUser.getProjectsId());
        MyUserDetails myUserDetails = userService.findById(projectsUser.getUserId());
        ProjectsUser projectsUser1 = projectUserService.getByUserIdAndProjectId(projectsUser.getUserId(),projectsUser.getProjectsId());
        if(projects == null){
            System.out.println("该项目不存在");
            return 1;
        }
        else if (myUserDetails == null){
            System.out.println("该用户不存在！");
            return 2;
        }
        else if(projectsUser1 != null){
            System.out.println("项目用户信息已存在！");
            return 4;
        }
        else if(projects != null && myUserDetails != null){
            Boolean flag = projectUserService.addProjectUser(projectsUser);
            if (flag){
                System.out.println("添加项目用户信息成功！");
                return 3;
            }else{
                System.out.println("添加项目用户信息失败！");
                return 5;
            }
        }else{
            return null;
        }
    }

    //删除项目用户
    @ResponseBody
    @PostMapping("/del_project_user")
    public Boolean delProjectUser(Integer id){
        Boolean flag =  projectUserService.deleteProjectUser(id);
        System.out.println("删除项目用户信息:"+ id + flag);
        if (flag){
            return true;
        }else{
            return false;
        }
    }

    //修改项目用户信息
    @ResponseBody
    @PostMapping("/edit_project_user")
    public Integer editProjectUser(ProjectsUser projectsUser){
        System.out.println("待修改项目用户信息："+projectsUser);
        Projects projects = projectService.getById(projectsUser.getProjectsId());
        MyUserDetails myUserDetails = userService.findById(projectsUser.getUserId());
        if(projects == null){
            System.out.println("该项目不存在");
            return 1;
        }
        else if (myUserDetails == null){
            System.out.println("该用户不存在！");
            return 2;
        }
        else if(projects != null && myUserDetails != null){
            ProjectsUser projectsUser1 = projectUserService.getById(projectsUser.getId());
            if(projectsUser1 != null){
                System.out.println("该记录已存在!");
                return 4;
            }else{
                Boolean flag = projectUserService.editProjectUser(projectsUser);
                if(flag){
                    System.out.println("修改项目用户信息成功");
                    return 3;
                }else{
                    System.out.println("修改项目用户信息失败");
                    return 5;
                }
            }
        }else{
            return null;
        }
    }

//    //查询所有注销项目用户信息
//    @GetMapping("/del_project_user_list")
//    public String deletedProjectUserList(Model model){
//        List<ProjectsUser> list = projectUserService.getDelProjectUserList();
//        model.addAttribute("delprojectuserlist",list);
//        System.out.println("查询所有已注销项目用户"+list);
//        return "deleted/delprojectuser";
//    }

    //还原项目用户
    @ResponseBody
    @PostMapping("/reduction_project_user")
    public Integer reductionProjectUser(Integer id){
        ProjectsUser projectsUser = projectUserService.getDelById(id);
        System.out.println("带还原的projectsUser"+projectsUser);
        Projects projects = projectService.getByIdAndNoDel(projectsUser.getProjectsId());
        if (projects == null){
            System.out.println("该项目不存在！");
            return 2;
        }else{
            MyUserDetails myUserDetails = userService.findById(projectsUser.getUserId());
            if(myUserDetails == null){
                System.out.println("该用户不存在！");
                return 3;
            }else{
                Boolean flag =  projectUserService.reductionProjectUser(id);
                System.out.println("还原项目用户:"+id+flag);
                if (flag){
                    return 1;
                }else {
                    return 4;
                }
            }
        }
    }

    //如果codeLine大于0，代码增加
    //等于0，代码不变，上传次数增加
    public String update(Integer codeLine, Integer userId, Integer projectId) {
        System.out.println("代码改变行数：：：" + codeLine);
        //获得人员项目表中的
        ProjectsUser projectsUser = projectUserService.getByUserIdAndProjectId(userId, projectId);
        Projects projects = projectService.getById(projectId);
        //修改个人代码贡献量
        if (codeLine > 0) {
            projectsUser.setCodeDevoteLine(projectsUser.getCodeDevoteLine() + codeLine);
            System.out.println("用户代码增加了" + codeLine);
        }
        //修改代码上传次数
        Integer codeUpdate = projectsUser.getCodeUpdate();
        if(codeUpdate == null){
            projectsUser.setCodeUpdate(1);
        }else {
            projectsUser.setCodeUpdate( codeUpdate + 1);
        }
        System.out.println(projectsUser);
        System.out.println("projectUser修改：" + projectUserService.editProjectUser(projectsUser));
        //修改系统代码量
        projects.setCodeLineNumber(projects.getCodeLineNumber() + codeLine);
        System.out.println("项目代码改变了" + codeLine);
        System.out.println("projects修改：" + projectService.editProject(projects));
        return "success";
    }

    @GetMapping("/getCodeDevoteData")//饼图1
    @ResponseBody
    public List getCodeDevoteData(Integer projectId) {
        List<Echarts> codeDevoteList = projectUserService.getCodeDevote(projectId);
        System.out.println(codeDevoteList);
        return codeDevoteList;
    }

    @GetMapping("/getCodeInsertData")//饼图2
    @ResponseBody
    public List getCodeInsertData(Integer projectId) {
        List<Echarts> codeInsertList = projectUserService.getCodeInsert(projectId);
        System.out.println(codeInsertList);
        return codeInsertList;
    }
}

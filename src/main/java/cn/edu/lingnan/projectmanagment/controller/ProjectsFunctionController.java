package cn.edu.lingnan.projectmanagment.controller;

import cn.edu.lingnan.projectmanagment.bean.Projects;
import cn.edu.lingnan.projectmanagment.bean.ProjectsFunction;
import cn.edu.lingnan.projectmanagment.service.impl.ProjectServiceImpl;
import cn.edu.lingnan.projectmanagment.service.impl.ProjectsFunctionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProjectsFunctionController {
    @Autowired
    private ProjectsFunctionServiceImpl projectsFunctionService;

    @Autowired
    private ProjectServiceImpl projectService;

    //查询所有项目功能点信息
    @GetMapping("/project_function_list")
    public String projectFunctionList(Model model){
        List<ProjectsFunction> list = projectsFunctionService.getProjectFunctionList();
        model.addAttribute("projectsfunctionlist",list);
        System.out.println("查询所有项目功能点信息:"+list);
        return "tables/projectsfunctionlist";
    }


    //添加项目功能点
    @PostMapping("/add_project_function")
    public String addProjectFunction(ProjectsFunction projectsFunction, Model model){
        Projects projects = projectService.getById(projectsFunction.getProjectsId());
        if(projects == null){
            model.addAttribute("addmsg","该项目不存在！");
            System.out.println("该项目不存在");
        }else{
            Integer functionid = projectsFunctionService.findMaxFunctionId(projectsFunction.getProjectsId());
            projectsFunction.setFunctionId(functionid+1);
            Boolean flag = projectsFunctionService.addProjectFunction(projectsFunction);
            System.out.println("添加项目功能点信息："+projectsFunction);
            if (flag){
                System.out.println("添加项目功能点信息成功！");
            }else{
                System.out.println("添加项目功能点信息失败！");
            }
        }
        List<ProjectsFunction> list = projectsFunctionService.getProjectFunctionList();
        model.addAttribute("projectsfunctionlist",list);
        System.out.println("查询所有项目功能点信息:"+list);
        return "tables/projectsfunctionlist";
    }

    //删除项目功能点
    @PostMapping("/del_project_function/{id}")
    public String delProjectFunction(@PathVariable("id")Integer id){
        Boolean flag =  projectsFunctionService.deleteProjectFunction(id);
        System.out.println("删除项目功能点信息:"+ id + flag);
        return "redirect:../project_function_list";
    }

    //修改项目功能点信息
    @PostMapping("/edit_project_function")
    public String editProjectFunction(ProjectsFunction projectsFunction, Model model){
        Projects projects = projectService.getById(projectsFunction.getProjectsId());
        if(projects == null){
            model.addAttribute("addmsg","该项目不存在！");
            System.out.println("该项目不存在");
        }else{
            Integer functionid = projectsFunctionService.findMaxFunctionId(projectsFunction.getProjectsId());
            projectsFunction.setFunctionId(functionid+1);
            Boolean flag = projectsFunctionService.editProjectFunction(projectsFunction);
            System.out.println("添加项目功能点信息："+projectsFunction);
            if (flag){
                System.out.println("修改项目功能点信息成功！");
            }else{
                System.out.println("修改项目功能点信息失败！");
            }
        }
        List<ProjectsFunction> list = projectsFunctionService.getProjectFunctionList();
        model.addAttribute("projectsfunctionlist",list);
        System.out.println("查询所有项目功能点信息:"+list);
        return "tables/projectsfunctionlist";
    }

    //查询所有注销项目功能点信息
    @GetMapping("/del_project_function_list")
    public String deletedProjectFunctionList(Model model){
        List<ProjectsFunction> list = projectsFunctionService.getDelProjectFunctionList();
        model.addAttribute("delprojectfunctionlist",list);
        System.out.println("查询所有已注销项目功能点"+list);
        return "deleted/delprojectfunction";
    }

    //还原项目功能点
    @PostMapping("/reduction_project_function/{id}")
    public String reductionProjectFunction(@PathVariable("id")Integer id){
        Boolean flag =  projectsFunctionService.reductionProjectFunction(id);
        System.out.println("还原项目功能点:"+id+flag);
        return "redirect:../del_project_function_list";
    }
}

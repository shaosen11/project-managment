package cn.edu.lingnan.projectmanagment.controller;

import cn.edu.lingnan.projectmanagment.bean.Projects;
import cn.edu.lingnan.projectmanagment.bean.ProjectsFunction;
import cn.edu.lingnan.projectmanagment.service.impl.ProjectServiceImpl;
import cn.edu.lingnan.projectmanagment.service.impl.ProjectsFunctionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
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
    @Transactional
    @PostMapping("/add_project_function")
    public String addProjectFunction(ProjectsFunction projectsFunction, Model model){
        Projects projects = projectService.getById(projectsFunction.getProjectsId());
        if(projects == null){
            model.addAttribute("addmsg","该项目不存在！");
            System.out.println("该项目不存在");
        }else{
            try{
                Integer functionid = projectsFunctionService.findMaxFunctionId(projectsFunction.getProjectsId());
                if(functionid == null){
                    functionid = 0;
                }
                functionid = functionid+1;
                projectsFunction.setFunctionId(functionid);
                Boolean flag = projectsFunctionService.addProjectFunction(projectsFunction);
                System.out.println("添加项目功能点信息："+projectsFunction);
                if (flag){
                    System.out.println("添加项目功能点信息成功！");
                    Projects projects1 =  projectService.getById(projectsFunction.getProjectsId());
                    projects1.setFunctionPoints(projectsFunctionService.countProjectFunctionByProjectId(projects1.getId()));
                    projectService.editProject(projects1);
                    projectService.updateSchedule(projects1);
                    System.out.println("项目信息："+projects1);
                }else{
                    System.out.println("添加项目功能点信息失败！");
                    model.addAttribute("addmsg","添加项目功能信息失败！");
                }
            }catch (Exception e){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                System.out.println("添加项目功能点信息失败！");
                model.addAttribute("addmsg","添加项目功能信息失败！");
            }
        }
        List<ProjectsFunction> list = projectsFunctionService.getProjectFunctionList();
        model.addAttribute("projectsfunctionlist",list);
        System.out.println("查询所有项目功能点信息:"+list);
        return "tables/projectsfunctionlist";
    }

    //删除项目功能点
    @Transactional
    @PostMapping("/del_project_function/{id}")
    public String delProjectFunction(@PathVariable("id")Integer id,Model model){
        try{
            ProjectsFunction projectsFunction = projectsFunctionService.getOneProjectFunction(id);
            System.out.println("projectsFunction="+projectsFunction);
            Boolean flag =  projectsFunctionService.deleteProjectFunction(id);
            if (flag){
                System.out.println("删除项目功能点信息成功！");
                Projects projects1 =  projectService.getById(projectsFunction.getProjectsId());
                projects1.setFunctionPoints(projectsFunctionService.countProjectFunctionByProjectId(projects1.getId()));
                projectService.editProject(projects1);
                projectService.updateSchedule(projects1);
                System.out.println("项目信息："+projects1);
            }else{
                System.out.println("删除项目功能点信息失败！");
            }
            System.out.println("删除项目功能点信息:"+ id + flag);
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println("事务回滚，删除项目功能点信息失败！");
            model.addAttribute("addmsg","删除项目功能信息失败！");
        }
        List<ProjectsFunction> list = projectsFunctionService.getProjectFunctionList();
        model.addAttribute("projectsfunctionlist",list);
        System.out.println("查询所有项目功能点信息:"+list);
        return "tables/projectsfunctionlist";
    }

    //修改项目功能点信息
    @Transactional
    @PostMapping("/edit_project_function")
    public String editProjectFunction(ProjectsFunction projectsFunction, Model model){
        try{
            Projects projects = projectService.getById(projectsFunction.getProjectsId());
            if(projects == null){
                model.addAttribute("addmsg","该项目不存在！");
                System.out.println("该项目不存在");
            }else {
                ProjectsFunction projectsFunction1 = projectsFunctionService.getOneProjectFunction(projectsFunction.getId());
                //projectsFunction修改后；projectsFunction1修改前
                if (projectsFunction.getProjectsId() == projectsFunction1.getProjectsId()) {//如果修改了项目id
                    Integer functionid = projectsFunctionService.findMaxFunctionId(projectsFunction.getProjectsId());
                    projectsFunction.setFunctionId(functionid + 1);
                    System.out.println("FunctionId=" + projectsFunction.getFunctionId());
                }
                //修改项目功能点信息
                Boolean flag = projectsFunctionService.editProjectFunction(projectsFunction);
                System.out.println("修改项目功能点信息：" + projectsFunction);
                if (flag) {
                    System.out.println("修改项目功能点信息成功！");
                    //如果修改了项目id
                    if (projectsFunction.getProjectsId() != projectsFunction1.getProjectsId()) {
                        //原来的项目的功能点、已完成功能点更新
                        Projects projects1 = projectService.getById(projectsFunction1.getProjectsId());
                        projects1.setFunctionPoints(projectsFunctionService.countProjectFunctionByProjectId(projects1.getId()));
                        projects1.setCompletedFunctionPoints(projectsFunctionService.countCompletedProjectFunctionByProjectId(projects1.getId()));
                        projectService.editProject(projects1);
                        System.out.println("修改前项目信息更新：" + projects1);
                        //更改后的项目的功能点、已完成功能点更新
                        Projects projects2 = projectService.getById(projectsFunction.getProjectsId());
                        projects2.setFunctionPoints(projectsFunctionService.countProjectFunctionByProjectId(projects2.getId()));
                        projects2.setCompletedFunctionPoints(projectsFunctionService.countCompletedProjectFunctionByProjectId(projects2.getId()));
                        projectService.editProject(projects2);
                        System.out.println("修改后项目信息更新：" + projects2);
                        projectService.updateSchedule(projects1);
                        projectService.updateSchedule(projects2);
                    } else {
                        Projects projects2 = projectService.getById(projectsFunction.getProjectsId());
                        projects2.setCompletedFunctionPoints(projectsFunctionService.countCompletedProjectFunctionByProjectId(projects2.getId()));
                        projectService.editProject(projects2);
                        projectService.updateSchedule(projects2);
                        System.out.println("修改后项目信息更新：" + projects2);
                    }
                } else {
                    System.out.println("修改项目功能点信息失败！");
                }
            }
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println("事务回滚，修改项目功能点信息失败！");
            model.addAttribute("addmsg","修改项目功能点信息失败！");
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
    @Transactional
    @PostMapping("/reduction_project_function/{id}")
    public String reductionProjectFunction(@PathVariable("id")Integer id,Model model){
        try{
            Boolean flag =  projectsFunctionService.reductionProjectFunction(id);
            if (flag){
                System.out.println("还原项目功能点信息成功！");
                ProjectsFunction projectsFunction = projectsFunctionService.getOneProjectFunction(id);
                System.out.println("projectsFunction="+projectsFunction);
                Projects projects1 =  projectService.getById(projectsFunction.getProjectsId());
                projects1.setFunctionPoints(projectsFunctionService.countProjectFunctionByProjectId(projects1.getId()));
                projectService.editProject(projects1);
                projectService.updateSchedule(projects1);
                System.out.println("项目信息："+projects1);
            }else{
                System.out.println("还原项目功能点信息失败！");
            }
            System.out.println("还原项目功能点:"+id+flag);
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println("事务回滚，还原项目功能点信息失败！");
            model.addAttribute("msg","还原项目功能点信息失败！");
        }
        List<ProjectsFunction> list = projectsFunctionService.getDelProjectFunctionList();
        model.addAttribute("delprojectfunctionlist",list);
        System.out.println("查询所有已注销项目功能点"+list);
        return "deleted/delprojectfunction";
    }
}

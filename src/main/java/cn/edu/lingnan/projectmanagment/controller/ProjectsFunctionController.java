package cn.edu.lingnan.projectmanagment.controller;

import cn.edu.lingnan.projectmanagment.bean.*;
import cn.edu.lingnan.projectmanagment.service.impl.ProjectServiceImpl;
import cn.edu.lingnan.projectmanagment.service.impl.ProjectsFunctionServiceImpl;
import cn.edu.lingnan.projectmanagment.service.impl.UserServiceImpl;
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

import java.util.List;

@Controller
public class ProjectsFunctionController {
    @Autowired
    private ProjectsFunctionServiceImpl projectsFunctionService;

    @Autowired
    private ProjectServiceImpl projectService;

    @Autowired
    private UserServiceImpl userService;

    //查询所有项目功能点信息
    @GetMapping("/project_function_list")
    public String projectFunctionList(Model model) {
        List<ProjectsFunction> list = projectsFunctionService.getProjectFunctionList();
        model.addAttribute("projectsfunctionlist", list);
        System.out.println("查询所有项目功能点信息:" + list);
        return "tables/projectsfunctionlist";
    }


    //添加项目功能点
    @Transactional
    @ResponseBody
    @PostMapping("/add_project_function")
    public Integer addProjectFunction(ProjectsFunction projectsFunction) {
        Projects projects = projectService.getById(projectsFunction.getProjectsId());
        System.out.println("projectsFunction=" + projectsFunction);
        if (projects == null) {
            System.out.println("该项目不存在");
            return 2;
        } else {
            if (projectsFunction.getPublishUserId() != null) {
                MyUserDetails myUserDetails = userService.findById(projectsFunction.getPublishUserId());
                if (myUserDetails == null) {
                    System.out.println("发布用户不存在！");
                    return 3;
                } else {
                    if (projectsFunction.getRealizeUserId() != null) {
                        MyUserDetails myUserDetails2 = userService.findById(projectsFunction.getRealizeUserId());
                        if (myUserDetails2 == null) {
                            System.out.println("实现用户不存在！");
                            return 4;
                        }
                    }

                }
            }
            try {
                Integer functionid = projectsFunctionService.findMaxFunctionId(projectsFunction.getProjectsId());
                if (functionid == null) {
                    functionid = 0;
                }
                functionid = functionid + 1;
                projectsFunction.setFunctionId(functionid);
                Boolean flag = projectsFunctionService.addProjectFunction(projectsFunction);
                System.out.println("添加项目功能点信息：" + projectsFunction);
                if (flag) {
                    System.out.println("添加项目功能点信息成功！");
                    Projects projects1 = projectService.getById(projectsFunction.getProjectsId());
                    projects1.setFunctionPoints(projectsFunctionService.countProjectFunctionByProjectId(projects1.getId()));
                    projectService.editProject(projects1);
                    projectService.updateSchedule(projects1);
                    System.out.println("项目信息：" + projects1);
                    return 1;
                } else {
                    System.out.println("添加项目功能点信息失败！");
                    return 5;
                }
            } catch (Exception e) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                System.out.println("添加项目功能点信息失败！");
                return 5;
            }
        }
    }

    //删除项目功能点
    @Transactional
    @ResponseBody
    @PostMapping("/del_project_function")
    public Boolean delProjectFunction(Integer id) {
        try {
            ProjectsFunction projectsFunction = projectsFunctionService.getOneProjectFunction(id);
            System.out.println("projectsFunction=" + projectsFunction);
            Boolean flag = projectsFunctionService.deleteProjectFunction(id);
            System.out.println("删除项目功能点信息:" + id + flag);
            if (flag) {
                System.out.println("删除项目功能点信息成功！");
                Projects projects1 = projectService.getById(projectsFunction.getProjectsId());
                projects1.setFunctionPoints(projectsFunctionService.countProjectFunctionByProjectId(projects1.getId()));
                int count = projectsFunctionService.countByProjectIdAndStatus(projects1.getId(), 2) + projectsFunctionService.countByProjectIdAndStatus(projects1.getId(), 3);
                projects1.setCompletedFunctionPoints(count);
                projectService.editProject(projects1);
                projectService.updateSchedule(projects1);
                System.out.println("项目信息：" + projects1);
                return true;
            } else {
                System.out.println("删除项目功能点信息失败！");
                return false;
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println("事务回滚，删除项目功能点信息失败！");
            return false;
        }
    }

    //修改项目功能点信息
    @Transactional
    @ResponseBody
    @PostMapping("/edit_project_function")
    public Integer editProjectFunction(ProjectsFunction projectsFunction) {
        try {
            Projects projects = projectService.getById(projectsFunction.getProjectsId());
            if (projects == null) {
                System.out.println("该项目不存在");
                return 2;
            } else {
                if (projectsFunction.getPublishUserId() != null) {
                    MyUserDetails myUserDetails = userService.findById(projectsFunction.getPublishUserId());
                    if (myUserDetails == null) {
                        System.out.println("发布用户不存在！");
                        return 3;
                    } else {
                        if (projectsFunction.getRealizeUserId() != null) {
                            MyUserDetails myUserDetails2 = userService.findById(projectsFunction.getRealizeUserId());
                            if (myUserDetails2 == null) {
                                System.out.println("实现用户不存在！");
                                return 4;
                            }
                        }
                    }
                }
                ProjectsFunction projectsFunction1 = projectsFunctionService.getOneProjectFunction(projectsFunction.getId());
                //projectsFunction修改后；projectsFunction1修改前
                if (projectsFunction.getProjectsId() != projectsFunction1.getProjectsId()) {//如果修改了项目id
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
                        int count = projectsFunctionService.countByProjectIdAndStatus(projects1.getId(), 2) + projectsFunctionService.countByProjectIdAndStatus(projects1.getId(), 3);
                        projects1.setCompletedFunctionPoints(count);
                        projectService.editProject(projects1);
                        System.out.println("修改前项目信息更新：" + projects1);
                        //更改后的项目的功能点、已完成功能点更新
                        Projects projects2 = projectService.getById(projectsFunction.getProjectsId());
                        projects2.setFunctionPoints(projectsFunctionService.countProjectFunctionByProjectId(projects2.getId()));
                        int count2 = projectsFunctionService.countByProjectIdAndStatus(projects2.getId(), 2) + projectsFunctionService.countByProjectIdAndStatus(projects2.getId(), 3);
                        projects2.setCompletedFunctionPoints(count2);
                        projectService.editProject(projects2);
                        System.out.println("修改后项目信息更新：" + projects2);
                        projectService.updateSchedule(projects1);
                        projectService.updateSchedule(projects2);
                    } else {
                        Projects projects2 = projectService.getById(projectsFunction.getProjectsId());
                        int count2 = projectsFunctionService.countByProjectIdAndStatus(projects2.getId(), 2) + projectsFunctionService.countByProjectIdAndStatus(projects2.getId(), 3);
                        projects2.setCompletedFunctionPoints(count2);
                        projectService.editProject(projects2);
                        projectService.updateSchedule(projects2);
                        System.out.println("修改后项目信息更新：" + projects2);
                    }
                    return 1;
                } else {
                    System.out.println("修改项目功能点信息失败！");
                    return 3;
                }
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println("事务回滚，修改项目功能点信息失败！");
            return 3;
        }
    }

    //查询所有注销项目功能点信息
    @GetMapping("/del_project_function_list")
    public String deletedProjectFunctionList(Model model) {
        List<ProjectsFunction> list = projectsFunctionService.getDelProjectFunctionList();
        model.addAttribute("delprojectfunctionlist", list);
        System.out.println("查询所有已注销项目功能点" + list);
        return "deleted/delprojectfunction";
    }

    //还原项目功能点
    @Transactional
    @ResponseBody
    @PostMapping("/reduction_project_function")
    public Integer reductionProjectFunction(Integer id) {
        try {
            ProjectsFunction projectsFunction = projectsFunctionService.getById(id);
            Projects projects = projectService.getByIdAndNoDel(projectsFunction.getProjectsId());
            if (projects == null) {
                System.out.println("该项目不存在！");
                return 2;
            } else {
                Boolean flag = projectsFunctionService.reductionProjectFunction(id);
                System.out.println("还原项目功能点:" + id + flag);
                if (flag) {
                    System.out.println("还原项目功能点信息成功！");
                    System.out.println("projectsFunction=" + projectsFunction);
                    projects.setFunctionPoints(projectsFunctionService.countProjectFunctionByProjectId(projects.getId()));
                    int count = projectsFunctionService.countByProjectIdAndStatus(projects.getId(), 2) + projectsFunctionService.countByProjectIdAndStatus(projects.getId(), 3);
                    projects.setCompletedFunctionPoints(count);
                    projectService.editProject(projects);
                    projectService.updateSchedule(projects);
                    System.out.println("项目信息：" + projects);
                    return 1;
                } else {
                    System.out.println("还原项目功能点信息失败！");
                    return 3;
                }
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println("事务回滚，还原项目功能点信息失败！");
            return 3;
        }
    }

    @GetMapping("/user_finish_function")
    @ResponseBody
    public List userFinishFunction(Integer projectId, Integer userId) {
        List<ProjectsFunction> functionByProjectIdAndRealizeUserId1 = projectsFunctionService.getFunctionByProjectIdAndRealizeUserId(projectId, userId, 2);
        List<ProjectsFunction> functionByProjectIdAndRealizeUserId2 = projectsFunctionService.getFunctionByProjectIdAndRealizeUserId(projectId, userId, 3);
        functionByProjectIdAndRealizeUserId1.addAll(functionByProjectIdAndRealizeUserId2);
        return functionByProjectIdAndRealizeUserId1;
    }

    @GetMapping("/user_developing_function")
    @ResponseBody
    public List userDevelopingFunction(Integer projectId, Integer userId) {
        return projectsFunctionService.getFunctionByProjectIdAndRealizeUserId(projectId, userId, 1);
    }
}


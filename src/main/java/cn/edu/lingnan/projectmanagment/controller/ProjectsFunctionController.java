package cn.edu.lingnan.projectmanagment.controller;

import cn.edu.lingnan.projectmanagment.bean.*;
import cn.edu.lingnan.projectmanagment.exception.AJaxResponse;
import cn.edu.lingnan.projectmanagment.service.impl.*;
import cn.edu.lingnan.projectmanagment.utils.DateFromatUtil;
import cn.edu.lingnan.projectmanagment.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProjectsFunctionController {
    @Autowired
    private ProjectsFunctionServiceImpl projectsFunctionService;

    @Autowired
    private ProjectServiceImpl projectService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ProjectUserServiceImpl projectUserService;

    @Autowired
    private ProjectsMessageServiceImpl projectsMessageService;

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
        System.out.println(projectsFunction);
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
    public Boolean delProjectFunction(Integer id, String reason) {
        try {
            ProjectsFunction projectsFunction = projectsFunctionService.getOneProjectFunction(id);
            projectsFunction.setDelReason(reason);
            projectsFunctionService.editProjectFunction(projectsFunction);
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

    @GetMapping("/project_function_view/{projectId}")
    public String projectView(@PathVariable("projectId") Integer projectId, Model model) {
        Projects projects = projectService.getById(projectId);
        model.addAttribute("project", projects);
        model.addAttribute("projectId", projectId);
        return "project/projectfunctionview";
    }

    @GetMapping("/projectFunctionDataCicleChart")
    @ResponseBody
    public Integer projectFunctionData(Integer projectId) {
        Integer data1 = projectsFunctionService.countProjectFunctionByProjectId(projectId);
        if (data1 == 0) {
            return 0;
        } else {
            Integer data2 = projectsFunctionService.countByProjectIdAndStatus(projectId, 2);
            Integer data3 = projectsFunctionService.countByProjectIdAndStatus(projectId, 3);
            Integer projectFunctionData = ((data2 + data3) * 100) / data1;
            return projectFunctionData;
        }
    }

    @GetMapping("/projectFunctionTotal")
    @ResponseBody
    public Object projectFunctionTotal(Integer projectId) {
        Integer data0 = projectsFunctionService.countByProjectIdAndStatus(projectId, 0);
        Integer data1 = projectsFunctionService.countByProjectIdAndStatus(projectId, 1);
        Integer data2 = projectsFunctionService.countByProjectIdAndStatus(projectId, 2);
        Integer data3 = projectsFunctionService.countByProjectIdAndStatus(projectId, 3);
        Integer data4 = projectsFunctionService.countDelByProjectId(projectId);
        Map<String, Integer> map = new HashMap<>();
        map.put("data0", data0);
        map.put("data1", data1);
        map.put("data2", data2);
        map.put("data3", data3);
        map.put("data4", data4);
        return map;
    }

    //获取项目的所有功能点
    @GetMapping("/project_function")
    @ResponseBody
    public AJaxResponse myProjectsJoin(Integer projectId) {
        List<ProjectsFunction> list = projectsFunctionService.getAllFunctionByProjectId(projectId);
        return AJaxResponse.success(list);
//        map.put("list", list);
//        Map<String, Object> map = new HashMap<String, Object>();
//        try {
//            ProjectsUser projectsUser = projectUserService.getByUserIdAndProjectId(userId, projectId);
//            if (projectsUser.getDutyCode() == 3) {
//                map.put("duty", "false");
//            } else {
//                map.put("duty", "true");
//                List<ProjectsFunction> list = projectsFunctionService.getAllFunctionByProjectId(projectId);
//                System.out.println("获得项目功能点信息:" + list);
//                // 封装数据，并返回
//                map.put("list", list);
//                System.out.println("map" + map);
//            }
//            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
//        } catch (Exception e) {
//            System.out.println("获取数据失败" + e);
//            return new ResponseEntity<Map<String, Object>>(
//                    HttpStatus.INTERNAL_SERVER_ERROR);
//        }
    }

    @GetMapping("/projects_plan_view/{projectId}")
    public String projectPlanView(@PathVariable("projectId") Integer projectId, Model model) {
        Projects projects = projectService.getById(projectId);
        model.addAttribute("project", projects);
        model.addAttribute("projectId", projectId);
        return "project/projectplanview";
    }

    public Map<String, Object> functionPageCom(Integer page, Integer count) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 每页显示条数
        int pageSize = 5;
        try {
            // 计算总页数
            int totalPage = count / pageSize;
            // 不满一页的数据按一页计算
            if (count % pageSize != 0) {
                totalPage++;
            }
            // 当页数大于总页数，直接返回
            if (page > totalPage) {
                return null;
            }
            // 计算sql需要的起始索引
            int offset = (page - 1) * pageSize;
            // 封装数据，并返回
            map.put("page", page);
            map.put("pageSize", pageSize);
            map.put("totalPage", totalPage);
            map.put("offset", offset);
            return map;
        } catch (Exception e) {
            System.out.println("获取函数数据失败" + e);
            return map;
        }
    }

    //获取项目的所有功能点计划
    @GetMapping("/projects_plan")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> projectsPlan(Integer page, Integer projectId) {
        System.out.println("当前页：" + page + "  项目id" + projectId);
        Map<String, Object> map = new HashMap<String, Object>();
        // 每页显示条数
        int pageSize = 5;
        try {
            // 获取总条目数
            List<ProjectsFunction> projectsFunctionList = projectsFunctionService.getProjectPlanFunctions(projectId);
            System.out.println(projectsFunctionList);
            int count = projectsFunctionList.size();
            map = functionPageCom(page, count);
            int offset = (int) map.get("offset");
//            int offset = (page - 1) * pageSize;
            System.out.println(offset + "========================");
            // 根据起始索引和页面大小去查询数据
            List<ProjectsFunction> list = projectsFunctionService.getProjectPlanFunctionsPage(projectId, offset, pageSize);
            System.out.println("分页list" + list);
            // 封装数据，并返回
            map.put("list", list);
            System.out.println("分页map" + map);
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("获取分页数据失败" + e);
            return new ResponseEntity<Map<String, Object>>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    //获取项目计划的项目时间
    @GetMapping("/projects_plan_time")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> projectsPlanTime(Integer projectId) {
        System.out.println(" 项目id=" + projectId);
        Map<String, Object> map = new HashMap<String, Object>();
        // 每页显示条数
        int pageSize = 5;
        try {
            Projects projects = projectService.getById(projectId);
            System.out.println("获得项目信息:" + projects);
            map.put("projects", projects);
            System.out.println("分页map" + map);
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("获取数据失败" + e);
            return new ResponseEntity<Map<String, Object>>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/projectFunctionMessageAlert")
    @ResponseBody
    public AJaxResponse projectFunctionMessageAlert(Integer functionId, Integer projectId, HttpServletRequest request) {
        MyUserDetails myUserDetails = UserUtil.getMyUserDetailsBySecurity(request);
        if (myUserDetails != null) {
            //项目是否存在此人
            ProjectsUser projectsUser = projectUserService.getByUserIdAndProjectId(myUserDetails.getId(), projectId);
            if (projectsUser != null) {
                //查询是否管理员或者功能点发布者
                if (projectsUser.getDutyCode() == 3) {
                    return AJaxResponse.error("你不是项目管理员，不可操作！");
                }
                //查找出项目功能点
                ProjectsFunction function = projectsFunctionService.getById(functionId);
                System.out.println(function);
                //封装信息
                ProjectsMessage projectsMessage = new ProjectsMessage();
                projectsMessage.setProjectId(projectId);
                projectsMessage.setFromUserId(myUserDetails.getId());
                projectsMessage.setToUserId(function.getRealizeUserId());
                projectsMessage.setTypeId(16);
                projectsMessage.setTime(DateFromatUtil.getNowDate(new Date()));
                String msg = projectsUser.getProjectsUserDuty().getDutyName() + projectsUser.getMyUserDetails().getUsername()
                        + "提醒你尽快完成功能点" + function.getFunctionName();
                projectsMessage.setMessage(msg);
                System.out.println(projectsMessage);
                projectsMessageService.insert(projectsMessage);
                return AJaxResponse.success("提醒成功！");
            } else {
                return AJaxResponse.error("你不是管理员，没有权限！");
            }
        } else {
            return AJaxResponse.error("请先登录！");
        }
    }

    @GetMapping("/projectFunction")
    @ResponseBody
    public ProjectsFunction projectFunctionById(Integer id){
        System.out.println(id);
        ProjectsFunction projectsFunction = projectsFunctionService.getById(id);
        System.out.println(projectsFunction);
        return projectsFunction;
    }

    @PutMapping("/projectFunction")
    @ResponseBody
    public AJaxResponse projectFunctionById(ProjectsFunction projectsFunction){
        projectsFunctionService.editProjectFunction(projectsFunction);
        return AJaxResponse.success();
    }

    @GetMapping("/project_function_detail_view/{projectFunctionId}")
    public String projectFunctionDetailView(@PathVariable("projectFunctionId") Integer projectFunctionId, Model model,HttpServletRequest request){
        ProjectsFunction projectsFunction = projectsFunctionService.getById(projectFunctionId);
        Projects project = projectService.getById(projectsFunction.getProjectsId());
        MyUserDetails user = UserUtil.getMyUserDetailsBySecurity(request);
        Integer role = 0;
        if(user != null){
            ProjectsUser projectsUser = projectUserService.getByUserIdAndProjectId(user.getId(),project.getId());
            if(projectsUser.getDutyCode() != 3){//项目负责人或者管理员
                role = 1;
            }else if(user.getId() == projectsFunction.getRealizeUserId()){//功能点实现者
                role = 2;
            }
        }
        model.addAttribute("projectsFunction", projectsFunction);
        model.addAttribute("project", project);
        model.addAttribute("projectId", projectsFunction.getProjectsId());
        model.addAttribute("role",role);
        return "project/projectfunctiondetailview";
    }

    @GetMapping("/project_function_test")
    public String projectFunctionTest() {
        return "page2/widgets2";
    }

}


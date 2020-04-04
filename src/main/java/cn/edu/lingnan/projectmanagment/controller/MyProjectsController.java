package cn.edu.lingnan.projectmanagment.controller;

import cn.edu.lingnan.projectmanagment.bean.*;
import cn.edu.lingnan.projectmanagment.service.impl.ProjectServiceImpl;
import cn.edu.lingnan.projectmanagment.service.impl.UserLikeServiceImpl;
import cn.edu.lingnan.projectmanagment.service.impl.UserServiceImpl;
import cn.edu.lingnan.projectmanagment.service.impl.UserStoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MyProjectsController {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserStoreServiceImpl userStoreService;

    @Autowired
    private ProjectServiceImpl projectService;

    @Autowired
    private UserLikeServiceImpl userLikeService;

    //查看我的项目
    @GetMapping("/my_projects/{id}")
    public String myProjects(@PathVariable("id")Integer id, Model model){
        //我所有项目
        List<Myprojects> myprojectsList = userService.getMyProjects(id);
        System.out.println("userid:"+id+" 我的项目："+myprojectsList);
        model.addAttribute("myprojects",myprojectsList);
        //我负责的项目
        List<Myprojects> mychargeprojectsList = userService.getMyChargeProjects(id);
        System.out.println("userid:"+id+" 我负责的项目："+mychargeprojectsList);
        model.addAttribute("mychargeprojects",mychargeprojectsList);
        //我参加的项目
        List<Myprojects> myjoinprojectsList = userService.getMyJoinProjects(id);
        System.out.println("userid:"+id+" 我参与的项目："+myjoinprojectsList);
        model.addAttribute("myjoinprojects",myjoinprojectsList);
        return "myprojects";
    }

    //我的项目--饼图1
    @PostMapping("/getMyProjectData/{id}")
    @ResponseBody
    public List<Echarts> getMyProjectData(@PathVariable("id")Integer id) {
        List<Echarts> list1 = new ArrayList<>();
        Integer num1 = userService.myProjectScheduleNum(id,"进行中");
        Integer num2 = userService.myProjectScheduleNum(id,"未开始");
        Integer num3 = userService.myProjectScheduleNum(id,"已完成");
        System.out.println("list1:: num1="+num1+" num2="+num2+" num3="+num3);
        list1.add(new Echarts("进行中",num1));
        list1.add(new Echarts("未开始",num2));
        list1.add(new Echarts("已完成",num3));
        System.out.println("getMyProjectData数据:"+list1);
        return list1;
    }

    //我的项目--饼图2
    @PostMapping("/getMyProjectData2/{id}")
    @ResponseBody
    public List<Echarts> getMyProjectData2(@PathVariable("id")Integer id) {
        List<Echarts> list2 = new ArrayList<>();
        Integer num1 = userService.myProjectScheduleNum2(id,"进行中");
        Integer num2 = userService.myProjectScheduleNum2(id,"未开始");
        Integer num3 = userService.myProjectScheduleNum2(id,"已完成");
        System.out.println("list2:: num1="+num1+" num2="+num2+" num3="+num3);
        list2.add(new Echarts("进行中",num1));
        list2.add(new Echarts("未开始",num2));
        list2.add(new Echarts("已完成",num3));
        System.out.println("getMyProjectData2数据:"+list2);
        return list2;
    }

    //我的项目--饼图3
    @PostMapping("/getMyProjectData3/{id}")
    @ResponseBody
    public List<Echarts> getMyProjectData3(@PathVariable("id")Integer id) {
        List<Echarts> list3 = new ArrayList<>();
        Integer num1 = userService.myProjectScheduleNum3(id,"进行中");
        Integer num2 = userService.myProjectScheduleNum3(id,"未开始");
        Integer num3 = userService.myProjectScheduleNum3(id,"已完成");
        System.out.println("list3:: num1="+num1+" num2="+num2+" num3="+num3);
        list3.add(new Echarts("进行中",num1));
        list3.add(new Echarts("未开始",num2));
        list3.add(new Echarts("已完成",num3));
        System.out.println("getMyProjectData3数据:"+list3);
        return list3;
    }

    //我的项目--收藏
    @GetMapping("/my_project_refrech/{userId}/{projectId}")
    public String myProjectsRefrech(@PathVariable("userId")Integer id,@PathVariable("projectId")Integer projectId,Model model){
        UserStore userStore = userStoreService.getOneUserStore(id,projectId);
        if(userStore == null){
            UserStore userStore1 = new UserStore();
            userStore1.setUserId(id);
            userStore1.setProjectsId(projectId);
            System.out.println("添加UserStore"+userStore1);
            userStoreService.addUserStore(userStore1);
        }else{
            userStoreService.reductionUserStore(id,projectId);
        }
        Integer storenum = userStoreService.countProjectBeStored(projectId);
        Projects projects = projectService.getById(projectId);
        projects.setStoreCount(storenum);
        System.out.println("收藏项目"+projects);
        projectService.editProject(projects);
        return "redirect:/my_projects/"+id;
    }

    //我的项目--取消收藏
    @GetMapping("/my_project_del_refrech/{userId}/{projectId}")
    public String myProjectsDelRefrech(@PathVariable("userId")Integer id,@PathVariable("projectId")Integer projectId,Model model){
        userStoreService.deleteUserStore(id,projectId);
        Integer storenum = userStoreService.countProjectBeStored(projectId);
        Projects projects = projectService.getById(projectId);
        projects.setStoreCount(storenum);
        System.out.println("取消收藏项目"+projects);
        projectService.editProject(projects);
        return "redirect:/my_projects/"+id;
    }

    //我的项目--点赞
    @GetMapping("/my_project_refrech_like/{userId}/{projectId}")
    public String myProjectsRefrechLike(@PathVariable("userId")Integer id,@PathVariable("projectId")Integer projectId,Model model){
        UserLike userLike = userLikeService.getOneUserLike(id,projectId);
        if(userLike == null){
            UserLike userLike1 = new UserLike();
            userLike1.setUserId(id);
            userLike1.setProjectsId(projectId);
            System.out.println("添加UserStore"+userLike1);
            userLikeService.addUserLike(userLike1);
        }else{
            userLikeService.reductionUserLike(id,projectId);
        }
        Integer storenum = userLikeService.countProjectBeLiked(projectId);
        Projects projects = projectService.getById(projectId);
        projects.setLikeCount(storenum);
        System.out.println("点赞项目"+projects);
        projectService.editProject(projects);
        return "redirect:/my_projects/"+id;
    }

    //我的项目--取消点赞
    @GetMapping("/my_project_del_refrech_like/{userId}/{projectId}")
    public String myProjectsDelRefrechLike(@PathVariable("userId")Integer id,@PathVariable("projectId")Integer projectId,Model model){
        userLikeService.deleteUserLike(id,projectId);
        Integer likenum = userLikeService.countProjectBeLiked(projectId);
        Projects projects = projectService.getById(projectId);
        projects.setLikeCount(likenum);
        System.out.println("取消点赞项目"+projects);
        projectService.editProject(projects);
        return "redirect:/my_projects/"+id;
    }

    //查看我的项目收藏
    @GetMapping("/my_projects_store/{id}")
    public String myProjectsStore(@PathVariable("id")Integer id, Model model){
        List<Myprojects> myprojectstores = userService.getMyProjectsStore(id);
        System.out.println("userid:"+id+" 我的项目收藏："+myprojectstores);
        model.addAttribute("myprojectstores",myprojectstores);
        return "mystore";
    }

    //我的收藏--取消收藏
    @GetMapping("/my_project_store_del/{userId}/{projectId}")
    public String myProjectsStoreDel(@PathVariable("userId")Integer id,@PathVariable("projectId")Integer projectId,Model model){
        userStoreService.deleteUserStore(id,projectId);
        Integer storenum = userStoreService.countProjectBeStored(projectId);
        Projects projects = projectService.getById(projectId);
        projects.setStoreCount(storenum);
        System.out.println("取消收藏项目"+projects);
        projectService.editProject(projects);
        return "redirect:/my_projects_store/"+id;
    }

    //我的收藏--点赞
    @GetMapping("/my_project_store_like/{userId}/{projectId}")
    public String myProjectsStoreLike(@PathVariable("userId")Integer id,@PathVariable("projectId")Integer projectId,Model model){
        UserLike userLike = userLikeService.getOneUserLike(id,projectId);
        if(userLike == null){
            UserLike userLike1 = new UserLike();
            userLike1.setUserId(id);
            userLike1.setProjectsId(projectId);
            System.out.println("添加UserStore"+userLike1);
            userLikeService.addUserLike(userLike1);
        }else{
            userLikeService.reductionUserLike(id,projectId);
        }
        Integer storenum = userLikeService.countProjectBeLiked(projectId);
        Projects projects = projectService.getById(projectId);
        projects.setLikeCount(storenum);
        System.out.println("点赞项目"+projects);
        projectService.editProject(projects);
        return "redirect:/my_projects_store/"+id;
    }

    //我的收藏--取消点赞
    @GetMapping("/my_project_store_del_like/{userId}/{projectId}")
    public String myProjectsStoreDelLike(@PathVariable("userId")Integer id,@PathVariable("projectId")Integer projectId,Model model){
        userLikeService.deleteUserLike(id,projectId);
        Integer likenum = userLikeService.countProjectBeLiked(projectId);
        Projects projects = projectService.getById(projectId);
        projects.setLikeCount(likenum);
        System.out.println("取消点赞项目"+projects);
        projectService.editProject(projects);
        return "redirect:/my_projects_store/"+id;
    }
}

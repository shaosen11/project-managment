package cn.edu.lingnan.projectmanagment.controller;

import cn.edu.lingnan.projectmanagment.bean.*;
import cn.edu.lingnan.projectmanagment.service.impl.ProjectServiceImpl;
import cn.edu.lingnan.projectmanagment.service.impl.UserLikeServiceImpl;
import cn.edu.lingnan.projectmanagment.service.impl.UserServiceImpl;
import cn.edu.lingnan.projectmanagment.service.impl.UserStoreServiceImpl;
import cn.edu.lingnan.projectmanagment.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public String myProjects(@PathVariable("id")Integer id){
        return "myprojects";
    }

    public Map<String, Object> functionPageCom(Integer page,Integer count){
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
            if (page > totalPage){
                return null;
            }
            // 计算sql需要的起始索引
            int offset = (page - 1) * pageSize;
            // 封装数据，并返回
            map.put("page", page);
            map.put("pageSize", pageSize);
            map.put("totalPage", totalPage);
            map.put("offset",offset);
            return map;
        } catch (Exception e) {
            System.out.println("获取函数数据失败" + e);
            return map;
        }
    }

    public ResponseEntity<Map<String, Object>> myProjects(Integer page, Integer userId,Integer status){
        System.out.println("当前页：" + page+"  当前用户：" + userId + " status" +status);
        Map<String, Object> map = new HashMap<String, Object>();
        // 每页显示条数
        int pageSize = 5;
        try {
            // 获取总条目数
            List<Myprojects> myprojectsList = null;
            if(status == 1){//我的项目
                myprojectsList = userService.getMyProjects(userId);
            }else if(status == 2){//我负责的项目
                myprojectsList = userService.getMyChargeProjects(userId);
            }else if(status == 3){//我参加的项目
                myprojectsList = userService.getMyJoinProjects(userId);
            }
            System.out.println("userid:"+userId+" 项目："+myprojectsList);
            int count = myprojectsList.size();
            map = functionPageCom(page,count);
            int offset = (int) map.get("offset");
            // 根据起始索引和页面大小去查询数据
            List<Myprojects> list = null;
            if(status == 1){//我的项目
                list = userService.getMyProjectsPage(userId,offset,pageSize);
            }else if(status == 2){//我负责的项目
                list = userService.getMyChargeProjectsPage(userId,offset,pageSize);
            }else if(status == 3){//我参加的项目
                list = userService.getMyJoinProjectsPage(userId,offset,pageSize);
            }
            System.out.println("分页list"+list);
            // 封装数据，并返回
            map.put("list", list);
            System.out.println("分页map"+map);
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("获取分页数据失败" + e);
            return new ResponseEntity<Map<String, Object>>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //我的全部项目
    @GetMapping("/my_project_all")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> myProjectsAll(Integer page, Integer userId){
        System.out.println("当前页：" + page+"  当前用户：" + userId);
        Map<String, Object> map = new HashMap<String, Object>();
        // 每页显示条数
        int pageSize = 5;
        try {
            // 获取总条目数
            List<Myprojects> myprojectsList = userService.getMyProjects(userId);
            System.out.println("userid:"+userId+" 我的项目："+myprojectsList);
            int count = myprojectsList.size();
            map = functionPageCom(page,count);
            int offset = (int) map.get("offset");
            // 根据起始索引和页面大小去查询数据
            List<Myprojects> list = userService.getMyProjectsPage(userId,offset,pageSize);
            System.out.println("分页list"+list);
            // 封装数据，并返回
            map.put("list", list);
            System.out.println("分页map"+map);
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("获取分页数据失败" + e);
            return new ResponseEntity<Map<String, Object>>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //我负责的项目
    @GetMapping("/my_project_charge")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> myProjectsCharge(Integer page, Integer userId){
        System.out.println("charge当前页：" + page+"  当前用户：" + userId);
        Map<String, Object> map = new HashMap<String, Object>();
        // 每页显示条数
        int pageSize = 5;
        try {
            // 获取总条目数
            List<Myprojects> myprojectsList = userService.getMyChargeProjects(userId);
            System.out.println("userid:"+userId+" 我负责的项目："+myprojectsList);
            int count = myprojectsList.size();
            map = functionPageCom(page,count);
            int offset = (int) map.get("offset");
            // 根据起始索引和页面大小去查询数据
            List<Myprojects> list = userService.getMyChargeProjectsPage(userId,offset,pageSize);
            System.out.println("我负责的项目分页list"+list);
            // 封装数据，并返回
            map.put("list", list);
            System.out.println("我负责的项目分页map"+map);
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("获取分页数据失败" + e);
            return new ResponseEntity<Map<String, Object>>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //我参加的项目
    @GetMapping("/my_project_join")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> myProjectsJoin(Integer page, Integer userId){
        System.out.println("join当前页：" + page+"  当前用户：" + userId);
        Map<String, Object> map = new HashMap<String, Object>();
        // 每页显示条数
        int pageSize = 5;
        try {
            // 获取总条目数
            List<Myprojects> myprojectsList = userService.getMyJoinProjects(userId);
            System.out.println("userid:"+userId+" 我负责的项目："+myprojectsList);
            int count = myprojectsList.size();
            map = functionPageCom(page,count);
            int offset = (int) map.get("offset");
            // 根据起始索引和页面大小去查询数据
            List<Myprojects> list = userService.getMyJoinProjectsPage(userId,offset,pageSize);
            System.out.println("我负责的项目分页list"+list);
            // 封装数据，并返回
            map.put("list", list);
            System.out.println("我负责的项目分页map"+map);
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("获取分页数据失败" + e);
            return new ResponseEntity<Map<String, Object>>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
    @ResponseBody
    @GetMapping("/my_project_refrech")
    public ResponseEntity<Map<String, Object>> myProjectsRefrech(Integer userId, Integer projectId,Integer page,Integer status){
        System.out.println("收藏项目:"+userId+" "+projectId+" "+page);
        UserStore userStore1 = new UserStore();
        userStore1.setUserId(userId);
        userStore1.setProjectsId(projectId);
        System.out.println("添加UserStore"+userStore1);
        userStoreService.addUserStore(userStore1);
        Integer storenum = userStoreService.countProjectBeStored(projectId);
        Projects projects = projectService.getById(projectId);
        projects.setStoreCount(storenum);
        System.out.println("收藏项目"+projects);
        projectService.editProject(projects);

        ResponseEntity<Map<String, Object>> m ;
        m = myProjects(page,userId,status);
        System.out.println("收藏项目,返回："+m);
        return m;
    }

    //我的项目--取消收藏
    @ResponseBody
    @GetMapping("/my_project_del_refrech")
    public ResponseEntity<Map<String, Object>> myProjectsDelRefrech(Integer userId,Integer projectId,Integer page,Integer status){
        System.out.println("取消收藏项目:"+userId+" "+projectId+" "+page);
        userStoreService.deleteUserStore(userId,projectId);
        Integer storenum = userStoreService.countProjectBeStored(projectId);
        Projects projects = projectService.getById(projectId);
        projects.setStoreCount(storenum);
        System.out.println("取消收藏项目"+projects);
        projectService.editProject(projects);
        ResponseEntity<Map<String, Object>> m ;
        m = myProjects(page,userId,status);
        System.out.println("取消收藏项目,返回："+m);
        return m;
    }

    //我的项目--点赞
    @ResponseBody
    @GetMapping("/my_project_refrech_like")
    public ResponseEntity<Map<String, Object>>  myProjectsRefrechLike(Integer userId,Integer projectId,Integer page,Integer status){
        System.out.println("点赞项目:"+userId+" "+projectId+" "+page);
        UserLike userLike1 = new UserLike();
        userLike1.setUserId(userId);
        userLike1.setProjectsId(projectId);
        System.out.println("添加userLike"+userLike1);
        userLikeService.addUserLike(userLike1);
        Integer storenum = userLikeService.countProjectBeLiked(projectId);
        Projects projects = projectService.getById(projectId);
        projects.setLikeCount(storenum);
        System.out.println("点赞项目"+projects);
        projectService.editProject(projects);
        ResponseEntity<Map<String, Object>> m ;
        m = myProjects(page,userId,status);
        System.out.println("点赞项目,返回："+m);
        return m;
    }

    //我的项目--取消点赞
    @ResponseBody
    @GetMapping("/my_project_del_refrech_like")
    public ResponseEntity<Map<String, Object>> myProjectsDelRefrechLike(Integer userId,Integer projectId,Integer page,Integer status){
        userLikeService.deleteUserLike(userId,projectId);
        Integer likenum = userLikeService.countProjectBeLiked(projectId);
        Projects projects = projectService.getById(projectId);
        projects.setLikeCount(likenum);
        System.out.println("取消点赞项目"+projects);
        projectService.editProject(projects);
        ResponseEntity<Map<String, Object>> m ;
        m = myProjects(page,userId,status);
        System.out.println("取消点赞项目,返回："+m);
        return m;
    }

    //查看我的项目收藏
    @GetMapping("/my_projects_store/{id}")
    public String myProjectsStore(@PathVariable("id")Integer id){
        return "mystore";
    }

    //查看我的项目收藏
    @GetMapping("/my_projects_store")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> myProjectsStore(Integer page, Integer userId){
        System.out.println("我的项目收藏当前页：" + page+"  当前用户：" + userId);
        Map<String, Object> map = new HashMap<String, Object>();
        // 每页显示条数
        int pageSize = 5;
        try {
            // 获取总条目数
            List<Myprojects> myprojectsList = userService.getMyProjectsStore(userId);
            System.out.println("userid:"+userId+" 我的项目收藏："+myprojectsList);
            int count = myprojectsList.size();
            map = functionPageCom(page,count);
            int offset = (int) map.get("offset");
            // 根据起始索引和页面大小去查询数据
            List<Myprojects> list = userService.getMyProjectsStorePage(userId,offset,pageSize);
            System.out.println("分页list"+list);
            // 封装数据，并返回
            map.put("list", list);
            System.out.println("分页map"+map);
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("获取分页数据失败" + e);
            return new ResponseEntity<Map<String, Object>>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Map<String, Object>> myStoreProjectsCom(Integer page, Integer userId){
        System.out.println("我的收藏--当前页：" + page+"  当前用户：" + userId);
        Map<String, Object> map = new HashMap<String, Object>();
        // 每页显示条数
        int pageSize = 5;
        try {
            // 获取总条目数
            List<Myprojects> myprojectsList = userService.getMyProjectsStore(userId);
            System.out.println("userid:"+userId+" 我的项目收藏："+myprojectsList);
            int count = myprojectsList.size();
            if(count <= (pageSize*(page-1))){
                page = page-1;
            }
            System.out.println("数量="+count);
            map = functionPageCom(page,count);
            int offset = (int) map.get("offset");
            // 根据起始索引和页面大小去查询数据
            List<Myprojects> list = userService.getMyProjectsStorePage(userId,offset,pageSize);
            System.out.println("分页list"+list);
            // 封装数据，并返回
            map.put("list", list);
            System.out.println("分页map"+map);
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("获取分页数据失败" + e);
            return new ResponseEntity<Map<String, Object>>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //我的收藏--取消收藏
    @GetMapping("/my_project_store_del")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> myProjectsStoreDel(Integer userId,Integer projectId,Integer page){
        System.out.println("取消收藏--我的收藏:"+userId+" "+projectId+" "+page);
        userStoreService.deleteUserStore(userId,projectId);
        Integer storenum = userStoreService.countProjectBeStored(projectId);
        Projects projects = projectService.getById(projectId);
        projects.setStoreCount(storenum);
        System.out.println("取消收藏项目"+projects);
        projectService.editProject(projects);
        ResponseEntity<Map<String, Object>> m ;
        m = myStoreProjectsCom(page,userId);
        System.out.println("取消收藏项目,返回："+m);
        return m;
    }

    //我的收藏--点赞
    @GetMapping("/my_project_store_like")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> myProjectsStoreLike(Integer userId,Integer projectId,Integer page){
        System.out.println("点赞--我的收藏:"+userId+" "+projectId+" "+page);
        UserLike userLike1 = new UserLike();
        userLike1.setUserId(userId);
        userLike1.setProjectsId(projectId);
        System.out.println("添加userLike1"+userLike1);
        userLikeService.addUserLike(userLike1);
        Integer storenum = userLikeService.countProjectBeLiked(projectId);
        Projects projects = projectService.getById(projectId);
        projects.setLikeCount(storenum);
        System.out.println("点赞项目"+projects);
        projectService.editProject(projects);
        ResponseEntity<Map<String, Object>> m ;
        m = myStoreProjectsCom(page,userId);
        System.out.println("点赞收藏项目,返回："+m);
        return m;
    }

    //我的收藏--取消点赞
    @GetMapping("/my_project_store_del_like")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> myProjectsStoreDelLike(Integer userId,Integer projectId,Integer page){
        System.out.println("取消点赞--我的收藏:"+userId+" "+projectId+" "+page);
        userLikeService.deleteUserLike(userId,projectId);
        Integer likenum = userLikeService.countProjectBeLiked(projectId);
        Projects projects = projectService.getById(projectId);
        projects.setLikeCount(likenum);
        System.out.println("取消点赞项目"+projects);
        projectService.editProject(projects);
        ResponseEntity<Map<String, Object>> m ;
        m = myStoreProjectsCom(page,userId);
        System.out.println("取消点赞收藏项目,返回："+m);
        return m;
    }

    //我的全部项目--按类型和进度搜索
    @GetMapping("/get_project_by_type")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getMyProjectsByType(Integer page,String type,String schedule,HttpServletRequest request){
        MyUserDetails myUserDetails = UserUtil.getMyUserDetailsBySecurity(request);
        System.out.println("当前页：" +page+"  当前用户：" + myUserDetails);
        System.out.println("type："+type+" schedule="+schedule);
        Map<String, Object> map = new HashMap<String, Object>();
        // 每页显示条数
        int pageSize = 5;
        List<Myprojects> myprojectsList= null;
        int count;
        List<Myprojects> list = null;
        try {
            // 获取总条目数
            if("null".equals(type) && "null".equals(schedule) ){
                myprojectsList = userService.getMyProjects(myUserDetails.getId());
                count = myprojectsList.size();
                map = functionPageCom(page,count);
                int offset = (int) map.get("offset");
                list = userService.getMyProjectsPage(myUserDetails.getId(),offset,pageSize);
            }else if("null".equals(schedule)){
                //按类型查
                count = userService.getMyProjectsByType(myUserDetails.getId(),type);
                map = functionPageCom(page,count);
                int offset = (int) map.get("offset");
                list = userService.getMyProjectsPageByType(myUserDetails.getId(),type,offset,pageSize);
            }else if("null".equals(type)){
                //按进度查
                count = userService.getMyProjectsBySchedule(myUserDetails.getId(),schedule);
                map = functionPageCom(page,count);
                int offset = (int) map.get("offset");
                list = userService.getMyProjectsPageBySchedule(myUserDetails.getId(),schedule,offset,pageSize);
            }else{
                //按类型、进度查
                count = userService.getMyProjectsByTypeSchedule(myUserDetails.getId(),type,schedule);
                map = functionPageCom(page,count);
                int offset = (int) map.get("offset");
                list = userService.getMyProjectsPageByTypeSchedule(myUserDetails.getId(),type,schedule,offset,pageSize);
            }
            System.out.println("分页list"+list);
            // 封装数据，并返回
            map.put("list", list);
            System.out.println("分页map"+map);
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("获取分页数据失败" + e);
            return new ResponseEntity<Map<String, Object>>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //我的全部项目--按项目名或负责人模糊搜索
    @GetMapping("/get_project_by_name_or_user")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getMyProjectsByNameOrUser(Integer page,String nameOrUser,HttpServletRequest request){
        MyUserDetails myUserDetails = UserUtil.getMyUserDetailsBySecurity(request);
        System.out.println("当前页：" +page+"  nameOrUser："+nameOrUser+"  当前用户：" + myUserDetails);
        Map<String, Object> map = new HashMap<String, Object>();
        // 每页显示条数
        int pageSize = 5;
        List<Myprojects> myprojectsList= null;
        int count=0;
        List<Myprojects> list = null;
        try {
            // 获取总条目数
            if(nameOrUser == null){
                myprojectsList = userService.getMyProjects(myUserDetails.getId());
                count = myprojectsList.size();
                map = functionPageCom(page,count);
                int offset = (int) map.get("offset");
                list = userService.getMyProjectsPage(myUserDetails.getId(),offset,pageSize);
            }else{
                count = userService.getMyProjectsByNameOrUser(myUserDetails.getId(),nameOrUser);
                map = functionPageCom(page,count);
                int offset = (int) map.get("offset");
                list = userService.getMyProjectsPageByNameOrUser(myUserDetails.getId(),nameOrUser,offset,pageSize);
            }
            System.out.println("分页list"+list);
            // 封装数据，并返回
            map.put("list", list);
            System.out.println("分页map"+map);
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("获取分页数据失败" + e);
            return new ResponseEntity<Map<String, Object>>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //我负责的项目--按类型和进度搜索
    @GetMapping("/get_project_charge_by_type")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getMyProjectsChargeByType(Integer page,String type,String schedule,HttpServletRequest request){
        MyUserDetails myUserDetails = UserUtil.getMyUserDetailsBySecurity(request);
        System.out.println("当前页：" +page+"  当前用户：" + myUserDetails);
        System.out.println("type："+type+" schedule="+schedule);
        Map<String, Object> map = new HashMap<String, Object>();
        // 每页显示条数
        int pageSize = 5;
        List<Myprojects> myprojectsList= null;
        int count;
        List<Myprojects> list = null;
        try {
            // 获取总条目数
            if("null".equals(type) && "null".equals(schedule) ){
                myprojectsList = userService.getMyChargeProjects(myUserDetails.getId());
                count = myprojectsList.size();
                map = functionPageCom(page,count);
                int offset = (int) map.get("offset");
                list = userService.getMyChargeProjectsPage(myUserDetails.getId(),offset,pageSize);
            }else if("null".equals(schedule)){
                //按类型查
                count = userService.getMyProjectsChargeByType(myUserDetails.getId(),type);
                map = functionPageCom(page,count);
                int offset = (int) map.get("offset");
                list = userService.getMyProjectsChargePageByType(myUserDetails.getId(),type,offset,pageSize);
            }else if("null".equals(type)){
                //按进度查
                count = userService.getMyProjectsChargeBySchedule(myUserDetails.getId(),schedule);
                map = functionPageCom(page,count);
                int offset = (int) map.get("offset");
                list = userService.getMyProjectsChargePageBySchedule(myUserDetails.getId(),schedule,offset,pageSize);
            }else{
                //按类型、进度查
                count = userService.getMyProjectsChargeByTypeSchedule(myUserDetails.getId(),type,schedule);
                map = functionPageCom(page,count);
                int offset = (int) map.get("offset");
                list = userService.getMyProjectsChargePageByTypeSchedule(myUserDetails.getId(),type,schedule,offset,pageSize);
            }
            System.out.println("分页list"+list);
            // 封装数据，并返回
            map.put("list", list);
            System.out.println("分页map"+map);
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("获取分页数据失败" + e);
            return new ResponseEntity<Map<String, Object>>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //我负责的项目--按项目名或负责人模糊搜索
    @GetMapping("/get_project_charge_by_name_or_user")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getMyProjectsChargeByNameOrUser(Integer page,String nameOrUser,HttpServletRequest request){
        MyUserDetails myUserDetails = UserUtil.getMyUserDetailsBySecurity(request);
        System.out.println("当前页：" +page+"  nameOrUser："+nameOrUser+"  当前用户：" + myUserDetails);
        Map<String, Object> map = new HashMap<String, Object>();
        // 每页显示条数
        int pageSize = 5;
        List<Myprojects> myprojectsList= null;
        int count=0;
        List<Myprojects> list = null;
        try {
            // 获取总条目数
            if(nameOrUser == null){
                myprojectsList = userService.getMyChargeProjects(myUserDetails.getId());
                count = myprojectsList.size();
                map = functionPageCom(page,count);
                int offset = (int) map.get("offset");
                list = userService.getMyChargeProjectsPage(myUserDetails.getId(),offset,pageSize);
            }else{
                count = userService.getMyProjectsChargeByNameOrUser(myUserDetails.getId(),nameOrUser);
                map = functionPageCom(page,count);
                int offset = (int) map.get("offset");
                list = userService.getMyProjectsChargePageByNameOrUser(myUserDetails.getId(),nameOrUser,offset,pageSize);
            }
            System.out.println("分页list"+list);
            // 封装数据，并返回
            map.put("list", list);
            System.out.println("分页map"+map);
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("获取分页数据失败" + e);
            return new ResponseEntity<Map<String, Object>>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //我参与的项目--按类型和进度搜索
    @GetMapping("/get_project_join_by_type")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getMyProjectsJoinByType(Integer page,String type,String schedule,HttpServletRequest request){
        MyUserDetails myUserDetails = UserUtil.getMyUserDetailsBySecurity(request);
        System.out.println("当前页：" +page+"  当前用户：" + myUserDetails);
        System.out.println("type："+type+" schedule="+schedule);
        Map<String, Object> map = new HashMap<String, Object>();
        // 每页显示条数
        int pageSize = 5;
        List<Myprojects> myprojectsList= null;
        int count;
        List<Myprojects> list = null;
        try {
            // 获取总条目数
            if("null".equals(type) && "null".equals(schedule) ){
                myprojectsList = userService.getMyJoinProjects(myUserDetails.getId());
                count = myprojectsList.size();
                map = functionPageCom(page,count);
                int offset = (int) map.get("offset");
                list = userService.getMyJoinProjectsPage(myUserDetails.getId(),offset,pageSize);
            }else if("null".equals(schedule)){
                //按类型查
                count = userService.getMyProjectsJoinByType(myUserDetails.getId(),type);
                map = functionPageCom(page,count);
                int offset = (int) map.get("offset");
                list = userService.getMyProjectsJoinPageByType(myUserDetails.getId(),type,offset,pageSize);
            }else if("null".equals(type)){
                //按进度查
                count = userService.getMyProjectsJoinBySchedule(myUserDetails.getId(),schedule);
                map = functionPageCom(page,count);
                int offset = (int) map.get("offset");
                list = userService.getMyProjectsJoinPageBySchedule(myUserDetails.getId(),schedule,offset,pageSize);
            }else{
                //按类型、进度查
                count = userService.getMyProjectsJoinByTypeSchedule(myUserDetails.getId(),type,schedule);
                map = functionPageCom(page,count);
                int offset = (int) map.get("offset");
                list = userService.getMyProjectsJoinPageByTypeSchedule(myUserDetails.getId(),type,schedule,offset,pageSize);
            }
            System.out.println("分页list"+list);
            // 封装数据，并返回
            map.put("list", list);
            System.out.println("分页map"+map);
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("获取分页数据失败" + e);
            return new ResponseEntity<Map<String, Object>>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //我参与的项目--按项目名或负责人模糊搜索
    @GetMapping("/get_project_join_by_name_or_user")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getMyProjectsJoinByNameOrUser(Integer page,String nameOrUser,HttpServletRequest request){
        MyUserDetails myUserDetails = UserUtil.getMyUserDetailsBySecurity(request);
        System.out.println("当前页：" +page+"  nameOrUser："+nameOrUser+"  当前用户：" + myUserDetails);
        Map<String, Object> map = new HashMap<String, Object>();
        // 每页显示条数
        int pageSize = 5;
        List<Myprojects> myprojectsList= null;
        int count=0;
        List<Myprojects> list = null;
        try {
            // 获取总条目数
            if(nameOrUser == null){
                myprojectsList = userService.getMyJoinProjects(myUserDetails.getId());
                count = myprojectsList.size();
                map = functionPageCom(page,count);
                int offset = (int) map.get("offset");
                list = userService.getMyJoinProjectsPage(myUserDetails.getId(),offset,pageSize);
            }else{
                count = userService.getMyProjectsJoinByNameOrUser(myUserDetails.getId(),nameOrUser);
                map = functionPageCom(page,count);
                int offset = (int) map.get("offset");
                list = userService.getMyProjectsJoinPageByNameOrUser(myUserDetails.getId(),nameOrUser,offset,pageSize);
            }
            System.out.println("分页list"+list);
            // 封装数据，并返回
            map.put("list", list);
            System.out.println("分页map"+map);
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("获取分页数据失败" + e);
            return new ResponseEntity<Map<String, Object>>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}


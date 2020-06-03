package cn.edu.lingnan.projectmanagment.controller;

import cn.edu.lingnan.projectmanagment.bean.*;
import cn.edu.lingnan.projectmanagment.exception.AJaxResponse;
import cn.edu.lingnan.projectmanagment.service.impl.*;
import cn.edu.lingnan.projectmanagment.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

@Controller
public class ProjectsIndexController {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserStoreServiceImpl userStoreService;

    @Autowired
    private ProjectServiceImpl projectService;

    @Autowired
    private UserClickServiceImpl userClickService;

    //查看我的项目
    @GetMapping("/projects_index/{userId}")
    public String getProjectsIndex(@PathVariable("userId") Integer id, Model model) {
        List list = getRecommendProject(id);
        if (list.size() >= 0) {
            model.addAttribute("projects", list);
        }
        /* int pageNum1 = pageNum == null ? 1 : pageNum;
            int pageSize = 5;
            //我所有项目
            List projectsList = projectService.getProject(pageNum1,pageSize);
            System.out.println("项目推荐：" + projectsList);
            model.addAttribute("projects", projectsList);
            int totalRecord = projectService.countProjectsRecommendation();
            Page p = new Page(pageNum, pageSize, totalRecord);
            model.addAttribute("page", p);
            int start=p.getStart();
            int end=p.getEnd();
            System.out.println("start:::"+start+"end:::"+end);
            int length=end-start+1;
            int[] pageList=new int[length];
            for (int i=start;i<=end;i++){
                pageList[i-1]=i;
            }
            model.addAttribute("pageList", pageList);
            System.out.println("pageList:::"+pageList);*/
        List todayProjectsList = projectService.getTodayProject();
        System.out.println("今日推荐：" + todayProjectsList);
        model.addAttribute("todayProjects", todayProjectsList);
        List weekProjectsList = projectService.getWeekProject();
        System.out.println("本周推荐：" + weekProjectsList);
        model.addAttribute("weekProjects", weekProjectsList);
        return "project_index";
    }

    //我的项目--收藏
    @PostMapping("/projects_index_refrech")
    @ResponseBody
    public AJaxResponse myProjectsRefrech(Integer userId, Integer projectId) {
        //查看数据库是否存在此收藏记录
        UserStore userStore = userStoreService.getOneUserStore(userId, projectId);
        //没有就添加此记录
        if (userStore == null) {
            UserStore userStore1 = new UserStore();
            Date now = new Date();
            //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
            //String time = dateFormat.format(now);
            System.out.println("now:::" + now);
            userStore1.setUserId(userId);
            userStore1.setProjectsId(projectId);
            userStore1.setStoreTime(now);
            System.out.println("添加UserStore" + userStore1);
            userStoreService.addUserStore(userStore1);
        } else {
            //否则则将数据库中的记录恢复
            userStoreService.reductionUserStore(userId, projectId);
        }
        //统计项目的收藏次数
        Integer storenum = userStoreService.countProjectBeStored(projectId);
        //获得被收藏对象
        Projects projects = projectService.getById(projectId);
        //重新为StoreCount收藏属性赋值
        projects.setStoreCount(storenum);
        System.out.println("收藏项目" + projects);
        //修改数据库项目记录
        projectService.editProject(projects);
        //重定向回推荐项目页面
        return AJaxResponse.success(projects, "收藏成功");
    }

    //我的项目--取消收藏
    @PostMapping("/projects_index_del_refrech")
    @ResponseBody
    public AJaxResponse myProjectsDelRefrech(Integer userId, Integer projectId) {
        //取消收藏
        userStoreService.deleteUserStore(userId, projectId);
        //重新统计收藏总次数
        Integer storenum = userStoreService.countProjectBeStored(projectId);
        //获得取消收藏对象
        Projects projects = projectService.getById(projectId);
        //重新为StoreCount收藏属性赋值
        projects.setStoreCount(storenum);
        System.out.println("取消收藏项目" + projects);
        //修改数据库项目记录
        projectService.editProject(projects);
        //重定向回推荐项目页面
        return AJaxResponse.success(projects, "取消收藏成功");
    }

    /**
     * 统计点击量(已经转移到project_view进行)
     *
     * @param userId
     * @param projectId
     * @return
     */
    @PostMapping("/projects_index_click")
    @ResponseBody
    public AJaxResponse getProjectsClick(Integer userId, Integer projectId) {
        projectService.updateProjectClickNumber(projectId);
        Date now = new Date();
        UserClick userClick1 = new UserClick();
        userClick1.setUserId(userId);
        userClick1.setProjectsId(projectId);
        userClick1.setClickTime(now);
        userClickService.addUserClick(userClick1);
        return AJaxResponse.success("/projects_view?projectId=" + projectId, "点击成功");
    }

/*
    @GetMapping("/documentRecordPage")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> pages(Integer page, Integer projectId, Integer userId) {

    }
*/

    /**
     * 协同过滤获取推荐项目
     * @param userId
     * @return
     */
    @GetMapping("/projectsRecommendationPage")
    @ResponseBody
    public List getRecommendProject(Integer userId) {
        System.out.println("Input the total users number:");
        //输入用户总量
        int N = userStoreService.countUserByStored();
        System.out.println(N);
        int[][] sparseMatrix = new int[N][N];
        //建立用户稀疏矩阵，用于用户相似度计算【相似度矩阵】
        Map<String, Integer> userItemLength = new HashMap<>();
        //存储每一个用户对应的不同物品总数 eg: A 3
        Map<String, Set<String>> itemUserCollection = new HashMap<>();
        //建立物品到用户的倒排表 eg: a A B
        Set<String> items = new HashSet<>();
        //辅助存储物品集合
        Map<String, Integer> userID = new HashMap<>();
        //辅助存储每一个用户的用户ID映射
        Map<Integer, String> idUser = new HashMap<>();
        //辅助存储每一个ID对应的用户映射
        List<List<ProjectsRecommendation>> list = new ArrayList<>();
        System.out.println("Input user--items maping infermation:<eg:A a b d>");
        userStoreService.findTheFirstRecored();
        for (int i = 0; i < N; i++) {
            //依次处理N个用户 输入数据 以空格间隔
            // String[] user_item = scanner.nextLine().split(" ");
            UserStore userStore = userStoreService.findTheSurplusRecored(i);

            String[] user_item = {userStore.getUserId().toString(), userStore.getProjectsId().toString()};
            for (int k = 0; k < user_item.length; k++) {
                System.out.print(user_item[k]);
            }
            int length = user_item.length;
            userItemLength.put(user_item[0], length - 1);
            //eg: A 3
            userID.put(user_item[0], i);
            //用户ID与稀疏矩阵建立对应关系
            idUser.put(i, user_item[0]);
            //建立物品--用户倒排表
            for (int j = 1; j < length; j++) {
                if (items.contains(user_item[j])) {
                    //如果已经包含对应的物品--用户映射，直接添加对应的用户
                    itemUserCollection.get(user_item[j]).add(user_item[0]);
                } else {
                    //否则创建对应物品--用户集合映射
                    items.add(user_item[j]);
                    itemUserCollection.put(user_item[j], new HashSet<String>());
                    //创建物品--用户倒排关系
                    itemUserCollection.get(user_item[j]).add(user_item[0]);
                }
            }
        }
        System.out.println(itemUserCollection.toString());
        //计算相似度矩阵【稀疏】
        Set<Entry<String, Set<String>>> entrySet = itemUserCollection.entrySet();
        Iterator<Entry<String, Set<String>>> iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            Set<String> commonUsers = iterator.next().getValue();
            for (String user_u : commonUsers) {
                for (String user_v : commonUsers) {
                    if (user_u.equals(user_v)) {
                        continue;
                    }
                    sparseMatrix[userID.get(user_u)][userID.get(user_v)] += 1;
                    //计算用户u与用户v都有正反馈的物品总数
                }
            }
        }
        System.out.println(userItemLength.toString());
        System.out.println("Input the user for recommendation:<eg:A>");
        String recommendUser = userId.toString();
        System.out.println(userID.get(recommendUser));
        //计算用户之间的相似度【余弦相似性】
        int recommendUserId = userID.get(recommendUser);
        for (int j = 0; j < sparseMatrix.length; j++) {
            if (j != recommendUserId) {
                System.out.println(idUser.get(recommendUserId) + "--" + idUser.get(j) + "相似度:" + sparseMatrix[recommendUserId][j] / Math.sqrt(userItemLength.get(idUser.get(recommendUserId)) * userItemLength.get(idUser.get(j))));
            }
        }
        //计算指定用户recommendUser的物品推荐度
        for (String item : items) {
            //遍历每一件物品
            Set<String> users = itemUserCollection.get(item);

            //得到购买当前物品的所有用户集合
            if (!users.contains(recommendUser)) {
                //如果被推荐用户没有购买当前物品，则进行推荐度计算
                double itemRecommendDegree = 0.0;
                for (String user : users) {
                    itemRecommendDegree += sparseMatrix[userID.get(recommendUser)][userID.get(user)] / Math.sqrt(userItemLength.get(recommendUser) * userItemLength.get(user));
                    //推荐度计算
                }
                System.out.println("The item " + item + " for " + recommendUser + "'s recommended degree:" + itemRecommendDegree);
                if (itemRecommendDegree > 0.0) {
                    List<ProjectsRecommendation> projects = projectService.getRecommendedCommodities(userId,Integer.valueOf(item));
                    list.add(projects);
                }
            }
        }
        System.out.println("list:" + list);
        List list1 = new ArrayList();
        if (list.size() >= 0) {
            System.out.println(list.size());
            for (int l = 0; l < list.size(); l++) {
                List<ProjectsRecommendation> projectsRecommendation1 = list.get(0);
                ProjectsRecommendation projectsRecommendation2 = projectsRecommendation1.get(0);
                list1.add(projectsRecommendation2);
            }
            System.out.println("list11111" + list1);
            return list1;
        } else {
            return null;
        }

    }

    //根据关键字获取搜索提示
    @GetMapping("/searchWord")
    @ResponseBody
    public List getMyProjectsByType(String word){
        System.out.println("关键字："+word);
        List<String> list = projectService.getProjectNameByWord(word);
        List<String> list1 = projectService.getTypeByWord(word);
        List<String> list2 = projectService.getUserNameByWord(word);
        if (list.size()>0){
            return list;
        }else if (list1.size()>0){
            return list1;
        }else if (list2.size()>0){
            return list2;
        } else {
            return null;
        }
    }

    public Map<String, Object> functionPageCom(Integer page,Integer count) {
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

    //按项目名、用户名以及项目类型进行搜索
    @GetMapping("/get_project_by_word")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getProjectsByWord(Integer page,String word,HttpServletRequest request){
        MyUserDetails myUserDetails = UserUtil.getMyUserDetailsBySecurity(request);
        System.out.println("当前页：" +page+"  关键字："+word+"  当前用户：" + myUserDetails);
        Map<String, Object> map = new HashMap<String, Object>();
        // 每页显示条数
        int pageSize = 5;
        int count=0;
        List<ProjectsRecommendation> list = null;
        try {
            // 获取总条目数
            if(word == null || word.equals("")){
                count = projectService.countProjectsRecommendation();
                map = functionPageCom(page,count);
                int offset = (int) map.get("offset");
                list =  projectService.getProject(myUserDetails.getId(),offset, pageSize);
            } else{
                count = projectService.countProjectsByWord(word);
                map = functionPageCom(page,count);
                int offset = (int) map.get("offset");
                list = projectService.getMyProjectsByWord(word,offset,pageSize);
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

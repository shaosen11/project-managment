package cn.edu.lingnan.projectmanagment.service;


import cn.edu.lingnan.projectmanagment.bean.MyUserDetails;
import cn.edu.lingnan.projectmanagment.bean.Myprojects;
import cn.edu.lingnan.projectmanagment.bean.UserRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author shaosen
 */
public interface UserService {
    /**
     * 前端异步请求注册邮箱是否存在
     * @param email
     * @return
     */
    MyUserDetails findByEmail(String email);

    /**
     * 注册用户
     * @param myUserDetails
     * @return
     */
    boolean addUser(MyUserDetails myUserDetails);

    /**
     * 更新用户信息
     * @param myUserDetails
     * @return
     */
    MyUserDetails updateUser(MyUserDetails myUserDetails);

    /**
     * 通过id查询是否存在
     * @return
     */
    MyUserDetails findById(Integer id);

    /**
     * 查询所有用户信息
     * @return
     */
    List<MyUserDetails> getUserList();

    /**
     * 添加用户2
     * @param myUserDetails
     * @return
     */
    boolean addUser2(MyUserDetails myUserDetails);

    /**
     * 删除用户
     * @return
     */
    boolean deleteUser(Integer id);

    /**
     * 修改用户信息
     * @return
     */
    boolean editUser(MyUserDetails myUserDetails);

    /**
     * 还原用户
     * @return
     */
    boolean reductionUser(Integer id);

    /**
     * 查询所有已注销用户信息
     * @return
     */
    List<MyUserDetails> getDeletedUserList();

    /**
     * 查询我的项目
     * @return
     */
    List<Myprojects> getMyProjects(Integer id);

    /**
     * 查询我负责的项目(user)
     * @return
     */
    List<Myprojects> getMyChargeProjects(Integer id);

    /**
     * 查询我参与的项目(user)
     * @return
     */
    List<Myprojects> getMyJoinProjects(Integer id);

    /**
     * 查询我所有的项目进度数量(饼图)
     * @return
     */
    Integer myProjectScheduleNum(Integer id,String schedule);

    /**
     * 查询我负责的项目进度数量(饼图1)
     * @return
     */
    Integer myProjectScheduleNum2(Integer id,String schedule);

    /**
     * 查询我参加的项目进度数量(饼图1)
     * @return
     */
    Integer myProjectScheduleNum3(Integer id,String schedule);

    /**
     * 查询我的项目收藏(user)
     * @return
     */
    List<Myprojects> getMyProjectsStore(Integer id);
}

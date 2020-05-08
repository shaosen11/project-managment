package cn.edu.lingnan.projectmanagment.mapper;

import cn.edu.lingnan.projectmanagment.bean.MyUserDetails;
import cn.edu.lingnan.projectmanagment.bean.Myprojects;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author shaosen
 */
@Mapper
@Repository
public interface UserMapper {
    /**
     * 前端异步请求注册邮箱是否存在
     *
     * @param email
     * @return
     */
    MyUserDetails findByEmail(@Param("email") String email);

    /**
     * 注册用户
     *
     * @param myUserDetails
     * @return
     */
    boolean addUser(MyUserDetails myUserDetails);

    /**
     * 更新用户信息
     *
     * @param myUserDetails
     */
    void updateUser(MyUserDetails myUserDetails);

    /**
     * 通过id查询是否存在
     *
     * @return
     */
    MyUserDetails findById(Integer id);

    /**
     * 查询所有用户信息
     */
    List<MyUserDetails> getUserList();

    /**
     * 注册用户
     *
     * @param myUserDetails
     * @return
     */
    boolean addUser2(MyUserDetails myUserDetails);

    /**
     * 删除用户
     *
     * @return
     */
    boolean deleteUser(Integer id);

    /**
     * 修改用户信息
     *
     * @return
     */
    boolean editUser(MyUserDetails myUserDetails);

    /**
     * 还原用户
     *
     * @return
     */
    boolean reductionUser(Integer id);

    /**
     * 查询所有已注销用户信息
     *
     * @return
     */
    List<MyUserDetails> getDeletedUserList();

    /**
     * 查询我的所有项目(user)
     *
     * @return
     */
    List<Myprojects> getMyProjects(Integer id);

    /**
     * 查询我负责的项目(user)
     *
     * @return
     */
    List<Myprojects> getMyChargeProjects(Integer id);

    /**
     * 查询我参与的项目(user)
     *
     * @return
     */
    List<Myprojects> getMyJoinProjects(Integer id);

    /**
     * 查询我所有的项目进度数量(饼图1)
     *
     * @return
     */
    Integer myProjectScheduleNum(@Param("id") Integer id, @Param("schedule") String schedule);

    /**
     * 查询我负责的项目进度数量(饼图1)
     *
     * @return
     */
    Integer myProjectScheduleNum2(@Param("id") Integer id, @Param("schedule") String schedule);

    /**
     * 查询我参加的项目进度数量(饼图1)
     *
     * @return
     */
    Integer myProjectScheduleNum3(@Param("id") Integer id, @Param("schedule") String schedule);

    /**
     * 查询我的项目收藏(user)
     *
     * @return
     */
    List<Myprojects> getMyProjectsStore(Integer id);

    /**
     * 查询user的信息和项目
     *
     * @param userId
     * @return
     */
    MyUserDetails getMyUserDetailsByUserId(Integer userId);

    /**
     * 查询简要的用户信息
     *
     * @param id
     * @return
     */
    MyUserDetails getSimpleMyUserDetailsByUserId(Integer id);
}



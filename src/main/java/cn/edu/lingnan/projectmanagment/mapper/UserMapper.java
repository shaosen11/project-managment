package cn.edu.lingnan.projectmanagment.mapper;

import cn.edu.lingnan.projectmanagment.bean.MyUserDetails;
import cn.edu.lingnan.projectmanagment.bean.UserRecord;
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
     * @param email
     * @return
     */
    MyUserDetails checkEmail(@Param("email") String email);

    /**
     * 注册用户
     * @param myUserDetails
     * @return
     */
    boolean addUser(MyUserDetails myUserDetails);

    /**
     * 更新用户信息
     * @param myUserDetails
     */
    void updateUser(MyUserDetails myUserDetails);

    /**
     * 查询所有用户信息
     */
    List<MyUserDetails> getUserList();

    /**
     * 注册用户
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
}


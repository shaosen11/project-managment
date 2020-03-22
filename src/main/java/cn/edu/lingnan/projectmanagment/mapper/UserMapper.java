package cn.edu.lingnan.projectmanagment.mapper;

import cn.edu.lingnan.projectmanagment.bean.MyUserDetails;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
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
}


package cn.edu.lingnan.projectmanagment.service;


import cn.edu.lingnan.projectmanagment.bean.MyUserDetails;
/**
 * @author shaosen
 */
public interface UserService {
    /**
     * 前端异步请求注册邮箱是否存在
     * @param email
     * @return
     */
    MyUserDetails checkEmail(String email);

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
}

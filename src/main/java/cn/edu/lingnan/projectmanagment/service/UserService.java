package cn.edu.lingnan.projectmanagment.service;


import cn.edu.lingnan.projectmanagment.bean.MyUserDetails;

public interface UserService {
    MyUserDetails checkEmail(String username);
    boolean addUser(MyUserDetails myUserDetails);
    MyUserDetails updateUser(MyUserDetails myUserDetails);
}

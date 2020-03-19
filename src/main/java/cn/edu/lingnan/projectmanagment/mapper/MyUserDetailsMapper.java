package cn.edu.lingnan.projectmanagment.mapper;

import cn.edu.lingnan.projectmanagment.bean.MyUserDetails;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MyUserDetailsMapper {
    //根据userID查询用户信息
    MyUserDetails findByEmail(@Param("email") String email);

    //根据userID查询你用户角色
    List<String> findRoleByEmail(@Param("email") String email);

    //根据用户角色查询用户权限
    List<String> findAuthorityByRoleCodes(@Param("roleCodes") List<String> roleCodes);
}
